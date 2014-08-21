package id.co.sigma.common.security.dto;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * User detail DTO
 * @author I Gede Mahendra
 * @since Jan 16, 2013, 12:54:50 PM
 * @version $Id
 */
public class UserDetailDTO implements Serializable,IsSerializable, IJSONFriendlyObject<UserDetailDTO>{

	private static final long serialVersionUID = -3644491221492219875L;
		
	private Long userId;
	private String username;	
	private String password;
	private String applicationName;
	private String fullNameUser;
	private Long applicationId;
	private String applicationUrl;
	private String applicationLoginUrl;
	private String passwordNoHashing;
	private String uuid;
	private Date lastLogin;
	
	/**
	 * Nama cabang dari current branch
	 */
	private String currentBranchName;
	
	/**
	 * kode cabang dari currennt user
	 */
	private String currentBranch ; 
		
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getFullNameUser() {
		return fullNameUser;
	}
	public void setFullNameUser(String fullNameUser) {
		this.fullNameUser = fullNameUser;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationUrl() {
		return applicationUrl;
	}
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	public String getApplicationLoginUrl() {
		return applicationLoginUrl;
	}
	public void setApplicationLoginUrl(String applicationLoginUrl) {
		this.applicationLoginUrl = applicationLoginUrl;
	}
	public String getPasswordNoHashing() {
		return passwordNoHashing;
	}
	public void setPasswordNoHashing(String passwordNoHashing) {
		this.passwordNoHashing = passwordNoHashing;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}	
	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("applicationLoginUrl",getApplicationLoginUrl());
		jsonContainer.put("applicationName",getApplicationName());
		jsonContainer.put("applicationUrl",getApplicationUrl());
		jsonContainer.put("fullNameUser",getFullNameUser());
		jsonContainer.put("lastLogin",getLastLogin());
		jsonContainer.put("password",getPassword());
		jsonContainer.put("passwordNoHashing",getPasswordNoHashing());
		jsonContainer.put("userId",getUserId());
		jsonContainer.put("username",getUsername());
		jsonContainer.put("uuid",getUuid());
		jsonContainer.put("currentBranch", this.currentBranch);
		jsonContainer.put("currentBranchName",this.currentBranchName);
	}
	
	@Override
	public UserDetailDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserDetailDTO retval = new UserDetailDTO();
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setApplicationLoginUrl( (String)jsonContainer.get("applicationLoginUrl" ,  String.class.getName()));
		retval.setApplicationName( (String)jsonContainer.get("applicationName" ,  String.class.getName()));
		retval.setApplicationUrl( (String)jsonContainer.get("applicationUrl" ,  String.class.getName()));
		retval.setFullNameUser( (String)jsonContainer.get("fullNameUser" ,  String.class.getName()));
		retval.setLastLogin( (Date)jsonContainer.get("lastLogin" ,  Date.class.getName()));
		retval.setPassword( (String)jsonContainer.get("password" ,  String.class.getName()));
		retval.setPasswordNoHashing( (String)jsonContainer.get("passwordNoHashing" ,  String.class.getName()));
		retval.setUserId( (Long)jsonContainer.get("userId" ,  Long.class.getName()));
		retval.setUsername( (String)jsonContainer.get("username" ,  String.class.getName()));
		retval.setUuid( (String)jsonContainer.get("uuid" ,  String.class.getName()));
		retval.setCurrentBranch(jsonContainer.getAsString("currentBranch"));
		retval.setCurrentBranchName(jsonContainer.getAsString("currentBranchName"));
		return retval; 
	}
	
	/**
	 * cabang dari currennt user
	 */
	public void setCurrentBranch(String currentBranch) {
		this.currentBranch = currentBranch;
	}
	/**
	 * cabang dari currennt user
	 */
	public String getCurrentBranch() {
		return currentBranch;
	}
	
	public String getCurrentBranchName() {
		return currentBranchName;
	}
	public void setCurrentBranchName(String currentBranchName) {
		this.currentBranchName = currentBranchName;
	}
}