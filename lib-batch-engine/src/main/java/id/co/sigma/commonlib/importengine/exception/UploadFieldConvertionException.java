package id.co.sigma.commonlib.importengine.exception;



/**
 * exception kalah upload gagal konversi field
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * 
 **/
public class UploadFieldConvertionException extends BaseUploadException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5018292322847034468L;
	
	
	
	
	
	
	/**
	 * string yang gagal di konversi
	 **/
	private String rawStringValue ; 
	
	/**
	 * constructor
	 * @param message message error message
	 * @param fieldName nama field yang bermasalah
	 * @param rawStringValue data yang gagal di konversi
	 * @param i18NKeyErrorMessage i18 key untuk message. mandatory violation pakai apa, invalid type pakai apa etc
	 **/
	public UploadFieldConvertionException(String message , String fieldName , String rawStringValue , String i18NKeyErrorMessage){
		super(message);
		this.fieldName = fieldName ; 
		this.rawStringValue = rawStringValue; 
		this.i18NKeyErrorMessage=i18NKeyErrorMessage;
	}
	
	
	
	

	/**
	 * string yang gagal di konversi
	 **/
	public String getRawStringValue() {
		return rawStringValue;
	}

	/**
	 * string yang gagal di konversi
	 **/
	public void setRawStringValue(String rawStringValue) {
		this.rawStringValue = rawStringValue;
	}
	

}
