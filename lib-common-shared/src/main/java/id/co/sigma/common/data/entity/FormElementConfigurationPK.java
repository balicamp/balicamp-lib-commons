package id.co.sigma.common.data.entity;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * PK dari Form Element
 * @author I Gede Mahendra
 * @version $Id
 */
@Embeddable
public class FormElementConfigurationPK implements Serializable,IsSerializable, IJSONFriendlyObject<FormElementConfigurationPK>{

	private static final long serialVersionUID = 59893415901929691L;
	
	@Column(name="form_id")
	private String formId;
	
	@Column(name="element_id")
	private String elementId;

	/**
	 * Default constructor
	 */
	public FormElementConfigurationPK() {
		super();
	}
	
	/**
	 * Additional constructor
	 * @param formId
	 * @param elementId
	 */
	public FormElementConfigurationPK(String formId, String elementId) {
		this.formId = formId;
		this.elementId = elementId;
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
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("elementId",getElementId());
		jsonContainer.put("formId",getFormId());
	}
	
	@Override
	public FormElementConfigurationPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormElementConfigurationPK retval = new FormElementConfigurationPK();
		retval.setElementId( (String)jsonContainer.get("elementId" ,  String.class.getName()));
		retval.setFormId( (String)jsonContainer.get("formId" ,  String.class.getName()));
		return retval; 
	}
}