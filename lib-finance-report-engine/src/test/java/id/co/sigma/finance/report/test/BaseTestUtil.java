package id.co.sigma.finance.report.test;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;



/**
 * base class untuk tester tools
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public abstract class BaseTestUtil {
	
	
	public String readFromClassPathAsString ( String classpathPath ) {
		try {
			
			return IOUtils.toString(  new ClassPathResource(classpathPath).getInputStream() ) ;
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null ; 
		}
		
		
		 
	}

}
