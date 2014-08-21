/**
 * 
 */
package id.co.sigma.common.server.lov;

import id.co.sigma.common.data.lov.CommonLOV;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.lov.BaseSelfRegisterLOVProvider;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * generic LOV. How to use : 
 * <ol>
 * 	<li>extend class ini dan override field yg di perlukan</li>
 * <li>set jadi bean dan make lazy=false*mekanisme menjadikan bean, ikut policy*</li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Jul 31, 2013 4:23:58 PM
 */
public abstract class GenericCustomLOVProvider<KEY, DATA> extends BaseSelfRegisterLOVProvider {
	
	private static Logger logger = LoggerFactory.getLogger(GenericCustomLOVProvider.class);
	
	@Autowired
	private IGeneralPurposeDao dao;
	
	
	
	
	/**
	 * versi dari LOV. ini di pakai dalam {@link #getVersion()}
	 **/
	private String version ; 
	

	@Override
	public List<CommonLOV> getLookupValues(String localizationCode) {
		try {
			@SuppressWarnings("rawtypes")
			List swap  =  dao.list(getLOVSourceClass(), getLOVSorter());
		
			@SuppressWarnings("unchecked")
			List<DATA> allData = (List<DATA>)swap; 
			if(allData==null||allData.isEmpty())
				return null;
			List<CommonLOV> retval = new ArrayList<CommonLOV>();
			
			for( DATA scn : allData){
				retval.add(  this.translateToCommonLovData(scn));
				String versi = getVersionFromData(scn); 
				if ( versi != null&& versi.length()> 0 ){
					if ( version==null)
						version = versi ; 
					else{
						if ( isNewerVersionNumber(version, versi))
							version = versi ; 
					}
				}
			}
			return retval ;
		} catch (Exception e) {
			logger.error("gagal membaca data LOV untuk class" + getLOVSourceClass()+ ",error message :" + e.getMessage() , e);
			e.printStackTrace();
			return null ;
		}
	}
	
	
	
	/**
	 * ini membaca versi dari data. ini bisa di ambil dari date, primary key data atau yang lain. rekomendasi : ambil dari updated date/ created date di jadikan long
	 **/
	protected abstract String getVersionFromData (DATA data) ; 
	
	
	/**
	 * pembanding, mana yang lebih baru dari 2 versi
	 **/
	protected abstract boolean isNewerVersionNumber (String currentVersion , String scannedVersion) ; 
	
	
	
	
	

	@Override
	public boolean isCacheable() {
		return true;
	}

	@Override
	public String getVersion() {
		return version;
	}
	
	/**
	 * table dari mana LOV di ambil
	 * 
	 **/
	protected abstract Class<DATA> getLOVSourceClass();
	
	/**
	 * field-field untuk sort
	 **/
	protected abstract SimpleSortArgument[] getLOVSorter();
	
	/**
	 * pekerjaan nya adalah merubah dari data MDA menjadi data LOV
	 **/
	protected abstract CommonLOV translateToCommonLovData(DATA data);
}
