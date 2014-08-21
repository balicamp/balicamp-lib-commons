package id.co.sigma.common.security.dto;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * DTO UserGroup
 * @author I Gede Mahendra
 * @since Nov 27, 2012, 10:34:18 AM
 * @version $Id
 */
public class UserGroupDTO implements Serializable,IsSerializable, IJSONFriendlyObject<UserGroupDTO>{

	private static final long serialVersionUID = 5959687907303113743L;
	
	private Integer id;	
	private String groupCode;
	private String groupName;
	private String status;
	private String superGroup;
	private Long applicationId;
	
	public UserGroupDTO(){
		super();
	}
	
	public UserGroupDTO(Integer id,String groupCode,String groupName,String isActive,String isSuperUser){
		this.id = id;
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.status = isActive;
		this.superGroup = isSuperUser;
	}	
	
	public UserGroupDTO(Integer id,String groupCode,String groupName){
		this.id = id;
		this.groupCode = groupCode;
		this.groupName = groupName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String isActive) {
		this.status = isActive;
	}
	public String getSuperGroup() {
		return superGroup;
	}
	public void setSuperGroup(String isSuperUser) {
		this.superGroup = isSuperUser;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("groupCode",getGroupCode());
		jsonContainer.put("groupName",getGroupName());
		jsonContainer.put("id",getId());
		jsonContainer.put("status",getStatus());
		jsonContainer.put("superGroup",getSuperGroup());
	}
	
	@Override
	public UserGroupDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserGroupDTO retval = new UserGroupDTO();
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setGroupCode( (String)jsonContainer.get("groupCode" ,  String.class.getName()));
		retval.setGroupName( (String)jsonContainer.get("groupName" ,  String.class.getName()));
		retval.setId( (Integer)jsonContainer.get("id" ,  Integer.class.getName()));
		retval.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		retval.setSuperGroup( (String)jsonContainer.get("superGroup" ,  String.class.getName()));
		return retval; 
	}
}