package id.co.sigma.common.server.util.strings.fixedlength;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface FixedLengthField {
	
	
	/**
	 * urutan field dalam fixed length. di posisi ke berapa
	 */
	int index () ; 
	
	
	
	/**
	 * berapa panjang dari string yang di inginkan
	 */
	int stringMaxLength();  
	
	
	
	
	
	
	/**
	 * serializer class
	 */
	Class<? extends CustomFixedLengthSerializer> serializer() ; 

}
