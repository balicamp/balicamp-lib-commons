package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




/**
 * konfigurasi label
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
@XmlRootElement(name="configurationLabel")
@XmlType(propOrder = {"key","label"})
public class ConfigurationLabel implements IJSONFriendlyObject<ConfigurationLabel> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2806644582675693443L;
	/**
	 * key konfigurasi
	 **/
	protected String key ;
	/**
	 * label pengganti
	 **/
	protected String label ;
	
	
	
	public ConfigurationLabel(){}
	
	
	
	
	/**
	 * konstruktor konfigurasi
	 * @param key key dari konfigurasi label
	 * @param label label dari konfigurasi
	 **/
	public ConfigurationLabel(String key, String label){
		this.key= key;
		this.label= label;
		
		
	}
	
	
	public ConfigurationLabel(ConfigurationLabel sample){
		this.key = sample.key ;
		this.label= sample.label;
	}
	/**
	 * key konfigurasi
	 **/
	public String getKey() {
		return key;
	}
	/**
	 * key konfigurasi
	 **/
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * label pengganti
	 **/
	public String getLabel() {
		return label;
	}
	/**
	 * label pengganti
	 **/
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("key",getKey());
		jsonContainer.put("label",getLabel());
	}
	@Override
	public ConfigurationLabel instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ConfigurationLabel retval = new ConfigurationLabel();
		retval.setKey( (String)jsonContainer.get("key" ,  String.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		return retval; 
	}


	
	
	

}
