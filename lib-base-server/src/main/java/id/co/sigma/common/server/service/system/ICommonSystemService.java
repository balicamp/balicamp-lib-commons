package id.co.sigma.common.server.service.system;

import java.util.Collection;
import java.util.Map;

import id.co.sigma.common.data.SystemParamDrivenClass;
import id.co.sigma.common.data.app.SystemSimpleParameter;
import id.co.sigma.common.server.IReloadableService;


/**
 * 
 * layer common system service
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICommonSystemService extends IReloadableService{
	
	
	
	/**
	 * membaca 1 squence tunggal . data di baca dari table  : ct_common_sequence
	 * @param sequenceName nama dari sequence
	 * @param remarkForSequence remark untuk sequence. sekadar untuk maintenence. ini catatan ini sequence apa
	 */
	public Long getSequence ( String sequenceName , String remarkForSequence);
	
	
	
	
	/**
	 * membaca n sequence tertentu. jadinya di baca sekalian. ini di pergunakan untuk bulk
	 * @param sequenceName nama sequence
	 * @param sizeOfSequenceToFetch berapa sequence yang di baca sekalian dari database
	 * @param remarkForSequence catatan atas sequence yang di pakai
	 */
	
	public Long[] getSequences ( String sequenceName , int sizeOfSequenceToFetch, String remarkForSequence);
	
	
	
	/**
	 * membuat 0 leaded string. dengan panjang tertentu
	 */
	public String generate0LeadedNumber( long number, int lengthOfString ); 
	
	
	
	
	/**
	 * membuat reference number. key akan di simpan dalam ct_common_sequence( mempergunakan method {@link #getSequence(String, String)}) 
	 * @param key key untuk di taruh dalam table ct_common_sequence 
	 * @param defaultRemarkForSequence remark yang akan ditaruh dalam table ct_common_sequence
	 * @param prefix prefix dari sequrnce, kalau diperlukan awalan untuk sequence
	 * @param lengthOfSequence panjang dari sequence( exclude prefix) . akan di awali 0 untuk yang kosong
	 */
	public String generateRefNumber ( String key, String prefix ,int lengthOfSequence  ,String defaultRemarkForSequence  ) ; 
	
	
	public String generateRefNumber(String key, String prefix,
			int lengthOfSequence, String defaultRemarkForSequence, boolean sequenceOnTail) ; 
	/**
	 * membaca parameter dengan key dari parameter
	 */
	public SystemSimpleParameter getParameterByKey ( String key ) ; 
	
	/**
	 * membaca data dengan keys dari data
	 * @param keys array of data keys
	 */
	public Map<String, SystemSimpleParameter> getParameterByKeys (String [] keys ) ;
	
	
	/**
	 * decrypt parameter
	 * @param encyptedParameters paremeter. semua nya encrypted 
	 *  
	 */
	public void decryptParameters (Collection< SystemSimpleParameter> encyptedParameters ) ;
	
	
	
	/**
	 * parameter yang perli di encrypt
	 */
	public void encryptParameters (Collection< SystemSimpleParameter> parameters ) ;
	
	
	
	
	/**
	 * load konfigurasi dengan class instance
	 * @param clazz yang perlu di load
	 */
	public <D extends  SystemParamDrivenClass<?,?>> D   loadConfiguration ( Class<D> clazz ) throws Exception ;  
	
	
	/**
	 * simpan data kembali ke dalam database
	 */
	public <D extends  SystemParamDrivenClass<?,?>> void saveConfiguration ( D configuration) throws Exception ; 
	

}
