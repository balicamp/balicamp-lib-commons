/**
 * File Name : BranchAssignment.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import id.co.sigma.common.security.domain.audit.BaseCreatedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * Entiti untuk tabel : sec_branch_assignment
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:32:17 PM
 * @version $Id
 */
@Entity
@Table(name="sec_branch_assignment")
public class BranchAssignment extends BaseCreatedObject implements IJSONFriendlyObject<BranchAssignment> {

	private static final long serialVersionUID = -3809659583242235813L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@Column(name="branch_id")
	private Long branchId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="branch_id", insertable=false, updatable=false)
	private Branch branch;

	/**
	 * Branch Assigment Id<br>
	 * COLUMN : BRANCH_ASSIGNMENT_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Branch Assigment Id<br>
	 * COLUMN : BRANCH_ASSIGNMENT_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * User Id<br>
	 * COLUMN : USER_ID
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * User Id<br>
	 * COLUMN : USER_ID
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * Reference object User {@link id.co.sigma.common.security.domain.User}
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Reference object User {@link id.co.sigma.common.security.domain.User}
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Id Cabang<br>
	 * COLUMN : BRANCH_ID
	 * @return branchId
	 */
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * Id Cabang<br>
	 * COLUMN : BRANCH_ID
	 * @param branchId
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	/**
	 * Reference object Branch {@link id.co.sigma.common.security.domain.Branch}
	 * @return branch
	 */
	public Branch getBranch() {
		return branch;
	}

	/**
	 * Reference object Branch {@link id.co.sigma.common.security.domain.Branch}
	 * @param branch
	 */
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result
				+ ((branchId == null) ? 0 : branchId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BranchAssignment other = (BranchAssignment) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	public String toString() {
		return "BranchAssignment [id=" + id + ", userId=" + userId + ", user="
				+ user + ", branchId=" + branchId + ", branch=" + branch + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		 Branch param1 = getBranch();   
		 if ( param1 != null){
			//1. Ok tampung dulu variable
			//2. null kan variable 
			// 3 taruh ke json
			jsonContainer.put("branch", param1);
			//4. restore lagi 
		}
		jsonContainer.put("branch",getBranch());
		jsonContainer.put("branchId",getBranchId());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("id",getId());
		jsonContainer.put("user",getUser());
		jsonContainer.put("userId",getUserId());
	}
	@Override
	public BranchAssignment instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		BranchAssignment retval = new BranchAssignment();
		retval.setBranch( (Branch)jsonContainer.get("branch" ,  Branch.class.getName()));
		retval.setBranchId( (Long)jsonContainer.get("branchId" ,  Long.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setUser( (User)jsonContainer.get("user" ,  User.class.getName()));
		retval.setUserId( (Long)jsonContainer.get("userId" ,  Long.class.getName()));
		return retval; 
	}
}