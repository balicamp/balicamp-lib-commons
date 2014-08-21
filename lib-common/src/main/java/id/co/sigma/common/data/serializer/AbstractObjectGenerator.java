package id.co.sigma.common.data.serializer;

import id.co.sigma.common.util.IObjectGenerator;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class AbstractObjectGenerator implements IObjectGenerator {
	
	
	
	/**
	 * class yang di generate oleh generator
	 **/
	public abstract Class<?> [] generatedClass () ; 
	
	

}
