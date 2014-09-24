package id.co.sigma.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValidator {
	
	/**
	 * Panjang maximum field.
	 * 
	 * @return pangjang kolom database
	 */
	int length();
	
	/**
	 * Nama kolom bisnis.
	 * 
	 * @return 
	 */
	String businessColumnName();
	
	/**
	 * Flag untuk mandatory column
	 * 
	 * @return
	 */
	boolean mandatory() default false;

}
