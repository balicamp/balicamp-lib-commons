package id.co.sigma.common.server.service.dualcontrol;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.sigma.common.data.app.DualControlEnabledData;

/**
 * interface custom approval untuk data yang tipe nya bulk. ini di pisahkan dari {@link ICustomDualControlAfterApproveTask} ,untuk menghindari kalau implementasi bulk berbeda dengan tipe standard 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomBulkDualControlAfterApproveTask<DATA extends DualControlEnabledData<?, ?>> {

	/**
	 * task setelah data di approve add new. task tambahan di masukan di sini 
	 * @param approvedData data yang di approve
	 */
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void approveCreateNewData ( DATA approvedData , String approverUserId ) throws Exception;
	
	
	/**
	 * ini dalam kasus data di di edit. task tambahan di handle di sini
	 **/
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void approveEditData ( DATA approvedData , String approverUserId ) throws Exception;
	
	
	/**
	 * class yang di handle custom task
	 **/
	public Class<DATA> getHandledClass() ; 
	
	
	
}
