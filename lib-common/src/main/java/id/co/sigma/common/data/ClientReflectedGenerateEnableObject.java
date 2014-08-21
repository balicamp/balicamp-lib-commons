package id.co.sigma.common.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * marker interface untuk object yang bisa di instantiate di sisi client. ini tidak untuk di pergunakan pada class generic. mohon pergunak pada actual class saha
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Target(value=ElementType.TYPE)
public @interface ClientReflectedGenerateEnableObject {

}

