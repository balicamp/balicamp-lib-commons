package id.co.sigma.common.control;




/**
 * interface object yang bisa menerima konfigurasi dari form(i18 configuration) 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface ResourceBundleConfigurableControl {
	
	
	
	
	
	
	/**
	 * setter. key internalization
	 **/
	public void setI18Key(String key) ; 
	
	
	/**
	 * getter. i18 key
	 **/
	public String getI18Key() ;
	
	
	
	
	
	
	/**
	 * set text dengan data di feed dari konfigurasi. ini belum tentu label yang tampil. bisa jadi widget business name. misal nama bisnis dari 
	 **/
	public void setConfiguredText (String text ) ; 
	
	
	
	
	
	
	
	
	
	
	
	

}
