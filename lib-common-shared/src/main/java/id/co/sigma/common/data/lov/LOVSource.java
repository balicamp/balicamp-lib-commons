package id.co.sigma.common.data.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;


/**
 * sumber dari LOV.
 **/
public enum LOVSource implements Serializable, IJSONFriendlyObject<LOVSource>{
	
	/**
	 * langsung dari table lendor_data.m_lookup_details
	 **/
	directFromLookupTable("DIRECT") , 
	/**
	 * custom di provide oleh user
	 **/
	useCustomProvider("CUSTOM") ;
	
	private String instance ; 
	
	
	private LOVSource(String code) {
		this.instance = code ; 
	}
	
	@Override
	public String toString() {
		return instance;
	}
	
	
	
	/**
	 * generate LOV dari string
	 **/
	public static LOVSource instantiateFromString (String rawString) {
		if ( rawString==null|| rawString.isEmpty())
			return null ;
		for (LOVSource scn : values() ){
			if ( scn.instance.equals(rawString))
				return scn;
		}
		return null ;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("instance", instance);
		
	}

	@Override
	public LOVSource instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		String key = jsonContainer.getAsString("instance");
		if ( key== null||key.isEmpty())
			return null ; 
		for ( LOVSource scn : LOVSource.values()){
			if ( scn.instance.equals(key))
				return scn  ; 
		}
		return null;
	}
}
