package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * exception kalau login sudah habis. user harus login kembali
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class LoginExpiredException extends BaseIsSerializableException implements IJSONFriendlyObject<LoginExpiredException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288061439889309316L;
	
	
	public LoginExpiredException(){
		super( "Login anda sudah expired, anda harus login ulang"); 
	}


	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", this.message);
		jsonContainerData.put("fullStackTrace", this.fullStackTrace);
	}


	@Override
	public LoginExpiredException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		LoginExpiredException retval  = new LoginExpiredException(); 
		retval.setFullStackTrace(jsonContainer.getAsString("fullStackTrace")); 
		retval.setMessage(jsonContainer.getAsString("message")); 
		
		return retval;
	}

}
