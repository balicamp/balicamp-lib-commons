/**
 * File Name : UserGroup.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
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
 * Entiti untuk tabel : sec_group
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:51:35 PM
 * @version $Id
 */
@Entity
@Table(name="sec_group")
public class UserGroup extends BaseAuditedObject implements IJSONFriendlyObject<UserGroup>{

	private static final long serialVersionUID = 8417245322597100469L;
	
	/**
	* group id<br/>
	* column :GROUP_ID
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id" , nullable=false )
	private Long id;
	/**
	* id applikasi<br/>
	* column :APPLICATION_ID
	**/
	@Column(name="app_id" , nullable=false)
	private Long applicationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="app_id", insertable=false, updatable=false)
	private Application application;
	
	/**
	* kode group.unik dalam 1 app<br/>
	* column :GROUP_CODE
	**/
	@Column(name="group_code" , length=32, nullable=false)
	private String groupCode;
	/**
	* nama group<br/>
	* column :GROUP_NAME
	**/
	@Column(name="group_name" , length=128, nullable=false)
	private String groupName;
	/**
	* Y=Yes, N=No<br/>
	* column :is_super_group
	**/
	@Column(name="is_super_group", length=1)
	private String superGroup;
	/**
	* status<br/>
	* column :data_status
	**/
	@Column(name="data_status",length=1)
	private String status;

	/**
	* group id<br/>
	* column :GROUP_ID
	**/
	public void setId(Long id){
	  this.id=id;
	}
	/**
	* group id<br/>
	* column :GROUP_ID
	**/
	public Long getId(){
	    return this.id;
	}
	/**
	* id applikasi<br/>
	* column :APPLICATION_ID
	**/
	public void setApplicationId(Long applicationId){
	  this.applicationId=applicationId;
	}
	/**
	* id applikasi<br/>
	* column :APPLICATION_ID
	**/
	public Long getApplicationId(){
	    return this.applicationId;
	}
	/**
	 * reference ke object Application {@link id.co.sigma.common.security.domain.Application}
	 * @return application
	 */
	public Application getApplication() {
		return application;
	}
	/**
	 * reference ke object Application {@link id.co.sigma.common.security.domain.Application}
	 * @param application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	/**
	* kode group.unik dalam 1 app<br/>
	* column :GROUP_CODE
	**/
	public void setGroupCode(String groupCode){
	  this.groupCode=groupCode;
	}
	/**
	* kode group.unik dalam 1 app<br/>
	* column :GROUP_CODE
	**/
	public String getGroupCode(){
	    return this.groupCode;
	}
	/**
	* nama group<br/>
	* column :GROUP_NAME
	**/
	public void setGroupName(String groupName){
	  this.groupName=groupName;
	}
	/**
	* nama group<br/>
	* column :GROUP_NAME
	**/
	public String getGroupName(){
	    return this.groupName;
	}
	/**
	* Y=Yes, N=No<br/>
	* column :IS_SUPER_GROUP
	**/
	public void setSuperGroup(String superGroup){
	  this.superGroup=superGroup;
	}
	/**
	* Y=Yes, N=No<br/>
	* column :IS_SUPER_GROUP
	**/
	public String getSuperGroup(){
	    return this.superGroup;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public void setStatus(String status){
	  this.status=status;
	}
	/**
	* status<br/>
	* column :STATUS
	**/
	public String getStatus(){
	    return this.status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result
				+ ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((superGroup == null) ? 0 : superGroup.hashCode());
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
		UserGroup other = (UserGroup) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (superGroup == null) {
			if (other.superGroup != null)
				return false;
		} else if (!superGroup.equals(other.superGroup))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", applicationId=" + applicationId
				+ ", application=" + application + ", groupCode=" + groupCode
				+ ", groupName=" + groupName + ", superGroup=" + superGroup
				+ ", status=" + status + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		  
		 Application param1 = getApplication();   
		 if ( param1 != null){ 
		
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("application", param1);
//4. restore lagi 
		}
		jsonContainer.put("application",getApplication());
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("groupCode",getGroupCode());
		jsonContainer.put("groupName",getGroupName());
		jsonContainer.put("id",getId());
		jsonContainer.put("modifiedBy",getModifiedBy());
		jsonContainer.put("modifiedByIPAddress",getModifiedByIPAddress());
		jsonContainer.put("modifiedOn",getModifiedOn());
		jsonContainer.put("status",getStatus());
		jsonContainer.put("superGroup",getSuperGroup());
	}
	
	@Override
	public UserGroup instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserGroup retval = new UserGroup();
		  
		retval.setApplication( (Application)jsonContainer.get("application" ,  Application.class.getName()));
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setGroupCode( (String)jsonContainer.get("groupCode" ,  String.class.getName()));
		retval.setGroupName( (String)jsonContainer.get("groupName" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setModifiedBy( (String)jsonContainer.get("modifiedBy" ,  String.class.getName()));
		retval.setModifiedByIPAddress( (String)jsonContainer.get("modifiedByIPAddress" ,  String.class.getName()));
		retval.setModifiedOn( (Date)jsonContainer.get("modifiedOn" ,  Date.class.getName()));
		retval.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		retval.setSuperGroup( (String)jsonContainer.get("superGroup" ,  String.class.getName()));
		return retval; 
	}
}