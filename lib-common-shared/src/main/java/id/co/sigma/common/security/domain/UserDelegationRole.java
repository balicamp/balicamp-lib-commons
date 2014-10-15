package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * POJO untuk table sec_user_delegate_role
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */

@Entity
@Table(name="sec_user_delegate_role")
public class UserDelegationRole implements Serializable, SingleKeyEntityData<Long>{
	
	private static final long serialVersionUID = 6178058735206547519L;
	
	//static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDelegationRole.class.getName());
	
	public UserDelegationRole(){
		
	}
	
	public UserDelegationRole(UserRole userRole){
		setRoleId(userRole.getRoleId());
	}
	
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_delegate_id")
	private Long userDelegateId;
	
	@Column(name="role_id")
	private Long roleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", insertable=false, updatable=false, nullable=true)
	private Role role;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserDelegateId() {
		return userDelegateId;
	}

	public void setUserDelegateId(Long userDelegateId) {
		this.userDelegateId = userDelegateId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		if(this.role != null) {
			return this.role.getRoleDesc();
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((userDelegateId == null) ? 0 : userDelegateId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserDelegationRole)) {
			return false;
		}
		UserDelegationRole other = (UserDelegationRole) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		if (userDelegateId == null) {
			if (other.userDelegateId != null) {
				return false;
			}
		} else if (!userDelegateId.equals(other.userDelegateId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserDelegationRole [id=" + id + ", userDelegateId="
				+ userDelegateId + ", roleId=" + roleId + "]";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
