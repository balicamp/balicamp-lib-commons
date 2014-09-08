package id.co.sigma.common.server.util;

import java.io.PrintWriter;
import java.io.StringWriter;




/**
 * util helper untuk handler masalah terkait exception handling
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public class ExceptionRelatedUtils {
	
	
	
	private static ExceptionRelatedUtils instance ; 
	
	
	
	public String getStackTraceAsString (Throwable caught ){
		StringWriter sw = new StringWriter(); 
		caught.printStackTrace(new PrintWriter( sw));
		return sw.toString() ; 
		
	}
	
	
	
	
	public static ExceptionRelatedUtils getInstance() {
		if ( instance == null )
			instance = new ExceptionRelatedUtils(); 
		return instance;
	}

}
