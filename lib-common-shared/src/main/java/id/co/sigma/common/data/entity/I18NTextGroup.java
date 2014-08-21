package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.EntityObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity utk table M_APP_I18_GROUP
 * @author I Gede Mahendra
 * @since Sep 19, 2012, 12:01:37 PM
 * @version $Id
 */
@Entity
@Table(name="m_app_i18_group")
public class I18NTextGroup extends EntityObject implements IJSONFriendlyObject<I18NTextGroup>{
	
	private static final long serialVersionUID = 2654374227373481076L;

	@Id
	@Column(name="GROUP_ID")
	private String id;
	
	@Column(name="GROUP_DESC")
	private String description;	
	
	/**
	 * Default Constructor
	 */
	public I18NTextGroup(){
		super();
	}
	
	/**
	 * Additional Constructor
	 * @param id
	 */
	public I18NTextGroup(String id){
		this.id = id;
	}
	
	/**
	 * Additional Constructor
	 * @param id
	 * @param description
	 */
	public I18NTextGroup(String id, String description){
		this.id = id;
		this.description = description;
	}
	
	/**
	 * Getter untuk GROUP_ID
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Setter untuk GROUP_ID
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter untuk GROUP_DESC - Description
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter untuk GROUP_DESC - Description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		I18NTextGroup other = (I18NTextGroup) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "I18NTextGroup [id=" + id + ", description=" + description + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("description",getDescription());
		jsonContainer.put("id",getId());
	}
	@Override
	public I18NTextGroup instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		I18NTextGroup retval = new I18NTextGroup();
		retval.setDescription( (String)jsonContainer.get("description" ,  String.class.getName()));
		retval.setId( (String)jsonContainer.get("id" ,  String.class.getName()));
		return retval; 
	}
}