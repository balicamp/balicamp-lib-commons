package id.co.sigma.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * method yang di secure masalah double submit
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DoubleSubmitSecuredMethod {
	
	
	 
	

}
