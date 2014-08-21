package id.co.sigma.common.control;


/**
 * provider i18 Text
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface I18TextProvider {
	
	
	/**
	 * worker untuk get text untuk internalization
	 * @param key key internalization
	 **/
	public String getText (String key) ; 
	

}
