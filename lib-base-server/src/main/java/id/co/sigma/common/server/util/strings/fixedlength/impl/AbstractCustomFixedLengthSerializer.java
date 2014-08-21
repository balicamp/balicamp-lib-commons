package id.co.sigma.common.server.util.strings.fixedlength.impl;

import id.co.sigma.common.server.util.strings.fixedlength.CustomFixedLengthSerializer;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class AbstractCustomFixedLengthSerializer implements CustomFixedLengthSerializer{

	
	
	/**
	 * membuat repeated string
	 */
	protected String makeRepeatedString ( char repaeted , int length ){
		StringBuffer retval = new StringBuffer() ; 
		for ( int i = 0 ; i < length ; i++){
			retval.append(repaeted); 
		}
		return retval.toString(); 
	}
}
