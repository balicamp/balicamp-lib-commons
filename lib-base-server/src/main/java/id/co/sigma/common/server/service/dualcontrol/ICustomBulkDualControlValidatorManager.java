package id.co.sigma.common.server.service.dualcontrol;

import id.co.sigma.common.data.app.DualControlEnabledData;

/**
 * manager custom bulk validator. ini spool validator
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomBulkDualControlValidatorManager  {
	
	/**
	 * register validator ke dalam container
	 */
	public <DATA extends DualControlEnabledData<?, ?>> void registerValidatorBulk ( ICustomBulkDualControlValidator<DATA> validator )  ; 
	
}
