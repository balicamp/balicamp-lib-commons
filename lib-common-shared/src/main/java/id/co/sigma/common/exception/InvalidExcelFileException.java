package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

public class InvalidExcelFileException extends AbstractJSONSerializableException implements IJSONFriendlyObject<InvalidExcelFileException> {
	private static final long serialVersionUID = 1588766687626580867L;
	
	private String fileType;
	
	public InvalidExcelFileException() {
	}

	public InvalidExcelFileException(String message, String fileType) {
		super();
		super.setMessage(message);
		this.fileType = fileType;
	}
	
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("fileType",getFileType());
		jsonContainer.put("message",getMessage());
	}
	@Override
	public InvalidExcelFileException instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		InvalidExcelFileException retval = new InvalidExcelFileException();
		retval.setMessage( (String)jsonContainer.get("message" ,  String.class.getName()));
		retval.setStackTraceAsString( (String)jsonContainer.get("stackTraceAsString" ,  String.class.getName()));
		return retval; 
	}

}
