package id.co.sigma.common.data.app;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * wrapper data. lokalisasi vs label nya
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class LabelDataWrapper implements IsSerializable, IJSONFriendlyObject<LabelDataWrapper> {
	
	private String localeCode ; 
	private String label ; 
	private String key;
	private Integer version;
	private String groupId;
	
	
	public LabelDataWrapper(){}
	
	
	public LabelDataWrapper(String localeCode , String label){
		this.localeCode = localeCode ; 
		this.label = label ; 
		
	}
	
	
	public String getLocaleCode() {
		return localeCode;
	}
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("groupId",getGroupId());
		jsonContainer.put("key",getKey());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("localeCode",getLocaleCode());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public LabelDataWrapper instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LabelDataWrapper retval = new LabelDataWrapper();
		retval.setGroupId( (String)jsonContainer.get("groupId" ,  String.class.getName()));
		retval.setKey( (String)jsonContainer.get("key" ,  String.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setLocaleCode( (String)jsonContainer.get("localeCode" ,  String.class.getName()));
		retval.setVersion( (Integer)jsonContainer.get("version" ,  Integer.class.getName()));
		return retval; 
	}
}