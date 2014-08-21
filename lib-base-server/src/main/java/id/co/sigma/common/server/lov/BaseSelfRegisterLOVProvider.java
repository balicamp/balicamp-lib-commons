package id.co.sigma.common.server.lov;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * base class untuk self registered LOV provider. ini harus di set Lazy = false
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 22-Oct-2012
 **/
public abstract class BaseSelfRegisterLOVProvider extends BaseLOVProvider {

	@Autowired
	protected ISelfRegisterLovManager lovManager ; 
	
	
	@PostConstruct
	protected void postConstuct(){
		lovManager.register(this);
	}

}
