package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;



/**
 * primary key dari form Label
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 **/
@Embeddable
public class FormLabelPK extends EntityObject implements IJSONFriendlyObject<FormLabelPK>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 781261674125517157L;
	@Column(name="form_id" )
	private String formId ;
	@Column(name="label_key")
	private String labelKey ;
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	@Override
	public String toString() {
		return "FormLabelPK [formId=" + formId + ", labelKey=" + labelKey + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result
				+ ((labelKey == null) ? 0 : labelKey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormLabelPK other = (FormLabelPK) obj;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (labelKey == null) {
			if (other.labelKey != null)
				return false;
		} else if (!labelKey.equals(other.labelKey))
			return false;
		return true;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("formId",getFormId());
		jsonContainer.put("labelKey",getLabelKey());
	}
	
	@Override
	public FormLabelPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormLabelPK retval = new FormLabelPK();
		retval.setFormId( (String)jsonContainer.get("formId" ,  String.class.getName()));
		retval.setLabelKey( (String)jsonContainer.get("labelKey" ,  String.class.getName()));
		return retval; 
	}

}
