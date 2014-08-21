package id.co.sigma.common.server.service.system;

import java.util.Date;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.AppFormConfiguration;
import id.co.sigma.common.data.app.LabelDataWrapper;
import id.co.sigma.common.data.entity.FormConfigurationSummary;
import id.co.sigma.common.data.entity.FormElementConfiguration;
import id.co.sigma.common.data.entity.I18Text;


/**
 * service terkait konfigurasi applikasi
 **/
public interface ApplicationConfigService {
	
	
	
	/**
	 * produce {@link AppFormConfiguration} dari object {@link FormConfigurationSummary}. pls be aware, {@link FormConfigurationSummary#getLabels()} merupakan xml yang mereferensikan text yang localize
	 * @param cachedConfig data hasil read entity {@link FormConfigurationSummary}. method ini kebagian break labels dan string panjang lain nya
	 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a> 
	 **/
	public AppFormConfiguration generateConfigurationObjectFromCachedData(FormConfigurationSummary cachedConfig) ;
	
	
	
	
	/**
	 * create konfigurasi dan save ke dalam table (class : FormConfigurationSummary) {@value FormConfigurationSummary#TABLE_NAME}
	 * @param formId id form/class yang localize
	 * @param localeCode localization code
	 * 
	 **/
	public AppFormConfiguration createAndWriteConfiguration (String formId,
			String localeCode) ; 
	
	
	
	/**
	 * simpan labels. sekalian beberapa labels
	 * @param textKey key dari text yang di simpan
	 * @param labels labels. ini di terima dari client
	 **/
	public void saveLabels(String textKey, LabelDataWrapper[] labels) throws Exception;
	
	/**
	 * Get group
	 * @param groupId
	 * @param pageSize
	 * @param rowPosition
	 * @return
	 */
	public PagedResultHolder<LabelDataWrapper> getI18NGroupId(I18Text groupId, int pageSize, int rowPosition); 
	
	/**
	 * simpan 1 label
	 * @param data
	 * @throws Exception
	 */
	public void saveLabel(I18Text data) throws Exception;
	
	/**
	 * update 1 label
	 * @param data
	 * @throws Exception
	 */
	public void updateLabel(I18Text data) throws Exception;
	
	/**
	 * Insert data control configuration
	 * @param data
	 * @throws Exception
	 */
	public void saveControlConfiguration(FormElementConfiguration data) throws Exception;
	
	/**
	 * Read single data control configuration
	 * @param formId
	 * @param elementId
	 * @return
	 * @throws Exception
	 */
	public FormElementConfiguration readControlConfiguration(String formId, String elementId) throws Exception;
	
	
	
	public void saveLabels(I18Text[] texts, String username ,Date applicationDate) throws Exception ;
}
