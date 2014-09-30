package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO untuk tabel sec_user_delegate_group
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */

@Entity
@Table(name="sec_user_delegate_group")
public class UserDelegationGroup implements Serializable, SingleKeyEntityData<Long>{
	
	private static final long serialVersionUID = 2898673019229329446L;

	//static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDelegationGroup.class.getName());
	
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="user_delegate_id")
	private Long userDelegateId;
	
	@Column(name="group_id")
	private Long groupId;
	
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof UserDelegationGroup)) {
			return false;
		}
		UserDelegationGroup other = (UserDelegationGroup) obj;
		if (groupId == null) {
			if (other.groupId != null) {
				return false;
			}
		} else if (!groupId.equals(other.groupId)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
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
		return "UserDelegationGroup [id=" + id + ", userDelegateId="
				+ userDelegateId + ", groupId=" + groupId + "]";
	}
	
}
