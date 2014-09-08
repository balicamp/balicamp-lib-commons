package id.co.sigma.common.server.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.server.util.IObjectCleanUp;

/**
 * base object untuk object cleaner
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseObjectCleaner<POJO> implements IObjectCleanUp<POJO> , InitializingBean{
	
	
	@Autowired
	private IObjectCleanUpManager cleanUpManager ; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		cleanUpManager.registerObjectCleaner(this); 
		
	}

}
