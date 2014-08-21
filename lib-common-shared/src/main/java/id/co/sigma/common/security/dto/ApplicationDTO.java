package id.co.sigma.common.security.dto;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Application DTO
 * @author I Gede Mahendra
 * @since Dec 28, 2012, 11:02:33 AM
 * @version $Id
 */
public class ApplicationDTO implements Serializable,IsSerializable, IJSONFriendlyObject<ApplicationDTO>{

	private static final long serialVersionUID = -7170563672591601672L;
	
	private Long id;
	private String applicationCode;
	private String applicationName;
	private String applicationUrl;
	private String applicationNotifyUrl;
	private Boolean isActive;
	
	private String applicationLoginUrl;
	private Boolean isConcurentUser;
	
	private String userId;
	
	public ApplicationDTO() {
		super();
	}
	
	public ApplicationDTO(Long id, String appCode, String appName, String appUrl, Boolean active, String appLoginUrl, Boolean concurentUser){
		this.id = id;
		this.applicationCode = appCode;
		this.applicationName = appName;
		this.applicationUrl = appUrl;
		this.isActive = active;
		this.applicationLoginUrl = appLoginUrl;
		this.isConcurentUser = concurentUser;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getApplicationLoginUrl() {
		return applicationLoginUrl;
	}
	public void setApplicationLoginUrl(String applicationLoginUrl) {
		this.applicationLoginUrl = applicationLoginUrl;
	}
	public Boolean getIsConcurentUser() {
		return isConcurentUser;
	}
	public void setIsConcurentUser(Boolean isConcurentUser) {
		this.isConcurentUser = isConcurentUser;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplicationNotifyUrl() {
		return applicationNotifyUrl;
	}
	public void setApplicationNotifyUrl(String applicationNotifyUrl) {
		this.applicationNotifyUrl = applicationNotifyUrl;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("applicationCode",getApplicationCode());
		jsonContainer.put("applicationLoginUrl",getApplicationLoginUrl());
		jsonContainer.put("applicationName",getApplicationName());
		jsonContainer.put("applicationNotifyUrl",getApplicationNotifyUrl());
		jsonContainer.put("applicationUrl",getApplicationUrl());
		jsonContainer.put("id",getId());
		jsonContainer.put("isActive",getIsActive());
		jsonContainer.put("isConcurentUser",getIsConcurentUser());
		jsonContainer.put("userId",getUserId());
	}
	
	@Override
	public ApplicationDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationDTO retval = new ApplicationDTO();
		retval.setApplicationCode( (String)jsonContainer.get("applicationCode" ,  String.class.getName()));
		retval.setApplicationLoginUrl( (String)jsonContainer.get("applicationLoginUrl" ,  String.class.getName()));
		retval.setApplicationName( (String)jsonContainer.get("applicationName" ,  String.class.getName()));
		retval.setApplicationNotifyUrl( (String)jsonContainer.get("applicationNotifyUrl" ,  String.class.getName()));
		retval.setApplicationUrl( (String)jsonContainer.get("applicationUrl" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setIsActive( (Boolean)jsonContainer.get("isActive" ,  Boolean.class.getName()));
		retval.setIsConcurentUser( (Boolean)jsonContainer.get("isConcurentUser" ,  Boolean.class.getName()));
		retval.setUserId( (String)jsonContainer.get("userId" ,  String.class.getName()));
		return retval; 
	}
}