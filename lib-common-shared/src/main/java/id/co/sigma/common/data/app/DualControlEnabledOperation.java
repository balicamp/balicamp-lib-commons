package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



/**
 * operasi dalam dual control enable data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public enum DualControlEnabledOperation implements IJSONFriendlyObject<DualControlEnabledOperation> {
	
	
	/**
	 * create data baru
	 **/
	INSERT ("insert"), 
	
	/**
	 * update existing data
	 **/
	UPDATE("update")  , 
	/**
	 * hapus existing data ( hanya flag hapus) 
	 **/
	DELETE("delete");
	
	
	private String internalCode  ; 
	
	private DualControlEnabledOperation(String internalCode){
		this.internalCode = internalCode ; 
	}

	@Override
	public String toString() {
		return internalCode;
	}
	
	
	public static DualControlEnabledOperation instantiateFromString ( String internalCode){
		for (DualControlEnabledOperation scn  : DualControlEnabledOperation.values()){
			if ( scn.toString().equals(internalCode))
				return scn ; 
		}
		return null ; 
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("internalCode", toString());
		
	}
	
	

	@Override
	public DualControlEnabledOperation instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		String code = jsonContainer.getAsString("internalCode"); 
		
		return  DualControlEnabledOperation.instantiateFromString( code);
	}
	

}
