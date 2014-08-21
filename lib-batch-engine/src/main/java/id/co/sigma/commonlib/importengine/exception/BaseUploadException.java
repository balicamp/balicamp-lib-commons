package id.co.sigma.commonlib.importengine.exception;

public abstract class BaseUploadException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4269462475282241698L;
	
	
	/**
	 * key resource bundle i18 untuk menjelaskan error 
	 **/
	protected String i18NKeyErrorMessage ;
	
	/**
	 * nama field yang bermasalah. ini untuk menyampaikan ke user field x bermasalah karena yy
	 **/
	protected String fieldName ;
	

	public BaseUploadException(String message ){
		super(message);
	}
	
	
	/**
	 * key resource bundle i18 untuk menjelaskan error 
	 **/
	public String getI18NKeyErrorMessage() {
		return i18NKeyErrorMessage;
	}
	
	/**
	 * nama field yang bermasalah. ini untuk menyampaikan ke user field x bermasalah karena yy
	 **/
	public String getFieldName() {
		return fieldName;
	}

}
