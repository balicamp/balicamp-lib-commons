package id.co.sigma.common.server.service.dualcontrol;

/**
 * manager of custom dual control handler
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomDualControlAfterApproveTaskManager {
	
	
	/**
	 * register custom handler
	 */
	void register ( ICustomDualControlAfterApproveTask< ?> handler);
	
	
	void register ( ICustomDualControlSubmitValidator<?>  validator ); 
	
	
	/**
	 * register handler bulk
	 */
	void register ( ICustomBulkDualControlAfterApproveTask<?> handler) ; 

}
