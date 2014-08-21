package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;



@ClientReflectableClass
@Entity
@Table(name="m_app_i18text")
public class I18Text extends EntityObject implements IJSONFriendlyObject<I18Text>{
	
	private static final long serialVersionUID = 3219258700591494844L;

	
	/**
	 * primary key dari i18
	 **/
	@EmbeddedId
	private I18TextPK id;	
	
	@Column(name="text_value")
	private String label ;
	
	@Version
	@Column(name="data_version", nullable=false)
	private Integer version ;	
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="group_id" , updatable=false , insertable=false)
	private I18NTextGroup groupId;
	
	
	
	/**
	 * kode group. as string
	 **/
	@Column(name="group_id")
	private String groupCode ; 

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	} 
	
	/**
	 * primary key dari i18
	 **/
	public void setId(I18TextPK id) {
		this.id = id;
	}
	/**
	 * primary key dari i18
	 **/
	public I18TextPK getId() {
		return id;
	}
	
	/**
	 * Get group Id
	 * @return
	 */
	public I18NTextGroup getGroupId() {
		return groupId;
	}

	/**
	 * Set group id
	 * @param groupId
	 */
	public void setGroupId(I18NTextGroup groupId) {
		this.groupId = groupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		I18Text other = (I18Text) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "I18Text [id=" + id + ", label=" + label + ", version="
				+ version + "]";
	}
	
	
	/**
	 * kode group. as string
	 **/
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * kode group. as string
	 **/
	public String getGroupCode() {
		return groupCode;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("groupCode",getGroupCode());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("groupId",getGroupId());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("id",getId());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("version",getVersion());
	}
	
	@Override
	public I18Text instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		I18Text retval = new I18Text();
		retval.setGroupCode( (String)jsonContainer.get("groupCode" ,  String.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setGroupId( (I18NTextGroup)jsonContainer.get("groupId" ,  I18NTextGroup.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setId( (I18TextPK)jsonContainer.get("id" ,  I18TextPK.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setVersion( (Integer)jsonContainer.get("version" ,  Integer.class.getName()));
		return retval; 
	}

}
