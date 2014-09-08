package id.co.sigma.commonlib.base.serializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;





/**
 * marker field di kirim ke job parameter. 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SendToParameterField {

	
	/**
	 * key untuk menaruh dalam job parameter
	 **/
	String key() ;
	
	
	
	/**
	 * class converter dari dan ke
	 **/
	Class<? extends ObjectToJobParamSerializer<?>> converter () default AutoDetectToJobParamSerializer.class; 
	
	
}
