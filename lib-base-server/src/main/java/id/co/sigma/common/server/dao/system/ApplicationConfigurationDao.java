package id.co.sigma.common.server.dao.system;

import id.co.sigma.common.data.entity.FormConfigurationSummary;
import id.co.sigma.common.data.entity.FormLabel;
import id.co.sigma.common.data.entity.I18Code;
import id.co.sigma.common.data.entity.I18NTextGroup;
import id.co.sigma.common.data.entity.I18Text;
import id.co.sigma.common.server.dao.IBaseDao;
import id.co.sigma.common.server.dao.base.BaseDaoInterface;

import java.util.Collection;
import java.util.List;



/**
 * dao untuk akses ke konfigurasi aplikasi. label form mandatory / no etc
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * 
 **/
public interface ApplicationConfigurationDao extends BaseDaoInterface, IBaseDao{
	
	
	/**
	 * membaca summary form configuration
	 * @param formId kode form. ini di define per class
	 * @param localizationCode kode language/localization 
	 * 
	 **/
	public FormConfigurationSummary readFormConfigurationSummary(String formId , String localizationCode );
	
	
	/**
	 * membaca form labels. apa saja yang di pergunakan dalam 1 form
	 **/
	public List<FormLabel> readFormLabelConfigurations (String formId ) ; 
	
	
	
	/**
	 * membaca hanya key dari form labels
	 * @param formId id form
	 **/
	public List<String> readFormLabelKeys (String formId ) ;
	
	
	/**
	 * membaca data labels dengan localization key + list of keys
	 * @param localizationCode kode localization
	 * @param textKeys list of text keys yang perlu di baca
	 *  
	 **/
	public List<I18Text> readLabels (String localizationCode , Collection<String> textKeys ) ; 
	
	
	/**
	 * membaca labels (semua localization)
	 * @param textKey key dari label yang perlu di baca
	 **/
	public List<I18Text> readLabels (String textKey) ;
	
	
	
	/**
	 * membaca lates label version number
	 **/
	public Integer getLatestLabelVersionNumber () ; 
	
	
	/**
	 * mencari form yang akan kena impact. jadi text di pergunakan di form mana saja
	 * @param labelKey key dari label. 
	 **/
	public List<String> getImpactedFormIds(String labelKey);
	
	
	
	/**
	 * hapus data dari table FormConfigurationSummary. data di hapus dengan id form
	 **/
	public void eraseFormSummaryBiFormIds (List<String> formIds); 
		
 
	/**
	 * Get I18N Text
	 * @param parameter
	 * @param pageSize
	 * @param rowPosition
	 * @return list of I18Text
	 */
	public List<I18Text> getI18TextGroup(I18Text parameter,int pageSize, int rowPosition);	
	
	/**
	 * Update label text
	 * @param data
	 * @throws Exception
	 */
	public void updateLabel(I18Text data) throws Exception;
	
	/**
	 * Get form Label / M_APP_FORM_FIELD_CNF
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public FormLabel getFormLabel(FormLabel parameter) throws Exception;
	
	/**
	 * Delete 1 data from M_APP_FORM_CNF_SUMMARY
	 * @param parameter
	 * @throws Exception
	 */
	public void deleteFormCnfSummary(FormConfigurationSummary parameter) throws Exception;
	
	
	/**
	 * membaca daftar i18 text yang ada database
	 **/
	public List<I18NTextGroup> getTextGroups();
	
	
	/**
	 * membaca daftar i18 text yang ada database
	 * @param groupId kode group. 
	 * @param i18Code kode internalization/language (en_US,in_ID etc)
	 **/
	public List<I18Text> getTextsOnGroup(String groupId , String i18Code);
	
	public List<I18Code> getAvaliableLanguages();
	
	
	/**
	 * ini membaca text dengan key. di ambil semua language
	 **/
	public List<I18Text> getAllAvailableLanguageTextById (String key) ; 
}