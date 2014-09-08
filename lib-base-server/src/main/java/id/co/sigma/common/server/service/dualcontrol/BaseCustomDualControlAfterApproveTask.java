package id.co.sigma.common.server.service.dualcontrol;

import java.io.Serializable;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.SimpleDualControlData;
import id.co.sigma.common.exception.DataValidationException;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.service.AbstractService;

/**
 * base class untuk custom handler
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseCustomDualControlAfterApproveTask<DATA extends SimpleDualControlData<?>> extends AbstractService implements ICustomDualControlAfterApproveTask<DATA> , InitializingBean{

	
	private static final Logger logger = LoggerFactory.getLogger(BaseCustomDualControlAfterApproveTask.class); 
	
	@Autowired
	private ICustomDualControlAfterApproveTaskManager manager ; 
	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ; 
	
	@SuppressWarnings("rawtypes")
	@Override
	public void afterPropertiesSet() throws Exception {
		manager.register(this);
		if(this instanceof ICustomBulkDualControlAfterApproveTask)
			manager.register((ICustomBulkDualControlAfterApproveTask) this);
	}
	
	
	/**
	 * worker untuk membaca existing data 
	 */
	@SuppressWarnings("unchecked")
	public DATA loadExistingData (Object id ){
		if ( id == null || !(id instanceof BigInteger))
			return null ; 
		
		try {
			return (DATA)generalPurposeDao.get(getHandledClass(),(BigInteger) id);
		} catch (Exception e) {
			logger.error("gagal membaca data : " + getHandledClass() + " id : " + id +", error : " + e.getMessage()  );
			return null ;
		} 
	}
	
	
	
	@Override
	public void runValidationBeforeAddNewData(
			CommonDualControlContainerTable rawData, DATA toBeApprovedData)
			throws DataValidationException {
		// tidak ada yang di lakukan
		
	}
	
	
	@Override
	public void runValidationBeforeEditExistingData(
			CommonDualControlContainerTable rawData, DATA toBeApprovedData)
			throws DataValidationException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void saveDataModification(Object data) throws Exception{
		if ( data == null || !(data instanceof Serializable))
				return  ; 
		generalPurposeDao.update((Serializable)data);
		
	}
	
	
	
}
