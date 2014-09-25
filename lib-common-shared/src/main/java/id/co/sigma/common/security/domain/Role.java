package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * sec_role. table : sec_role.
 * @author <a href="mailto:rie.anggreani@gmail.com">Arie Anggreani</a>
 */
@Entity
@Table(name="sec_role")
public class Role implements IJSONFriendlyObject<Role>, SingleKeyEntityData<Long> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5863224435217499936L;

	/**
	* id dari role<br/>
	* column :id
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	
	/**
	* code dari role<br/>
	* column :role_code
	**/
	@Column(name="role_code")
	private String roleCode;
	
	/**
	* description dari role<br/>
	* column :role_description
	**/
	@Column(name="role_description")
	private String roleDesc;
	
	/**
	* is_predefined dari role<br/>
	* column :is_predefined
	**/
	@Column(name="is_predefined")
	private String isPredefined;
	
	
	public String getIsPredefined() {
		return isPredefined;
	}

	public void setIsPredefined(String isPredefined) {
		this.isPredefined = isPredefined;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	
	

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id", id);
		jsonContainer.put("roleCode", roleCode);
		jsonContainer.put("roleDesc", roleDesc);
		jsonContainer.put("isPredefined", isPredefined);
		
	}

	@Override
	public Role instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Role retval = new Role();
		retval.id=(Long)jsonContainer.get("id", Long.class.getName());
		retval.roleCode=(String)jsonContainer.get("roleCode", String.class.getName());
		retval.roleDesc=(String)jsonContainer.get("roleDesc", String.class.getName());
		retval.isPredefined=(String)jsonContainer.get("isPredefined", String.class.getName());
		return retval;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleCode=" + roleCode + ", roleDesc="
				+ roleDesc + ", isPredefined=" + isPredefined + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isPredefined == null) ? 0 : isPredefined.hashCode());
		result = prime * result
				+ ((roleCode == null) ? 0 : roleCode.hashCode());
		result = prime * result
				+ ((roleDesc == null) ? 0 : roleDesc.hashCode());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPredefined == null) {
			if (other.isPredefined != null)
				return false;
		} else if (!isPredefined.equals(other.isPredefined))
			return false;
		if (roleCode == null) {
			if (other.roleCode != null)
				return false;
		} else if (!roleCode.equals(other.roleCode))
			return false;
		if (roleDesc == null) {
			if (other.roleDesc != null)
				return false;
		} else if (!roleDesc.equals(other.roleDesc))
			return false;
		return true;
	}
	
	
	

}
