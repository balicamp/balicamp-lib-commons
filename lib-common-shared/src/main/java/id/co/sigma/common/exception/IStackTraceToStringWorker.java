package id.co.sigma.common.exception;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * interface untuk membongkar stack trace ke dalam string
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IStackTraceToStringWorker extends IsSerializable{
	
	
	/**
	 * worker untuk extract dari exception ke dalam string stack trace. ini berguna untuk debuging
	 **/
	public String extractStackTraceToString(Throwable exception); 

}
