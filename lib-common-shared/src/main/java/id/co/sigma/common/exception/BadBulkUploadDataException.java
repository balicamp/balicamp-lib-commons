package id.co.sigma.common.exception;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * exception pada saat data di kirimkan rusak
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class BadBulkUploadDataException extends BaseIsSerializableException implements IJSONFriendlyObject<BadBulkUploadDataException>{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3661218262912904820L;
	/**
	 * invalid message yang di kirim
	 */
	private String [] invalidDataMessages ; 
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("message", message);
		jsonContainer.appendToArray(  "invalidDataMessages", invalidDataMessages);
	}

	@Override
	public BadBulkUploadDataException instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		BadBulkUploadDataException retval = new BadBulkUploadDataException() ; 
		retval.message =jsonContainer.getAsString( "message"); 
		retval.invalidDataMessages = jsonContainer.getAsStringArray("invalidDataMessages"); 
		return retval;
	}
	
	/**
	 * data message yang di nyatakan tidak valid dalam proses
	 */
	public void setInvalidDataMessages(String[] invalidDataMessages) {
		this.invalidDataMessages = invalidDataMessages;
	}
	/**
	 * data message yang di nyatakan tidak valid dalam proses
	 */
	public String[] getInvalidDataMessages() {
		return invalidDataMessages;
	}

}
