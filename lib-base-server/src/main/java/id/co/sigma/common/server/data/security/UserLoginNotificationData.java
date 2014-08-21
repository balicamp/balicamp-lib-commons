package id.co.sigma.common.server.data.security;

import java.io.Serializable;
import java.util.Date;



/**
 * wrapper data user
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class UserLoginNotificationData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7723065846409443066L;
	private String userName ; 
	private String uuid ;
	private String fullName ;
	private String branchCode ; 
	private String branchName ; 
	private String email ;
	
	
	/**
	 * groups di mana user terdaftar. ini untuk menentukan translasi dari user groups vs spring security code authority
	 **/
	protected String[] userGroups ; 
	
	
	/**
	 * host dari current user
	 **/
	private String userHost ; 
	
	private Date notificationTime ; 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLoginNotificationData other = (UserLoginNotificationData) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserLoginNotificationData [userName=" + userName + ", uuid="
				+ uuid + ", fullName=" + fullName + ", branchCode="
				+ branchCode + ", branchName=" + branchName + ", email="
				+ email + "]";
	}
	public Date getNotificationTime() {
		return notificationTime;
	}
	public void setNotificationTime(Date notificationTime) {
		this.notificationTime = notificationTime;
	}
	/**
	 * host dari current user
	 **/
	public String getUserHost() {
		return userHost;
	}
	/**
	 * host dari current user
	 **/
	public void setUserHost(String userHost) {
		this.userHost = userHost;
	} 
	
	/**
	 * groups di mana user terdaftar. ini untuk menentukan translasi dari user groups vs spring security code authority
	 **/
	public void setUserGroups(String[] userGroups) {
		this.userGroups = userGroups;
	}
	/**
	 * groups di mana user terdaftar. ini untuk menentukan translasi dari user groups vs spring security code authority
	 **/
	public String[] getUserGroups() {
		return userGroups;
	}
	

}
