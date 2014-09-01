package id.co.sigma.common.exception;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


/**
 * 
 * base class dengan stack trace serializing. class ini tidak implements 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class AbstractJSONSerializableException extends Exception implements IsSerializable , Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6411601195844758519L;


	/**
     * message dari string
     */
	protected String message ; 

    
    /**
     * stack trace
     **/
	protected String stackTraceAsString ; 
    
    
	public AbstractJSONSerializableException(){
	    	super("no Message");
	}
	    
	public AbstractJSONSerializableException(String message ) {
	        super(message); 
	        this.message = message ; 
	}
	public AbstractJSONSerializableException(Exception baseException ) {
	        super(baseException.getMessage()); 
	        this.message = baseException.getMessage() ; 

	        putStackTrace(baseException);
	}
	
	
	
	/**
	 * memindahkan exception ke dalam variable {@link #stackTraceAsString}
	 **/
	protected void putStackTrace (Throwable caught){
		stackTraceAsString =  BaseIsSerializableException.STACKTRACE_TO_STRING_WORKER.extractStackTraceToString(caught);
	}
	/**
     * message dari string
     */
    public String getMessage() {
		return message;
	}
	/**
     * message dari string
     */
	public void setMessage(String message) {
		this.message = message;
	}
	 
    /**
     * stack trace
     **/
	public String getStackTraceAsString() {
		return stackTraceAsString;
	}
	 
    /**
     * stack trace
     **/
	public void setStackTraceAsString(String stackTraceAsString) {
		this.stackTraceAsString = stackTraceAsString;
	}
	
	
	
	/**
	 * menaruh variable dari base ke dalam json container
	 **/
	protected void putMemberVariableToJson(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("message", getMessage());
		jsonContainerData.put("stackTraceAsString", getStackTraceAsString());
	}
	
	
	
	/**
	 * menaruh variable dari json ke dalam element 
	 * @param target target kemana data akan di taruh
	 * @param jsonContainerData json dari mana data akan di ambil
	 **/
	protected void fetchVariableFromJson( AbstractJSONSerializableException target ,   ParsedJSONContainer jsonContainerData) {
		target.setMessage(jsonContainerData.getAsString("message"));
		target.setStackTraceAsString(jsonContainerData.getAsString("stackTraceAsString"));
	}
}
