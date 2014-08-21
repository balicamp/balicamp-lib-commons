package id.co.sigma.commonlib.importengine.exception;



/**
 * ini kalau data tidak berada dalam range yang di ijinkan
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a> 
 * @version $Id
 **/
public class StringNotAvaliableOnMapperException extends UploadFieldConvertionException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringNotAvaliableOnMapperException(String message,
			String fieldName, String rawStringValue, String i18nKeyErrorMessage) {
		super(message, fieldName, rawStringValue, i18nKeyErrorMessage);
	}
}
