package id.co.sigma.common.data.app;


import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;




/**
 * application configuration
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
@XmlRootElement
@XmlType(propOrder = {"parentId","localeId","version","configurationLabel","missingLabelKeys"})
public class AppFormConfiguration       implements IJSONFriendlyObject<AppFormConfiguration>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1214154745396863902L;
	protected String parentId ;
	protected String localeId ;
	
	
	/**
	 * versi konfigurasi form
	 **/
	protected Integer version ;
	
	/**
	 * configuration labels
	 **/
	protected ConfigurationLabel[] configurationLabels; 
	
	     
	
	/**
	 * daftar key dari label yang belum di definisi. ini bisa berakibat label tidak muncul properly
	 * 
	 **/
	protected String[] missingLabelKeys ; 
	
	
	public AppFormConfiguration(){}
	
	
	public AppFormConfiguration(AppFormConfiguration sampleInstance ){
		this.parentId = sampleInstance.parentId; 
		this.localeId=sampleInstance.localeId ; 
		this.missingLabelKeys=sampleInstance.missingLabelKeys;
		this.version=sampleInstance.version ; 
		
	}
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLocaleId() {
		return localeId;
	}
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
	
	
	
	/**
	 * configuration labels
	 **/
	public void setConfigurationLabel(ConfigurationLabel[] configurationLabels) {
		this.configurationLabels = configurationLabels;
	}
	/**
	 * configuration labels
	 **/
	@XmlElementWrapper(name="configurationlabels")
	public ConfigurationLabel[] getConfigurationLabel() {
		return configurationLabels;
	}

	/**
	 * versi konfigurasi form
	 **/
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * versi konfigurasi form
	 **/
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * daftar key dari label yang belum di definisi. ini bisa berakibat label tidak muncul properly
	 * 
	 **/
	@XmlElement(nillable=true)
	public String[] getMissingLabelKeys() {
		return missingLabelKeys;
	}
	/**
	 * daftar key dari label yang belum di definisi. ini bisa berakibat label tidak muncul properly
	 * 
	 **/
	public void setMissingLabelKeys(String[] missingLabelKeys) {
		this.missingLabelKeys = missingLabelKeys;
	}


	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.appendToArray( "configurationLabel",getConfigurationLabel());
		jsonContainer.put("localeId",getLocaleId());
		jsonContainer.appendToArray("missingLabelKeys",getMissingLabelKeys());
		jsonContainer.put("parentId",getParentId());
		jsonContainer.put("version",getVersion());
	}
	@Override
	public AppFormConfiguration instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		AppFormConfiguration retval = new AppFormConfiguration();
		retval.setConfigurationLabel( (ConfigurationLabel[])jsonContainer.get("configurationLabel" ,  ConfigurationLabel[].class.getName()));
		retval.setLocaleId( (String)jsonContainer.get("localeId" ,  String.class.getName()));
		retval.setMissingLabelKeys( (String[])jsonContainer.get("missingLabelKeys" ,  String[].class.getName()));
		retval.setParentId( (String)jsonContainer.get("parentId" ,  String.class.getName()));
		retval.setVersion( (Integer)jsonContainer.get("version" ,  Integer.class.getName()));
		return retval; 
	}


	

	
	
	
}
