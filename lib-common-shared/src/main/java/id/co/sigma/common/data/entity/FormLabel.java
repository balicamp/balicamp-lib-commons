package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * mapping. form x memakai label apa saja m_app_form_field_cnf
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * 
 **/
@ClientReflectableClass
@Entity
@Table(name="m_app_form_field_cnf")
public class FormLabel extends EntityObject implements IJSONFriendlyObject<FormLabel>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4472995121709349112L;

	@EmbeddedId
	private FormLabelPK id = new FormLabelPK(); 
	
	@Column(name="form_id" ,insertable=false, updatable=false)
	private String formId ;
	@Column(name="label_key",insertable=false, updatable=false)
	private String labelKey ;
	@Column(name="data_version")
	private Integer version ;
	
	
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
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public FormLabelPK getId() {
		return id;
	}
	public void setId(FormLabelPK id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "FormLabel [id=" + id + ", formId=" + formId + ", labelKey="
				+ labelKey + ", version=" + version + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((labelKey == null) ? 0 : labelKey.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		FormLabel other = (FormLabel) obj;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (labelKey == null) {
			if (other.labelKey != null)
				return false;
		} else if (!labelKey.equals(other.labelKey))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	} 
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("formId",getFormId());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("id",getId());
		jsonContainer.put("labelKey",getLabelKey());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public FormLabel instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormLabel retval = new FormLabel();
		retval.setFormId( (String)jsonContainer.get("formId" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setId( (FormLabelPK)jsonContainer.get("id" ,  FormLabelPK.class.getName()));
		retval.setLabelKey( (String)jsonContainer.get("labelKey" ,  String.class.getName()));
		retval.setVersion( (Integer)jsonContainer.get("version" ,  Integer.class.getName()));
		return retval; 
	}
	

}
