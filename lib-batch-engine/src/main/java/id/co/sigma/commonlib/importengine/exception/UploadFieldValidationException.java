package id.co.sigma.commonlib.importengine.exception;




/**
 * valdiation failure exception
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class UploadFieldValidationException extends BaseUploadException{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7708173365994124250L;

	public UploadFieldValidationException(String message,String fieldName , String i18NKeyForErrorMessage) {
		super(message);
		this.i18NKeyErrorMessage= i18NKeyForErrorMessage ; 
		this.fieldName = fieldName ; 
		
	}

}
