package id.co.sigma.common.server.dao.system;

import java.util.List;

import id.co.sigma.common.data.entity.FormElementConfiguration;

/**
 * Application konfigurasi field control
 * @author I Gede Mahendra
 * @version $Id
 */
public interface IApplicationConfigFieldControl {
	
	/**
	 * Get form element config
	 * @param formId
	 * @param elementId
	 * @return
	 * @throws Exception
	 */
	public FormElementConfiguration getFormElementConfigurationData(String formId, String elementId) throws Exception;
	
	/**
	 * Save atau update configuration control
	 * @param data
	 * @throws Exception
	 */
	public void saveOrUpdate(FormElementConfiguration data) throws Exception; 
	
	
	
	
	/**
	 * membaca form configurations dengan berdasar pada id groups
	 * @param groupIds id groups (field : group_id)
	 **/
	public List<FormElementConfiguration> getFormElementConfigurationsData(String groupIds ); 
	
	
	
	
	/**
	 * groups dari form configurations yang ada dalam database
	 **/
	public List<String> getFormConfigurationGroups() ; 
	
}