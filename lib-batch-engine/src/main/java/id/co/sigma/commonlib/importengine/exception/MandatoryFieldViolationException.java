package id.co.sigma.commonlib.importengine.exception;



/**
 * ini kalau field mandatory ndak di isi
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * 
 **/
public class MandatoryFieldViolationException extends UploadFieldConvertionException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8053898835373546274L;
	
	


	public MandatoryFieldViolationException(String message, String fieldName,
			String rawStringValue, String i18nKeyErrorMessage) {
		super(message, fieldName, rawStringValue, i18nKeyErrorMessage);
	}

}
