package id.co.sigma.common.server.service.dualcontrol;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * abstract class untuk menghandle proses validasi untuk field-field yang tidak boleh di update. ini bersesuain dengan form editor.
 * beberapa field memang tidak di ijinkan untuk di update, termasuk dalam proses batch 
 */
public abstract class BaseCustomBulkDualControlNotUpdatableValidator<T>  implements ICustomBulkDualControlNotUpdatableValidator<T>, 
	InitializingBean{
	
	@Autowired
	private ICustomBulkDualControlFieldRegister bulkDualControlFieldRegister;

	@Override
	public void afterPropertiesSet() throws Exception {
		bulkDualControlFieldRegister.registerBulkValidator(this);
	}
}
