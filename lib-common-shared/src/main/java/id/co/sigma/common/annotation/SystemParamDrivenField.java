package id.co.sigma.common.annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation untuk marker , field dalam java di ambil dari mana
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemParamDrivenField {
	
	
	/**
	 * key dari field. ini untuk ambil ke dalam system parameter
	 */
	public String key() ; 
	
	
	

}
