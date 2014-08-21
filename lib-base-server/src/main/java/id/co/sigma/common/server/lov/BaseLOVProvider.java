package id.co.sigma.common.server.lov;

import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.lov.CommonLOVHeader;
import id.co.sigma.common.data.lov.LOVRequestArgument;
import id.co.sigma.common.data.lov.StrongTypedCustomLOVID;

import java.util.List;


/**
 * base class untuk lov provider service
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 5 aug 2012
 **/
public abstract class BaseLOVProvider {


	/**
	 * memabca data lookup. ini berisi pembacaan dari database
	 **/
	public abstract List<CommonLOV> getLookupValues ( String localizationCode ); 
	
	
	
	
	/**
	 * cacheable atau tidak di sisi client. ini bisa di static. kalau di pertimbangkan LOV perubahan nya hampir 0 dalam 1 hari, set cacheable
	 **/
	public abstract boolean isCacheable (); 
	
	/**
	 * versi dari lookup. ini tidak boleh di cache. musti di select setiap ada request
	 **/
	public abstract String getVersion (); 
	
	/**
	 * ID dari lookup
	 **/
	public  String getId (){
		return getType().getModulGroupId() +"." +  getType().getId(); 
	} 
	
	
	
	/**
	 * strong type dari lov. jadinya mau tidak mau musti define enum untuk custom LOV
	 **/
	public abstract StrongTypedCustomLOVID getType (); 
	
	
	
	/**
	 * helper. akses lookup dengan {@link LOVRequestArgument}
	 * @param localizationCode kode localization
	 * @param requestArgument parameter request 
	 *   
	 *  
	 * @return : still sycn -> return null
	 **/
	public   CommonLOVHeader  getLookupValues ( String localizationCode ,  LOVRequestArgument requestArgument){
		if ( requestArgument==null)
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

		CommonLOVHeader retval = new CommonLOVHeader(); 
		retval.setCacheable(isCacheable()) ;
		retval.setSource(requestArgument.getSource()); 
		retval.setLovId(getId()) ; 
		retval.setDetails(getLookupValues(localizationCode)); 
		retval.setVersion(getVersion());
		return retval ;
	}
}
