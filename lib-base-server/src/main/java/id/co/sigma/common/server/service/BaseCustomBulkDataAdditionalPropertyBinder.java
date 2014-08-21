package id.co.sigma.common.server.service;

import id.co.sigma.common.data.app.DualControlEnabledData;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * base class untuk custom additional data loader bulk
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseCustomBulkDataAdditionalPropertyBinder<DATA extends DualControlEnabledData<DATA, ?>> implements ICustomBulkDataAdditionalPropertyBinder<DATA> , InitializingBean{

	@Autowired
	ICustomBulkDataAdditionalPropertyBinderManager additionalPropertyBinderManager ; 
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		additionalPropertyBinderManager.registerBulkDataBinder(this);
	}
}
