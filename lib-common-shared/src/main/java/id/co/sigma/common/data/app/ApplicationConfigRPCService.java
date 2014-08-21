package id.co.sigma.common.data.app;



import java.util.List;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.entity.FormElementConfiguration;
import id.co.sigma.common.data.entity.I18Code;
import id.co.sigma.common.data.entity.I18NTextGroup;
import id.co.sigma.common.data.entity.I18Text;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;





/**
 * application configuration related provider
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
//@RemoteServiceRelativePath(value="/corelib-rpc/app-cnf-data.app-rpc")
public interface ApplicationConfigRPCService extends JSONSerializedRemoteService{
	
	
	/**
	 * membaca konfigurasi form
	 * @param formId id form yang i18 friendly
	 * @param localeCode localization code
	 * @param currentVersioOnClientCache versi data dalam client cache. ini untuk mengecek update atau tidak data di bandingkan dengan versi server
	 * @exception I18FormMasterConfigurationNotDefinedException ini kalau master konfigrasi i18 untuk form yang di minta tidak di temukan. jadinya form harus mengirimkan detil label-label yang i18 ready untuk di daftarkan ke server. ini untuk efisiensi transfer data
	 * @exception NoConfigurationChangeException ini kalau tidak ada perubahan versi data. jadinya tidak ada yang perlu di kirim kembali ke client 
	 **/
	public AppFormConfiguration getFormConfiguration (String formId , String localeCode , Integer currentVersioOnClientCache ) throws 
		NoConfigurationChangeException, 
		I18FormMasterConfigurationNotDefinedException; 
	
	
	
	/**
	 * simpan perubahan pada label(form related label).
	 * @param localeCode kode lokalisasi. internalization
	 * @param key key text
	 * @param label label baru untuk konfigurasi yang di minta 
	 **/
	public void saveLabel (String localeCode , String key , String label ); 
	
	
	
	/**
	 * labels, dalam semua localization
	 * @param key key dari locale
	 **/
	public LabelDataWrapper[] getLabels (String key); 
	
	
	
	/**
	 * simpan sekalian labels. ini akan memaksa semua localization di save sekalian
	 **/
	public void saveLabels(String textKey , LabelDataWrapper[] labels); 
	
	/**
	 * Get i18N text group Id
	 * @param groupId
	 */
	public PagedResultHolder<LabelDataWrapper> getI18NGroupId(I18Text groupId, int pageSize, int rowPosition);
	
	/**
	 * Save new label
	 * @param data	 
	 */
	public void saveLabel(I18Text data);
	
	
	/**
	 * Save new label(dengan array)
	 * @param 	 texts array of text yang akan d i simpan
	 */
	public void saveLabels(I18Text[] texts) throws Exception ;
	
	/**
	 * Update label
	 * @param data	 
	 */
	public void updateLabel(I18Text data);
	
	
	/**
	 * languages yang ada dalam database
	 **/
	public List<I18Code> getAvaliableLanguages();
	
	
	/**
	 * Menyimpan konfigurasi field control
	 * @param data
	 */
	public void saveControlConfiguration(FormElementConfiguration data) throws Exception;
	
	/**
	 * Membaca konfigurasi field control
	 * @param formId
	 * @param elementId
	 * @return
	 */
	public FormElementConfiguration readControlConfiguration(String formId, String elementId) throws Exception;
	
	
	/**
	 * membaca data i18N text . ini untuk browse text box
	 * @param pageSize ukuran page per pembacaan 
	 * @param pagePosition posisi page yang hendak di baca
	 *  
	 **/
	public PagedResultHolder<I18Text> getI18NTexts(int pageSize , int pagePosition , SimpleQueryFilter [] filters ) throws Exception; 
	
	
	
	/**
	 * membaca semua languages 
	 **/
	public List<I18Text> getAllLanguagesTextById(String i18Nkey ) ; 
	
	
	
	/**
	 * membaca group i18 N yang ada dalam database
	 **/
	public List<I18NTextGroup> getTextGroups ();
}
