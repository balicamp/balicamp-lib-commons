package id.co.sigma.common.data;

import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.SystemSimpleParameter;
import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.SharedServerClientLogicManager;

/**
 * wrapper untuk data hasil konfigurasi item yang di keep dalam table m_system_parameters  {@link SystemSimpleParameter}.
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@SuppressWarnings("rawtypes")
public class AppConfigurationDrivenDetaiResultHolder<D extends SystemParamDrivenClass<? , ?>> implements IJSONFriendlyObject<AppConfigurationDrivenDetaiResultHolder> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8425091354672034089L;


	/**
	 * data yang masih menunggu approval. ini untuk menghindari data di modifikasi lebih dari 1 kali
	 */
	private CommonDualControlContainerTable latestWaitingApprovalData ; 
	
	
	/**
	 * data configurasi yang di wrap dalam request
	 */
	private D configurationData ; 
	
	
	/**
	 * data yang masih menunggu approval. ini untuk menghindari data di modifikasi lebih dari 1 kali
	 */
	public void setLatestWaitingApprovalData(
			CommonDualControlContainerTable latestWaitingApprovalData) {
		this.latestWaitingApprovalData = latestWaitingApprovalData;
	}
	/**
	 * data yang masih menunggu approval. ini untuk menghindari data di modifikasi lebih dari 1 kali
	 */
	public CommonDualControlContainerTable getLatestWaitingApprovalData() {
		return latestWaitingApprovalData;
	}
	
	/**
	 * data configurasi yang di wrap dalam request
	 */
	public void setConfigurationData(D configurationData) {
		this.configurationData = configurationData;
	}

	/**
	 * data configurasi yang di wrap dalam request
	 */
	public D getConfigurationData() {
		return configurationData;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		if ( configurationData!= null){
			jsonContainer.put("configurationData",(IJSONFriendlyObject) this.configurationData);
			jsonContainer.put("fqcnGeneric", configurationData.getClass().getName());
		}
		
		jsonContainer.put("latestWaitingApprovalData", latestWaitingApprovalData);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public AppConfigurationDrivenDetaiResultHolder instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		AppConfigurationDrivenDetaiResultHolder retval = new AppConfigurationDrivenDetaiResultHolder() ;
		retval.latestWaitingApprovalData =  jsonContainer.get("latestWaitingApprovalData", CommonDualControlContainerTable.class.getName());
		if ( jsonContainer.contain("fqcnGeneric")){
			String fqcn = jsonContainer.getAsString("fqcnGeneric"); 
			retval.configurationData = (D)jsonContainer.get("configurationData", fqcn); 
		}
		return retval;
	}
	
	
	/**
	 * membaca dari JSON menjadi configuration data
	 */
	public D getWaitingApprovalData () throws Exception{
		if (latestWaitingApprovalData== null)
			return null ;
		D retval = (D) ObjectGeneratorManager.getInstance().instantiateSampleObject(latestWaitingApprovalData.getTargetObjectFQCN()); 
		ParsedJSONContainer js =  SharedServerClientLogicManager.getInstance().getJSONParser().parseJSON(latestWaitingApprovalData.getJsonData());
		retval.instantiateFromJSON(js);
		return retval ; 
	}
}
