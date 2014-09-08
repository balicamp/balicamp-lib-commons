package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



/**
 * exception wrapper, ini memwrap exception lain yang tidak bisa lewat dalam serialization proccess
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class CommonWrappedSerializableException extends BaseIsSerializableException implements IJSONFriendlyObject<CommonWrappedSerializableException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 313195776972031183L;
	
	
	
	/**
	 * stack trace asli 
	 **/
	private String originalStackTrace  ;
	
	public CommonWrappedSerializableException() {
		super();
	}
	
	public CommonWrappedSerializableException(String friendlyMessage, Throwable actualError){
		super(friendlyMessage  , actualError); 
		
	}

	/**
	 * stack trace asli 
	 **/
	public String getOriginalStackTrace() {
		return originalStackTrace;
	}

	/**
	 * stack trace asli 
	 **/
	public void setOriginalStackTrace(String originalStackTrace) {
		this.originalStackTrace = originalStackTrace;
	}
	
	
	protected String stackTraceToString(Throwable e) {
	    StringBuilder sb = new StringBuilder();
	    for (StackTraceElement element : e.getStackTrace()) {
	        sb.append(element.toString());
	        sb.append("\n");
	    }
	    return sb.toString();
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("fullStackTrace", getFullStackTrace());
		jsonContainerData.put("originalStackTrace", getOriginalStackTrace());
	}

	@Override
	public CommonWrappedSerializableException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		CommonWrappedSerializableException retval = new CommonWrappedSerializableException();
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace"));
		retval.setMessage(jsonContainer.getAsString("message"));
		retval.setOriginalStackTrace(jsonContainer.getAsString("originalStackTrace"));
		return null;
	}

}
