package id.co.sigma.common.server.dao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * base class custom provider
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseCustomJoinHqlProvider<T> implements ICustomJoinHqlProvider<T> , InitializingBean{
	
	
	@Autowired
	private ICustomJoinHqlProviderManager customJoinHqlProviderManager ;

	@Override
	public void afterPropertiesSet() throws Exception {
		customJoinHqlProviderManager.register(this); 
		
	}

	 
}
