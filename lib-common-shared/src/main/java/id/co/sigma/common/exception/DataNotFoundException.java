package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;




/**
 * common error dalam kasus data not found
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class DataNotFoundException extends BaseIsSerializableException implements IJSONFriendlyObject<DataNotFoundException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8953633400074971559L;
	
	public DataNotFoundException(){}
	public DataNotFoundException(String message){
		super(); 
		setMessage(message);
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
	}
	
	@Override
	public DataNotFoundException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		DataNotFoundException retval = new DataNotFoundException();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		return retval;
	}
}
