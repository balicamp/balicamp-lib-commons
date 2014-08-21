package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;



/**
 * table : m_app_form_cnf_summary
 * summary all form configuration
 **/
@ClientReflectableClass
@Entity
@Table(name="m_app_form_cnf_summary")
public class FormConfigurationSummary implements Serializable, IJSONFriendlyObject<FormConfigurationSummary>{
	
	
	public static String TABLE_NAME="m_app_form_cnf_summary";
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253000324118387967L;

	@EmbeddedId
	private FormConfigurationSummaryPK id ; 
	
	@Version
	@Column(name="data_version")
	private Integer version ;
	
	
	@Column(name="labels")
	private String labels ;


	


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public String getLabels() {
		return labels;
	}


	public void setLabels(String labels) {
		this.labels = labels;
	}

	public FormConfigurationSummaryPK getId() {
		return id;
	}


	public void setId(FormConfigurationSummaryPK id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "FormConfigurationSummary [id=" + id + ", version=" + version
				+ ", labels=" + labels + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
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
		FormConfigurationSummary other = (FormConfigurationSummary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (labels == null) {
			if (other.labels != null)
				return false;
		} else if (!labels.equals(other.labels))
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
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("id",getId());
		jsonContainer.put("labels",getLabels());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public FormConfigurationSummary instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		FormConfigurationSummary retval = new FormConfigurationSummary();
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setId( (FormConfigurationSummaryPK)jsonContainer.get("id" ,  FormConfigurationSummaryPK.class.getName()));
		retval.setLabels( (String)jsonContainer.get("labels" ,  String.class.getName()));
		retval.setVersion( (Integer)jsonContainer.get("version" ,  Integer.class.getName()));
		return retval; 
	}
	

}
