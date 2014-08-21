package id.co.sigma.common.data.lov;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public class Common2ndLevelLOV implements IsSerializable, IJSONFriendlyObject<Common2ndLevelLOV>{
	

	/**
	 * value parent LOV
	 **/
	protected String parentValue ;
	/**
	 * value dari parameter
	 **/
	protected String value ; 
	/**
	 * label parameter. visible bagi user
	 **/
	protected String label ;
	
	/**
	 * extended data 1
	 **/
	protected String additionalData1; 
	
	
	/**
	 * data tambahan 2
	 **/
	protected String additionalData2;
	
	/**
	 * value parent LOV
	 **/
	public void setParentValue(String parentValue) {
		this.parentValue = parentValue;
	}
	/**
	 * value parent LOV
	 **/
	public String getParentValue() {
		return parentValue;
	}
	/**
	 * value dari parameter
	 **/
	public String getValue() {
		return value;
	}
	/**
	 * value dari parameter
	 **/
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * label parameter. visible bagi user
	 **/
	public String getLabel() {
		return label;
	}
	/**
	 * label parameter. visible bagi user
	 **/
	public void setLabel(String label) {
		this.label = label;
	}

	
	/**
	 * extended data 1
	 **/
	public void setAdditionalData1(String additionalData1) {
		this.additionalData1 = additionalData1;
	}
	/**
	 * extended data 1
	 **/
	public String getAdditionalData1() {
		return additionalData1;
	}
	
	/**
	 * data tambahan 2
	 **/
	public void setAdditionalData2(String additionalData2) {
		this.additionalData2 = additionalData2;
	}
	
	/**
	 * data tambahan 2
	 **/
	public String getAdditionalData2() {
		return additionalData2;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("additionalData1",getAdditionalData1());
		jsonContainer.put("additionalData2",getAdditionalData2());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("parentValue",getParentValue());
		jsonContainer.put("value",getValue());
	}
	
	@Override
	public Common2ndLevelLOV instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Common2ndLevelLOV retval = new Common2ndLevelLOV();
		retval.setAdditionalData1( (String)jsonContainer.get("additionalData1" ,  String.class.getName()));
		retval.setAdditionalData2( (String)jsonContainer.get("additionalData2" ,  String.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setParentValue( (String)jsonContainer.get("parentValue" ,  String.class.getName()));
		retval.setValue( (String)jsonContainer.get("value" ,  String.class.getName()));
		return retval; 
	}
}
