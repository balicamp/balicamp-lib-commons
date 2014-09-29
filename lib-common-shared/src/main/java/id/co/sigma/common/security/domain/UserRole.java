package id.co.sigma.common.security.domain;

import id.co.sigma.common.security.domain.audit.BaseCreatedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka Darmawan</a>
 */
@Entity
@Table(name="sec_user_role" , uniqueConstraints={@UniqueConstraint(columnNames={
		"role_id" , 
		"user_id"
})})
public class UserRole extends BaseCreatedObject implements IJSONFriendlyObject<UserRole> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="user_id",nullable=false)
	private Long userId;
	
	@Column(name="role_id", nullable=false)
	private Long roleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=false, updatable=false, nullable=true)
	private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false, nullable=true)
	private User user;
	
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleId="
				+ roleId + ", role=" + role + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("id", getId());
		jsonContainer.put("userId", getUserId());
		jsonContainer.put("roleId", getRoleId());
		
		User param4 = getUser();   
		 if ( param4 != null){ 
		
		 //1. Ok tampung dulu variable
		Application param4_defaultApplication_tmp = param4.getDefaultApplication();
		//2. null kan variable 
		param4.setDefaultApplication(null);
		//3 taruh ke json
		jsonContainer.put("user", param4);
		//4. restore lagi 
			param4.setDefaultApplication(param4_defaultApplication_tmp);
		}
		jsonContainer.put("user",getUser());
		
		jsonContainer.put("role",getRole());
		
	}

	@Override
	public UserRole instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserRole retval = new UserRole();
		retval.setId( (Long) jsonContainer.get("id", Long.class.getName()));
		retval.setUserId( (Long) jsonContainer.get("userId", Long.class.getName()));
		retval.setRoleId( (Long) jsonContainer.get("roleId", Long.class.getName()));
		retval.setUser( (User)jsonContainer.get("user" ,  User.class.getName()));
		retval.setRole((Role) jsonContainer.get("role", Role.class.getName()));
		return retval;
	}
	
	
}
