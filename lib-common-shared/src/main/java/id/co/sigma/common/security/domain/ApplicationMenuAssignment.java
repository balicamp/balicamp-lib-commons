/**
 * File Name : FunctionAssignment.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.security.domain.audit.BaseCreatedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


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

/**
 * Entiti untuk tabel : sec_function_assignment
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 3:28:50 PM
 * @version $Id
 */
@Entity
@Table(name="sec_menu_assignment")
public class ApplicationMenuAssignment extends BaseCreatedObject implements IJSONFriendlyObject<ApplicationMenuAssignment> {

	private static final long serialVersionUID = 4776419933883162445L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pk")
	private Long id;
	
	@Column(name="menu_id")
	private Long functionId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menu_id", insertable=false, updatable=false)
	private ApplicationMenu function;
	
	@Column(name="group_id")
	private Long groupId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="group_id", insertable=false, updatable=false)
	private UserGroup group;

	/**
	 * Function Assignment Id<br>
	 * COLUMN : FUNCTION_ASSIGNMENT_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Function Assignment Id<br>
	 * COLUMN : FUNCTION_ASSIGNMENT_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Function  Id<br>
	 * COLUMN : FUNCTION_ID
	 * @return
	 */
	public Long getFunctionId() {
		return functionId;
	}

	/**
	 * Function  Id<br>
	 * COLUMN : FUNCTION_ID
	 * @param functionId
	 */
	public void setFunctionId(Long functionId) {
		this.functionId = functionId;
	}

	/**
	 * reference ke object Function {@link id.co.sigma.common.security.domain.ApplicationMenu}
	 * @return function
	 */
	public ApplicationMenu getFunction() {
		return function;
	}

	/**
	 * reference ke object Function {@link id.co.sigma.common.security.domain.ApplicationMenu}
	 * @param function
	 */
	public void setFunction(ApplicationMenu function) {
		this.function = function;
	}

	/**
	 * Group Id<br>
	 * COLUMN : GROUP_ID
	 * @return groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * Group Id<br>
	 * COLUMN : GROUP_ID
	 * @param groupId
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * reference ke object UserGroup {@link id.co.sigma.common.security.domain.UserGroup}
	 * @return group
	 */
	public UserGroup getGroup() {
		return group;
	}

	/**
	 * reference ke object UserGroup {@link id.co.sigma.common.security.domain.UserGroup}
	 * @param group
	 */
	public void setGroup(UserGroup group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((functionId == null) ? 0 : functionId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ApplicationMenuAssignment other = (ApplicationMenuAssignment) obj;
		if (functionId == null) {
			if (other.functionId != null)
				return false;
		} else if (!functionId.equals(other.functionId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
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
		return "FunctionAssignment [id=" + id + ", functionId=" + functionId
				+ ", groupId=" + groupId + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		  
		 ApplicationMenu param5 = getFunction();   
		 if ( param5 != null){ 
		
 //1. Ok tampung dulu variable
			Application param5_application_tmp = param5.getApplication();
			PageDefinition param5_pageDefinition_tmp = param5.getPageDefinition();
//2. null kan variable 
			param5.setApplication(null);
			param5.setPageDefinition(null);
// 3 taruh ke json
			jsonContainer.put("function", param5);
//4. restore lagi 
			param5.setApplication(param5_application_tmp);
			param5.setPageDefinition(param5_pageDefinition_tmp);
		}
		jsonContainer.put("function",getFunction());
		jsonContainer.put("functionId",getFunctionId());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		jsonContainer.put("group",getGroup());
		jsonContainer.put("groupId",getGroupId());
		jsonContainer.put("id",getId());
	}
	
	@Override
	public ApplicationMenuAssignment instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationMenuAssignment retval = new ApplicationMenuAssignment();
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		  
		retval.setFunction( (ApplicationMenu)jsonContainer.get("function" ,  ApplicationMenu.class.getName()));
		retval.setFunctionId( (Long)jsonContainer.get("functionId" ,  Long.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setGroup( (UserGroup)jsonContainer.get("group" ,  UserGroup.class.getName()));
		retval.setGroupId( (Long)jsonContainer.get("groupId" ,  Long.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		return retval; 
	}
}