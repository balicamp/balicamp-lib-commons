package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * ini kalau LOV provider tidak ada. ini agar developer aware terhadap masalah ini
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class UnknownLookupValueProviderException extends BaseIsSerializableException implements IJSONFriendlyObject<UnknownLookupValueProviderException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 854604166888079653L;
	
	/**
	 * ID dari lookup yang tidak di temukan
	 **/
	private String lookupValueId ; 
	
	
	public UnknownLookupValueProviderException(){
		super();
	}
	
	public UnknownLookupValueProviderException(String friendlyMessage,String lookupValueId){
		super(friendlyMessage); 
		this.lookupValueId=lookupValueId; 
	}
	/**
	 * ID dari lookup yang tidak di temukan
	 **/
	public void setLookupValueId(String lookupValueId) {
		this.lookupValueId = lookupValueId;
	}
	/**
	 * ID dari lookup yang tidak di temukan
	 **/
	public String getLookupValueId() {
		return lookupValueId;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("lookupValueId", getLookupValueId());
	}

	@Override
	public UnknownLookupValueProviderException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		UnknownLookupValueProviderException retval = new UnknownLookupValueProviderException();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		retval.setLookupValueId(jsonContainer.getAsString("lookupValueId"));
		return retval;
	}
	
	

}
