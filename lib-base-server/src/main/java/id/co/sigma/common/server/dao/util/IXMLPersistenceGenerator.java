package id.co.sigma.common.server.dao.util;

import java.lang.reflect.Field;



/**
 * worker untuk generate persistence.xml
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface IXMLPersistenceGenerator {
	
	/**
	 * parser untuk memproses field pojo
	 **/
	public String generateXMLComplience ( Field pojoField  ); 
	

}
