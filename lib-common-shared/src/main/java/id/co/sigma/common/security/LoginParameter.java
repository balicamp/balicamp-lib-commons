/**
 * File Name : LoginParameter.java
 * Package   : id.co.sigma.arium.security.shared
 * Project   : security-shared
 */
package id.co.sigma.common.security;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Wrapper untuk menampung parameter login
 * @author I Gede Mahendra
 * @since Nov 19, 2012, 11:49:57 AM
 * @version $Id
 */
public class LoginParameter implements Serializable,IsSerializable, IJSONFriendlyObject<LoginParameter>{

	private static final long serialVersionUID = -8171301692870940153L;
	
	private Long userId;
	private String username;
	private String password;
	private Long applicationId;
	
	
	
	public LoginParameter(){
		
	}
	
	
	/**
	 * browser dari user
	 **/
	private String userBrowser ; 
	
	

	/**
	 * nama host dari user
	 **/
	private String userHostName ; 
	
	
	/**
	 * browser dari user
	 **/
	public String getUserBrowser() {
		return userBrowser;
	}
	/**
	 * browser dari user
	 **/
	public void setUserBrowser(String userBrowser) {
		this.userBrowser = userBrowser;
	}
	
	/**
	 * nama host dari user
	 **/
	public String getUserHostName() {
		return userHostName;
	}
	/**
	 * nama host dari user
	 **/
	public void setUserHostName(String userHostName) {
		this.userHostName = userHostName;
	}
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
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicationId == null) ? 0 : applicationId.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		LoginParameter other = (LoginParameter) obj;
		if (applicationId == null) {
			if (other.applicationId != null)
				return false;
		} else if (!applicationId.equals(other.applicationId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "LoginParameter [username=" + username + ", password="
				+ password + ", applicationId=" + applicationId + "]";
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("applicationId",getApplicationId());
		jsonContainer.put("password",getPassword());
		jsonContainer.put("userBrowser",getUserBrowser());
		jsonContainer.put("userHostName",getUserHostName());
		jsonContainer.put("userId",getUserId());
		jsonContainer.put("username",getUsername());
	}
	
	@Override
	public LoginParameter instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LoginParameter retval = new LoginParameter();
		retval.setApplicationId( (Long)jsonContainer.get("applicationId" ,  Long.class.getName()));
		retval.setPassword( (String)jsonContainer.get("password" ,  String.class.getName()));
		retval.setUserBrowser( (String)jsonContainer.get("userBrowser" ,  String.class.getName()));
		retval.setUserHostName( (String)jsonContainer.get("userHostName" ,  String.class.getName()));
		retval.setUserId( (Long)jsonContainer.get("userId" ,  Long.class.getName()));
		retval.setUsername( (String)jsonContainer.get("username" ,  String.class.getName()));
		return retval; 
	}
}