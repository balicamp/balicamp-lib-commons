package id.co.sigma.common.data;

import id.co.sigma.common.data.lov.StrongTypedCustomLOVID;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public enum CoreLibLookup implements StrongTypedCustomLOVID<CoreLibLookup>{
	
	
	/**
	 * group internalization
	 **/
	i18TextGroup("CORE-18N-GROUP"),
	approvalDefinitionTypes("core-approval:approval-definition-types");

	private String internalRepresentation ; 
	
	
	private CoreLibLookup (String code){
		this.internalRepresentation = code;
	}
	@Override
	public String getId() {
		return internalRepresentation;
	}

	@Override
	public String getModulGroupId() {
		return "SIGMA-global";
	}
	
	
	@Override
	public String toString() {
		
		return getModulGroupId() + "." + internalRepresentation;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("internalRepresentation", internalRepresentation);
		
		
	}
	@Override
	public CoreLibLookup instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		
		
		String key = jsonContainer.getAsString("internalRepresentation"); 
		
		if ( key== null|| key.isEmpty())
			return null ; 
		for ( CoreLibLookup scn :  CoreLibLookup.values()){
			if ( scn.getId().equals(key))
				return scn ; 
			
		}
		 
		return null;
	}
	

}
