package id.co.sigma.common.server.service.dualcontrol;

import org.springframework.beans.factory.annotation.Autowired;

import id.co.sigma.common.data.SystemParamTableConfigurationData;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.server.service.system.ICommonSystemService;

/**
 * base class untuk handle approval/ approval untuk item berbasis system parameter driven table
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseSystemParameterDrivenConfigDrivenDataAfterApproveTask<DATA extends SystemParamTableConfigurationData<?, ?>	> extends BaseCustomDualControlAfterApproveTask<DATA>{
	
	@Autowired
	ICommonSystemService commonSystemService ; 
	@Override
	public void afterApproveCreateNewData(
			CommonDualControlContainerTable rawData, DATA approvedData,
			String approverUserId) throws Exception {
		commonSystemService.saveConfiguration(approvedData);
	}
	
	@Override
	public void afterApproveDeleteData(CommonDualControlContainerTable rawData,
			DATA approvedData, String approverUserId) throws Exception {
		commonSystemService.saveConfiguration(approvedData);
		
	}
	@Override
	public void afterApproveEditData(CommonDualControlContainerTable rawData,
			DATA approvedData, String approverUserId) throws Exception {
		commonSystemService.saveConfiguration(approvedData);
	}
	
	@Override
	public boolean isUseVirtualTable() {
		return true;
	}
	
	
}
