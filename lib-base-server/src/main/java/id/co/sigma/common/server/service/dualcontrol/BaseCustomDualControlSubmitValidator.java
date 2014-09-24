package id.co.sigma.common.server.service.dualcontrol;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

/**
 * base class untuk validator
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseCustomDualControlSubmitValidator<DATA extends DualControlEnabledData<?, ?>> implements ICustomDualControlSubmitValidator<DATA> , InitializingBean{

	@Autowired
	private ICustomDualControlAfterApproveTaskManager manager ; 
	
	
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		manager.register(this); 
	}
	
	
	
	
	/**
	 * 
	 */
	protected DATA getDataFromJSONString (String jsonString) throws Exception{
		DATA sample = BeanUtils.instantiate(getValidatedType()); 
		ParsedJSONContainer c =  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON(jsonString);
		
		return (DATA) sample.instantiateFromJSON(c); 
		
		
	}
}
