package id.co.sigma.commonlib.importengine.exception;




/**
 * ini kalau data salah tipe
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class DataTypeViolationException extends UploadFieldConvertionException{

	




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * tipe data yang di perlukan.  ini menjelaskan field x seharusnya is nya apa
	 **/
	private String expcectedDataType ; 

	public DataTypeViolationException(String message, String fieldName,
			String rawStringValue, String i18nKeyErrorMessage , String expectedDataType) {
		super(message, fieldName, rawStringValue, i18nKeyErrorMessage);
		this.expcectedDataType = expectedDataType ;
	}
	
	
	/**
	 * tipe data yang di perlukan.  ini menjelaskan field x seharusnya is nya apa
	 **/
	public String getExpcectedDataType() {
		return expcectedDataType;
	}
	

}
