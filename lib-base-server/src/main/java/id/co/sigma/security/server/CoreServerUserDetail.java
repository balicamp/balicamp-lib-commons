package id.co.sigma.security.server;


import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * User Detail implement dari User Detail spring security
 * @author I Gede Mahendra
 * @since Jan 14, 2013, 6:07:32 PM
 * @version $Id
 */
public class CoreServerUserDetail implements UserDetails{

	private static final long serialVersionUID = -2716995867343635820L;
	
	/**
	 * ID PK dari user
	 **/
	private Long userInternalId ;
	
	/*Varible yg bersesuaian dg kebutuhan spring security*/			
	private String password;
	private String username;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Collection<CoreServerUserAuthority> authorities;
	
	/*Varibale tambahan yg dibutuhan oleh aplikasi*/
	private String applicationName;
	private String fullNameUser;
	private Long applicationId;
	private String applicationUrl;
	private String applicationLoginUrl;
	private String passwordNoHashing;
	private Date lastLogin;
	private String uuid;
	
	
	
	/**
	 * email dari user
	 */
	private String email ; 
		
	/**
	 * transient field, user ogin dari IP mana 
	 */
	private String ipAddress ; 
	
	
	
	
	
	/**
	 * kode cabang dari current 
	 */
	private String currentBranchCode ; 
	
	
	/**
	 * kapan login, logika nya pada saat object ini di buat, seputaran itu lah user login
	 */
	private Date loginTime = new Date() ; 
	
	
	@Override
	public Collection<CoreServerUserAuthority> getAuthorities() {		
		return authorities;
	}

	@Override
	public String getPassword() {		
		return password;
	}

	@Override
	public String getUsername() {		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {		
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {		
		return enabled;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
	
	/**
	 * ID PK dari user
	 **/
	public void setUserInternalId(Long userInternalId) {
		this.userInternalId = userInternalId;
	}
	/**
	 * ID PK dari user
	 **/
	public Long getUserInternalId() {
		return userInternalId;
	}

	/**
	 * Set authority
	 */
	public void setAuthorities(Collection<CoreServerUserAuthority> authorities) {
		this.authorities = authorities;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accountNonExpired ? 1231 : 1237);
		result = prime * result + (accountNonLocked ? 1231 : 1237);
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime
				* result
				+ ((applicationLoginUrl == null) ? 0 : applicationLoginUrl
						.hashCode());
		result = prime * result
				+ ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result
				+ ((applicationUrl == null) ? 0 : applicationUrl.hashCode());
		result = prime * result
				+ ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + (credentialsNonExpired ? 1231 : 1237);
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((fullNameUser == null) ? 0 : fullNameUser.hashCode());
		result = prime * result
				+ ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime
				* result
				+ ((passwordNoHashing == null) ? 0 : passwordNoHashing
						.hashCode());
		result = prime * result
				+ ((userInternalId == null) ? 0 : userInternalId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		CoreServerUserDetail other = (CoreServerUserDetail) obj;
		if (accountNonExpired != other.accountNonExpired)
			return false;
		if (accountNonLocked != other.accountNonLocked)
			return false;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (applicationLoginUrl == null) {
			if (other.applicationLoginUrl != null)
				return false;
		} else if (!applicationLoginUrl.equals(other.applicationLoginUrl))
			return false;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (applicationUrl == null) {
			if (other.applicationUrl != null)
				return false;
		} else if (!applicationUrl.equals(other.applicationUrl))
			return false;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (credentialsNonExpired != other.credentialsNonExpired)
			return false;
		if (enabled != other.enabled)
			return false;
		if (fullNameUser == null) {
			if (other.fullNameUser != null)
				return false;
		} else if (!fullNameUser.equals(other.fullNameUser))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (passwordNoHashing == null) {
			if (other.passwordNoHashing != null)
				return false;
		} else if (!passwordNoHashing.equals(other.passwordNoHashing))
			return false;
		if (userInternalId == null) {
			if (other.userInternalId != null)
				return false;
		} else if (!userInternalId.equals(other.userInternalId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
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
		return "SigmaUserDetail [userInternalId=" + userInternalId
				+ ", password=" + password + ", username=" + username
				+ ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired
				+ ", enabled=" + enabled + ", authorities=" + authorities
				+ ", applicationName=" + applicationName + ", fullNameUser="
				+ fullNameUser + ", applicationId=" + applicationId
				+ ", applicationUrl=" + applicationUrl
				+ ", applicationLoginUrl=" + applicationLoginUrl
				+ ", passwordNoHashing=" + passwordNoHashing + ", lastLogin="
				+ lastLogin + ", uuid=" + uuid + "]";
	}	
	
	/**
	 * transient field, user ogin dari IP mana 
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * transient field, user ogin dari IP mana 
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	
	/**
	 * kapan login, logika nya pada saat object ini di buat, seputaran itu lah user login
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * kapan login, logika nya pada saat object ini di buat, seputaran itu lah user login
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	 * email dari user
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email dari user
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * kode cabang dari current 
	 */
	public void setCurrentBranchCode(String currentBranchCode) {
		this.currentBranchCode = currentBranchCode;
	}
	/**
	 * kode cabang dari current 
	 */
	public String getCurrentBranchCode() {
		return currentBranchCode;
	}
}