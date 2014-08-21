package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * exception kalau token tidak sesuai. ntah karena token tidak ada, atau token sudah di pergunakan sebelumnya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class InvalidTokenException extends BaseIsSerializableException implements IJSONFriendlyObject<InvalidTokenException>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4778869919780684049L;
	
	
	/**
	 * token yang expired
	 */
	private String expiredToken ; 
	
	
	
	public InvalidTokenException(){
		super("Token sudah pernah di pergunakan"); 
	}
	
	
	public InvalidTokenException( String message , String invalidToken ){
		super(message); 
		this.expiredToken = invalidToken ; 
	}
	
	/**
	 * token yang expired
	 */
	public String getExpiredToken() {
		return expiredToken;
	}
	/**
	 * token yang expired
	 */
	public void setExpiredToken(String expiredToken) {
		this.expiredToken = expiredToken;
	}
	
	@Override
	public InvalidTokenException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		InvalidTokenException retval = new InvalidTokenException() ;
		retval.message = jsonContainer.getAsString("message"); 
		retval.expiredToken = jsonContainer.getAsString("expiredToken") ; 
		return retval;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("expiredToken", this.expiredToken);
		jsonContainerData.put("message", message);
		
		
	}

}
