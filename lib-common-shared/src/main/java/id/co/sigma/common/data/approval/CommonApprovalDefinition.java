/**
 * 
 */
package id.co.sigma.common.data.approval;

import id.co.sigma.common.data.ICreateAndModifyAuditedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * POJO untuk common approval definition 
 * @author <a href="mailto:gede.wibawa@sigma.co.id">Agus Gede Adipartha Wibawa</a>
 * @since Sep 24, 2013 1:26:34 PM
 */
@Entity
@Table(name="ct_common_approvable_definition")
public class CommonApprovalDefinition  implements Serializable , IJSONFriendlyObject<CommonApprovalDefinition>, ICreateAndModifyAuditedObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1281222973618232L;

	@Id
	@Column(name="fqcn", updatable=false, insertable=true)
	private String id;
	
	/**
	 * nama object
	 * column = obj_name
	 */
	@Column(name="obj_name", nullable=true)
	private String objectName;
	
	/**
	 * remark object
	 * column = obj_remark
	 */
	@Column(name="obj_remark", nullable=true)
	private String objectRemark;
	
	/**
	 * created by
	 * column = created_by
	 */
	@Column(name="created_by", nullable=true)
	private String createdBy;
	
	/**
	 * created on
	 * column = created_time
	 */
	@Column(name="created_time", nullable=true)
	private Date createdOn;
	
	/**
	 * creator ip address
	 * column = creator_ip
	 */
	@Column(name="creator_ip", nullable=true)
	private String creatorIpAddress;
	
	/**
	 * modified by
	 * column = last_modified_by
	 */
	@Column(name="last_modified_by", nullable=true)
	private String modifiedBy;
	
	/**
	 * modified on
	 * column = last_modified_time
	 */
	@Column(name="last_modified_time", nullable=true)
	private Date modifiedOn;
	
	/**
	 * modificator ip address
	 * column = last_modified_ip
	 */
	@Column(name="last_modified_ip", nullable=true)
	private String modifiedByIpAddress;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * nama object
	 * column = obj_name
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * nama object
	 * column = obj_name
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * remark object
	 * column = obj_remark
	 */
	public String getObjectRemark() {
		return objectRemark;
	}

	/**
	 * remark object
	 * column = obj_remark
	 */
	public void setObjectRemark(String objectRemark) {
		this.objectRemark = objectRemark;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainerData) {
		jsonContainerData.put("id",getId());
		jsonContainerData.put("objectName",getObjectName());
		jsonContainerData.put("objectRemark",getObjectRemark());
	}

	@Override
	public CommonApprovalDefinition instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		CommonApprovalDefinition retval = new CommonApprovalDefinition();
		retval.setId(jsonContainer.getAsString("id"));
		retval.setObjectName(jsonContainer.getAsString("objectName"));
		retval.setObjectRemark(jsonContainer.getAsString("objectRemark"));
		return retval;
	}

	@Override
	public void setCreatedBy(String username) {
		this.createdBy = username;
	}

	@Override
	public String getCreatedBy() {
		return this.createdBy;
	}

	@Override
	public void setCreatedOn(Date createTime) {
		this.createdOn = createTime;
	}

	@Override
	public Date getCreatedOn() {
		return this.createdOn;
	}

	@Override
	public void setCreatorIPAddress(String ipAddress) {
		this.creatorIpAddress = ipAddress;
	}

	@Override
	public String getCreatorIPAddress() {
		return this.creatorIpAddress;
	}

	@Override
	public void setModifiedBy(String username) {
		this.modifiedBy = username;
	}

	@Override
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	@Override
	public void setModifiedOn(Date modifyTime) {
		this.modifiedOn = modifyTime;
	}

	@Override
	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	@Override
	public void setModifiedByIPAddress(String ipAddress) {
		this.modifiedByIpAddress = ipAddress;
	}

	@Override
	public String getModifiedByIPAddress() {
		return this.modifiedByIpAddress;
	}

}
