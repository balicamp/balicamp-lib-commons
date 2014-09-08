package id.co.sigma.common.security.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * exception di throw kalau user tidak di temukan dalam database
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class UserNotExistException extends Exception implements IJSONFriendlyObject<UserNotExistException>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6856346217783132387L;


	private String message ; 
	
	 
	public UserNotExistException(){
		super(); 
	}
	
	
	public UserNotExistException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("message", message);
		
		
	}


	@Override
	public UserNotExistException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		UserNotExistException retval = new UserNotExistException(); 
		retval.message = jsonContainer.getAsString("message"); 
		 
		return retval;
	}
	
	
}
