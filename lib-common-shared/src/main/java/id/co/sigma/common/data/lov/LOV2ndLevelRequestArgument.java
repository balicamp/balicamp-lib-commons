package id.co.sigma.common.data.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class LOV2ndLevelRequestArgument implements  IsSerializable, IJSONFriendlyObject<LOV2ndLevelRequestArgument>{
	
	
	/**
	 * value yang di pilih pada parent
	 **/
	private String parentLovValueId ; 
	
	

	/**
	 * id Lookup
	 *
	 **/
	private StrongTyped2ndLevelLOVID lookupId ;
	
	
	
	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	private String cacheVersion; 
	
	
	
	
	
	public LOV2ndLevelRequestArgument() {
		
		super();
	}

	

	
	/**
	 * id Lookup
	 *
	 **/
	public StrongTyped2ndLevelLOVID getLookupId() {
		return lookupId;
	}
	/**
	 * id Lookup
	 *
	 **/
	public void setLookupId(StrongTyped2ndLevelLOVID lookupId) {
		this.lookupId = lookupId;
	}


	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	public void setCacheVersion(String cacheVersion) {
		this.cacheVersion = cacheVersion;
	}
	/**
	 * versi data yang di cche. kalau null berarti belum di cache. ambil versi langsung
	 **/
	public String getCacheVersion() {
		return cacheVersion;
	}
	
	
	/**
	 * value yang di pilih pada parent
	 **/
	public void setParentLovValueId(String parentLovValueId) {
		this.parentLovValueId = parentLovValueId;
	}
	/**
	 * value yang di pilih pada parent
	 **/
	public String getParentLovValueId() {
		return parentLovValueId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("cacheVersion",getCacheVersion());
		jsonContainer.put("lookupId",getLookupId());
		jsonContainer.put("parentLovValueId",getParentLovValueId());
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public LOV2ndLevelRequestArgument instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LOV2ndLevelRequestArgument retval = new LOV2ndLevelRequestArgument();
		retval.setCacheVersion( (String)jsonContainer.get("cacheVersion" ,  String.class.getName()));
		retval.setLookupId( (StrongTyped2ndLevelLOVID)jsonContainer.get("lookupId" ,  StrongTyped2ndLevelLOVID.class.getName()));
		retval.setParentLovValueId( (String)jsonContainer.get("parentLovValueId" ,  String.class.getName()));
		
		return retval; 
	}
}
