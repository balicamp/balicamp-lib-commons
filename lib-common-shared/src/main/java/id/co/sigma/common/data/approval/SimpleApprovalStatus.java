package id.co.sigma.common.data.approval;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * simple approval status. ini unutk approval sederhana, <br/>
 * 
 * <ol>
 * <li>Menunggu approval</li>
 * <li>di approve</li>
 * <li>di tolak</li>
 * </ol>
 * 
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public enum SimpleApprovalStatus implements IJSONFriendlyObject<SimpleApprovalStatus>{
	WAITING_APPROVAL("WAITING_APPROVAL") , 
	APPROVED("APPROVED"), 
	REJECTED("REJECTED");
	
	private String internalCode ; 
	
	private SimpleApprovalStatus(String internalCode){
		this.internalCode = internalCode ; 
	}
	@Override
	public String toString() {
		return internalCode;
	}
	
	
	
	
	/**
	 * generate enum dari string
	 **/
	public static SimpleApprovalStatus instantiateFromString ( String rawString ){
		if ( rawString== null||rawString.length()==0)
			return null ; 
		for ( SimpleApprovalStatus scn : SimpleApprovalStatus.values()){
			if(scn.internalCode.equals(rawString))
				return scn ; 
		}
		return null ; 
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("internalCode", internalCode);
		
	}
	@Override
	public SimpleApprovalStatus instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		String code = jsonContainer.getAsString("internalCode");
		return instantiateFromString(code); 
		
	}

}
