package id.co.sigma.common.server.lov;

import id.co.sigma.common.data.lov.Common2ndLevelLOVHeader;
import id.co.sigma.common.data.lov.CommonLOVHeader;
import id.co.sigma.common.data.lov.LOV2ndLevelRequestArgument;
import id.co.sigma.common.data.lov.LOVRequestArgument;
import id.co.sigma.common.exception.CacheStillUpToDateException;
import id.co.sigma.common.exception.UnknownLookupValueProviderException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * penyedia lookup valud 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public interface ILOVProviderService {

	/**
	 * membaca data LOV .Method ini hanya akan mereturn LOV yang di anggap out of date
	 * @param dataRequest data request LOV. pls check {@link LOVRequestArgument}, untuk detil
	 * @param localizationCode kode localization 
	 **/
	public List<CommonLOVHeader> getModifiedLOVs( String localizationCode ,  List<LOVRequestArgument> dataRequest) ;  

	
	
	
	/**
	 * membaca data lookup as Hash map. 
	 * @param localizationCode i18n Code 
	 * @param lovIds id dari lov, tidak peduli dari table lookup atau dari table lain nya 
	 */
	public  Map<String ,  CommonLOVHeader> getLOVAsMap( String localizationCode ,  Collection<String> lovIds) ;
	
	/**
	 * request 2nd Level 
	 * @param localizationCode kode localization
	 * @param dataRequest data request, definisi dari LOV ada di sini. mohon cross check ke class {@link LOV2ndLevelRequestArgument}
	 * @exception UnknownLookupValueProviderException ini dalam kasus LOV tidak di temukan dalam provider. dalam artian salah kode LOV, atau memang blm di register
	 **/
	public Common2ndLevelLOVHeader getModifiedLOV(String localizationCode , LOV2ndLevelRequestArgument dataRequest) throws UnknownLookupValueProviderException, CacheStillUpToDateException , Exception;
	
	
	/**
	 * method ini membaca lookup yang 1 level. ini di pergunakan dalam kasus di set cuma dengan value dari current data, jadinya kontrol memerlukan data lain nya dalam 1 (yang parent nya sama). jadinya RPC ini akan return data dengan parent yang sama
	 * @param localizationCode kode localization
	 * @param lovId id dari lookup
	 * @param lookupValue value dari lookup
	 *  
	 **/
	public Common2ndLevelLOVHeader getSameParent2ndLevelLOV( String localizationCode,LOV2ndLevelRequestArgument lovRequestArgument ) throws UnknownLookupValueProviderException , CacheStillUpToDateException , Exception;
}
