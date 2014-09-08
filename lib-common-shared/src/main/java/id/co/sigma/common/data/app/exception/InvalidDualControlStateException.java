package id.co.sigma.common.data.app.exception;

import id.co.sigma.common.data.app.DualControlApprovalStatusCode;
import id.co.sigma.common.exception.BaseIsSerializableException;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * exception invalid state
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class InvalidDualControlStateException extends BaseIsSerializableException implements IJSONFriendlyObject<InvalidDualControlStateException>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7101847361337477064L;
	/**
	 * status yang di ijinkan untuk di proses
	 **/
	private DualControlApprovalStatusCode [] expectedStatuses ;
	/**
	 * status code data saat ini
	 **/
	private DualControlApprovalStatusCode  curentDataStatus ;
	
	public InvalidDualControlStateException() {
		super();
	}
	
	public InvalidDualControlStateException(String message , DualControlApprovalStatusCode [] expectedStatuses , DualControlApprovalStatusCode  curentDataStatus){
		super(message);
		this.expectedStatuses = expectedStatuses ; 
		this.curentDataStatus = curentDataStatus ; 
	}
	
	
	/**
	 * status code data saat ini
	 **/
	public DualControlApprovalStatusCode getCurentDataStatus() {
		return curentDataStatus;
	}
	/**
	 * status code data saat ini
	 **/
	public void setCurentDataStatus(DualControlApprovalStatusCode curentDataStatus) {
		this.curentDataStatus = curentDataStatus;
	}
	/**
	 * status yang di ijinkan untuk di proses
	 **/
	public DualControlApprovalStatusCode[] getExpectedStatuses() {
		return expectedStatuses;
	}
	/**
	 * status yang di ijinkan untuk di proses
	 **/
	public void setExpectedStatuses(DualControlApprovalStatusCode[] expectedStatuses) {
		this.expectedStatuses = expectedStatuses;
	} 
	
	@Override
	public InvalidDualControlStateException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		InvalidDualControlStateException retval = new InvalidDualControlStateException();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		if ( jsonContainer.contain("expectedStatuses")){
			String[] stts =  jsonContainer.getAsStringArray( "expectedStatuses");
			
			if ( stts!= null && stts.length > 0 ) { 
				retval.expectedStatuses = new DualControlApprovalStatusCode[stts.length]; 
				for  ( int i = 0 ; i < stts.length ; i++){
					retval.expectedStatuses[i]  = DualControlApprovalStatusCode.generateFromRawString(stts[i]); 
				}
			}
		}
		if ( jsonContainer.contain("curentDataStatus")){
			retval.curentDataStatus = DualControlApprovalStatusCode.generateFromRawString(jsonContainer.getArrayTypeFQCN("curentDataStatus")); 
		}
		return retval;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		if ( expectedStatuses!= null && expectedStatuses.length> 0 ){
			String[] arr = new String[expectedStatuses.length];
			for ( int i = 0 ; i < expectedStatuses.length; i++){
				arr[i] = expectedStatuses[i].toString();
			}
			jsonContainerData.appendToArray( "expectedStatuses", arr);
		}
		if ( curentDataStatus!= null){
			jsonContainerData.put("curentDataStatus", curentDataStatus.toString());
		}

	}
	

}
