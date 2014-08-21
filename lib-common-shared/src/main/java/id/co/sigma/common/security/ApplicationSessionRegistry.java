package id.co.sigma.common.security;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.util.Date;

/**
 * holder data session registry. register siapa saja yang sedang login
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ApplicationSessionRegistry implements IJSONFriendlyObject<ApplicationSessionRegistry> {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -894728445035398857L;


	/**
	 * id session. ini untuk force logout user
	 */
	private String sessionId ;
	
	
	/**
	 * nama user yang login
	 */
	private String userName ; 
	
	
	/**
	 * real name dari user.kalau ada
	 */
	private String realName ; 
	
	
	
	/**
	 * email dari user
	 */
	private String email ;
	
	
	/**
	 * kapan login pertama kali di lakukan
	 */
	private Date loginTime ; 
	
	
	/**
	 * IP address dari mana user login untuk session in
	 */
	private String ipAddress ; 
	
	
	/**
	 * id session. ini untuk force logout user
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * id session. ini untuk force logout user
	 */
	public String getSessionId() {
		return sessionId;
	}
	
	
	/**
	 * nama user yang login
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * nama user yang login
	 */
	public String getUserName() {
		return userName;
	}
	
	
	/**
	 * real name dari user.kalau ada
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * real name dari user.kalau ada
	 */
	public String getRealName() {
		return realName;
	}
	
	/**
	 * kapan login pertama kali di lakukan
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * kapan login pertama kali di lakukan
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * IP address dari mana user login untuk session in
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * IP address dari mana user login untuk session in
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	
	
	/**
	 * kapan login pertama kali di lakukan
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * kapan login pertama kali di lakukan
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("email", this.email);
		jsonContainer.put("ipAddress", this.ipAddress);
		jsonContainer.put("loginTime", this.loginTime);
		jsonContainer.put("realName", this.realName);
		jsonContainer.put("sessionId", this.sessionId);
		jsonContainer.put("userName", this.userName);
		
	}
	@Override
	public ApplicationSessionRegistry instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		ApplicationSessionRegistry retval = new ApplicationSessionRegistry(); 
		retval.email = jsonContainer.getAsString("email");
		retval.ipAddress = jsonContainer.getAsString("ipAddress");
		retval.loginTime = jsonContainer.getAsDate( "loginTime");
		retval.realName = jsonContainer.getAsString("realName");
		retval.userName = jsonContainer.getAsString("userName");
		retval.sessionId = jsonContainer.getAsString("sessionId");
		return retval;
	}
}
