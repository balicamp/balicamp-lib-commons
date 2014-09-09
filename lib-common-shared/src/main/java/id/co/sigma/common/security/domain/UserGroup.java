/**
 * File Name : UserGroup.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
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

/**
 * Entiti untuk tabel : sec_group
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 2:51:35 PM
 * @version $Id
 */
@Entity
@Table(name="sec_group")
public class UserGroup extends BaseAuditedObject implements SingleKeyEntityData<Long>, IJSONFriendlyObject<UserGroup>{

	private static final long serialVersionUID = 8417245322597100469L;
	
	/**
	* group id<br/>
	* column :GROUP_ID
	**/
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id")
	private Long id;
	
	/**
	* id applikasi<br/>
	* column :APPLICATION_ID
	**/
	@Column(name="app_id" , nullable=false)
	private Long applicationId = 1L; 
	
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
	private String superGroup = "N";
	
	/**
	* status<br/>
	* column :data_status
	**/
	@Column(name="data_status",length=1)
	private String activeFlag = "A";

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
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		  
		 Application param1 = getApplication();   
		 if ( param1 != null){
			jsonContainer.put("application", param1);
		}
		jsonContainer.put("application",getApplication());
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
//		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("groupCode",getGroupCode());
		jsonContainer.put("groupName",getGroupName());
		jsonContainer.put("id",getId());
		jsonContainer.put("modifiedBy",getModifiedBy());
//		jsonContainer.put("modifiedByIPAddress",getModifiedByIPAddress());
		jsonContainer.put("modifiedOn",getModifiedOn());
//		jsonContainer.put("activeFlag",getActiveFlag());
		jsonContainer.put("superGroup",getSuperGroup());
	}

	@Override
	public UserGroup instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
	

}