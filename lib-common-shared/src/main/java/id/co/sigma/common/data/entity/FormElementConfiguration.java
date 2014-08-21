package id.co.sigma.common.data.entity;


import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * variable wrapper untuk form configuration
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @author Gede Mahendra
 * @version $Id
 **/
@ClientReflectableClass
@Entity
@Table(name="m_app_config_control")
public class FormElementConfiguration implements Serializable,IsSerializable, IJSONFriendlyObject<FormElementConfiguration>{

	private static final long serialVersionUID = 1029333684813597875L;
	
	@EmbeddedId
	private FormElementConfigurationPK id;
	
	@Transient
	private String formId ;
	
	@Transient
	private String elementId ;
	
	@Column(name="i18Key_hint_id")
	private String hintI18NKey ;
	
	@Column(name="i18Key_placeholder_id")
	private String placeHolderI18NKey;
	
	@Column(name="is_mandatory")	
	private Boolean mandatory;
	
	@Column(name="max_length")
	private Integer maxLength ;
	
	@Column(name="min_value")
	private String minValue ;
	
	@Column(name="max_value")
	private String maxValue ;

	@Column(name="group_id")
	private String groupId;
	
	public FormElementConfigurationPK getId() {
		return id;
	}

	public void setId(FormElementConfigurationPK id) {
		this.id = id;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getHintI18NKey() {
		return hintI18NKey;
	}

	public void setHintI18NKey(String hintI18NKey) {
		this.hintI18NKey = hintI18NKey;
	}

	public String getPlaceHolderI18NKey() {
		return placeHolderI18NKey;
	}

	public void setPlaceHolderI18NKey(String placeHolderI18NKey) {
		this.placeHolderI18NKey = placeHolderI18NKey;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public static String generateDataKey(String formId2, String elementId2) {
		return formId2 +"." + elementId2;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("elementId",getElementId());
		jsonContainer.put("formId",getFormId());
		jsonContainer.put("groupId",getGroupId());
		jsonContainer.put("hintI18NKey",getHintI18NKey());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("id",getId());
		jsonContainer.put("mandatory",getMandatory());
		jsonContainer.put("maxLength",getMaxLength());
		jsonContainer.put("maxValue",getMaxValue());
		jsonContainer.put("minValue",getMinValue());
		jsonContainer.put("placeHolderI18NKey",getPlaceHolderI18NKey());
	}
	
	@Override
	public FormElementConfiguration instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormElementConfiguration retval = new FormElementConfiguration();
		retval.setElementId( (String)jsonContainer.get("elementId" ,  String.class.getName()));
		retval.setFormId( (String)jsonContainer.get("formId" ,  String.class.getName()));
		retval.setGroupId( (String)jsonContainer.get("groupId" ,  String.class.getName()));
		retval.setHintI18NKey( (String)jsonContainer.get("hintI18NKey" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setId( (FormElementConfigurationPK)jsonContainer.get("id" ,  FormElementConfigurationPK.class.getName()));
		retval.setMandatory( (Boolean)jsonContainer.get("mandatory" ,  Boolean.class.getName()));
		retval.setMaxLength( (Integer)jsonContainer.get("maxLength" ,  Integer.class.getName()));
		retval.setMaxValue( (String)jsonContainer.get("maxValue" ,  String.class.getName()));
		retval.setMinValue( (String)jsonContainer.get("minValue" ,  String.class.getName()));
		retval.setPlaceHolderI18NKey( (String)jsonContainer.get("placeHolderI18NKey" ,  String.class.getName()));
		return retval; 
	}
	
}