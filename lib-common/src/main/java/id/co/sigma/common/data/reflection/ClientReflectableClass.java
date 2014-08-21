package id.co.sigma.common.data.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * marker annotation, ini akan menandai class ini bisa di reflected di sisi client 
 * initial date : Mar 15, 2013
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 *@version $Id
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface ClientReflectableClass {

}
