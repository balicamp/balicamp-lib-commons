package id.co.sigma.common.server.lov;

import java.util.List;

import id.co.sigma.common.data.lov.Common2ndLevelLOV;
import id.co.sigma.common.data.lov.Common2ndLevelLOVHeader;
import id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument;
import id.co.sigma.common.data.lov.StrongTyped2ndLevelLOVID;
import id.co.sigma.common.exception.CacheStillUpToDateException;

/**
 * ini LOV untuk level 2. Lookup yang depend pada entry lain nya. jadinya ada kode parent nya untuk LOV data
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public abstract class Base2ndLevelLOVProvider<KEY , PARENTVALUE> {
	
	
	/**
	 * membaca data lookup. ini berisi pembacaan dari database
	 **/
	protected abstract List<Common2ndLevelLOV> getLookupValues ( String localizationCode , PARENTVALUE parentValue) throws Exception; 
	
	
	
	
	/**
	 * method ini bertugas membaca id dari parent. di cari berdasarkan ID dari lookup
	 **/
	protected abstract PARENTVALUE  getParentId ( KEY lookupValue) throws Exception;
	
	
	/**
	 * cacheable atau tidak di sisi client. ini bisa di static. kalau di pertimbangkan LOV perubahan nya hampir 0 dalam 1 hari, set cacheable
	 **/
	public abstract boolean isCacheable (); 
	
	/**
	 * versi dari lookup. ini tidak boleh di cache. musti di select setiap ada request
	 **/
	public abstract String getVersion (); 
	
	
	/**
	 * strong type dari lov. jadinya mau tidak mau musti define enum untuk custom LOV
	 **/
	public abstract StrongTyped2ndLevelLOVID getType (); 
	
	/**
	 * ID dari lookup
	 **/
	public  String getId (){
		return getType().getModulGroupId() +"." +  getType().getId(); 
	} 

	
	
	/**
	 * instantiate LOV header
	 **/
	protected abstract Common2ndLevelLOVHeader instantiateHeaderData(String parentValue) ;
	
	
	
	/**
	 * konversi ke actual object, tergantung pada tipe dari parent. kalau big integer jadi big int, Int jadi int . ini untuk validitas JPA, karena kemungkinan akan bermasalah kalau angka di kasi string dalam proses query
	 * @param parentReferenceAsString variable ke parent.  
	 **/
	protected abstract PARENTVALUE transalateParentToActualObject(String parentReferenceAsString);

	
	/**
	 * transalate dari 
	 * @param primaryKeyAsString variable primary key, di kirim dalam bentuk string
	 **/
	protected abstract KEY transalateKeyToActualObject(String primaryKeyAsString);
	/**
	 * worker untuk request LOV 2nd Level
	 **/
	public   Common2ndLevelLOVHeader  getLookupValues ( String localizationCode ,  LOV2ndLevelRequestArgument requestArgument) throws CacheStillUpToDateException , Exception{
		if(requestArgument==null)
			return null ; 
		boolean  isOutOfSync = true ; 
		if ( isCacheable()){
			String currentVersion  =  getVersion() ; 
			if ( currentVersion !=null){
				isOutOfSync= !currentVersion.equals(requestArgument.getCacheVersion()); 
			}
			
		}
		if ( !isOutOfSync && isCacheable()){
			return null ; // kalau masi up to sync maka di return null 
		}
		Common2ndLevelLOVHeader retval = instantiateHeaderData(requestArgument.getParentLovValueId());
		PARENTVALUE parentAsObject = transalateParentToActualObject(  requestArgument.getParentLovValueId()) ; 
		retval.setLookupValues(this.getLookupValues(localizationCode, parentAsObject ));
		
		return retval ; 
		
	} 
	
	public   Common2ndLevelLOVHeader getSameLevelLookupValues (String localizationCode ,String lookupValue, String clientVersion)throws CacheStillUpToDateException , Exception{
		PARENTVALUE parentId = getParentId(transalateKeyToActualObject(lookupValue));
		boolean  isOutOfSync = true ; 
		if ( isCacheable()){
			String currentVersion  =  getVersion() ; 
			if ( currentVersion !=null){
				isOutOfSync= !currentVersion.equals(clientVersion); 
			}
			
		}
		if ( !isOutOfSync && isCacheable()){
			throw new CacheStillUpToDateException("cache masih relevan untuk " + getType().getId(), getType().getId()) ; // kalau masi up to sync maka di return null 
		}
		Common2ndLevelLOVHeader retval = instantiateHeaderData(parentId==null?null:parentId.toString() );
		retval.setLookupValues(this.getLookupValues(localizationCode, parentId));
		return retval ;
	}
}
