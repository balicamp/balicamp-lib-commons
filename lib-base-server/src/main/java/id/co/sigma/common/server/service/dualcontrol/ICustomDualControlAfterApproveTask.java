package id.co.sigma.common.server.service.dualcontrol;



import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.exception.DataValidationException;

/**
 * interface class handler after approve item. ini di pergunakan kalau di perlukan pekerjaan tambahan setelah item di approve
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomDualControlAfterApproveTask<DATA extends DualControlEnabledData<?, ?>> {
	
	
	
	
	
	/**
	 * validasi data sebelum data di insert ke dalam database
	 * @param rawData data json mentahan
	 * @param toBeApprovedData data yang akan di insert
	 */
	public void runValidationBeforeAddNewData ( CommonDualControlContainerTable rawData  ,  DATA toBeApprovedData) throws DataValidationException ;
	
	
	
	
	
	/**
	 * validasi sebelum data di edit dari data base
	 * @param rawData data json
	 * @param toBeApprovedData data yang akan di edit
	 */
	public void runValidationBeforeEditExistingData ( CommonDualControlContainerTable rawData  ,  DATA toBeApprovedData) throws DataValidationException ;
	
	/**
	 * task setelah data di approve add new. task tambahan di masukan di sini 
	 * @param approvedData data yang di approve
	 */
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void afterApproveCreateNewData ( CommonDualControlContainerTable rawData  ,  DATA approvedData , String approverUserId ) throws Exception;
	
	
	
	
	
	
	/**
	 * ini dalam kasus data di di delete. task tambahan di handle di sini
	 **/
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void afterApproveDeleteData (CommonDualControlContainerTable rawData  ,  DATA approvedData , String approverUserId ) throws Exception;
	
	
	 
	/**
	 * ini dalam kasus data di di edit. task tambahan di handle di sini
	 **/
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public void afterApproveEditData (CommonDualControlContainerTable rawData  ,  DATA approvedData , String approverUserId ) throws Exception;
	
	
	/**
	 * class yang di handle custom task
	 **/
	public Class<DATA> getHandledClass() ; 
	
	
	/**
	 * flag virtual table. kalau true berarti tidak di perlukan aktivitas save ke table tujuan, karena table tujuan tidak ada
	 */
	boolean isUseVirtualTable () ; 
	
	/**
	 * worker untuk membaca existing data 
	 */
	public DATA loadExistingData (Object id ) ;
	
	
	
	/**
	 * kalau di perlukan tindakan custom, maka masukan di sini proses untuk update data
	 * @param data data yang di modifikasi
	 */
	public void saveDataModification ( Object data)  throws Exception; 
	

}
