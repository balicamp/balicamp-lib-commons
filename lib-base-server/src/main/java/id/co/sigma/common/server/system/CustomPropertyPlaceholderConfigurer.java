package id.co.sigma.common.server.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * property place holder configurer tweak  , property di expose sebagai hash map
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class CustomPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	
	
	 private  Map<String, String> propertiesMap;
	    // Default as in PropertyPlaceholderConfigurer
	 private int springSystemPropertiesMode = SYSTEM_PROPERTIES_MODE_FALLBACK;
	    
	 @Override
	    public void setSystemPropertiesMode(int systemPropertiesMode) {
	        super.setSystemPropertiesMode(systemPropertiesMode);
	        springSystemPropertiesMode = systemPropertiesMode;
	    }

	    @Override
	    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
	        super.processProperties(beanFactory, props);

	        propertiesMap = new HashMap<String, String>();
	        for (Object key : props.keySet()) {
	            String keyStr = key.toString();
	            String valueStr = resolvePlaceholder(keyStr, props, springSystemPropertiesMode);
	            propertiesMap.put(keyStr, valueStr);
	        }
	    }

	    
	    /**
	     * akses value dengan nama properties
	     */
	    public  String getProperty(String name) {
	        return propertiesMap.get(name).toString();
	    }
	    
	    
	    /**
	     * akses langsung ke map of properties
	     */
	    public Map<String, String> getRawProperties () {
	    	return propertiesMap ; 
	    }
	    
	    
}
