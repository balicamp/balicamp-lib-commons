package id.co.sigma.common.exception;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;




/**
 * base exception class, yang bisa di pass ke client. ini di pergunakan untuk throw business exceptionke client
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public abstract class BaseIsSerializableException extends RuntimeException implements IsSerializable ,Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6413251754517851849L;
	/**
	 * worker untuk membongkar dari stack trace ke dalam string
	 **/
	public static IStackTraceToStringWorker STACKTRACE_TO_STRING_WORKER = new IStackTraceToStringWorker() {
		
		@Override
		public String extractStackTraceToString(Throwable exception) {
			
			return null;
		}
	};  
	protected String message ;
	
	
	/**
	 * full stack trace
	 **/
	protected String fullStackTrace ; 
	public BaseIsSerializableException(){
		// ini untuk support serialization
		super(); 
	}
	public BaseIsSerializableException(String friendlyMessage){
		super(friendlyMessage);
		setMessage(friendlyMessage);
	}
	
	public BaseIsSerializableException(String friendlyMessage, Throwable actualError){
		
		super(friendlyMessage);
		if (STACKTRACE_TO_STRING_WORKER!=null )
			this.fullStackTrace = STACKTRACE_TO_STRING_WORKER.extractStackTraceToString(actualError);
		this.message = friendlyMessage ; 
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFullStackTrace() {
		return fullStackTrace;
	}
	
	public void setFullStackTrace(String fullStackTrace) {
		this.fullStackTrace = fullStackTrace;
	}

}
