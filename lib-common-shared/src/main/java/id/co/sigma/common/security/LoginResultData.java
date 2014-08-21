/**
 * File Name : LoginResultData.java
 * Package   : id.co.sigma.arium.security.shared
 * Project   : security-shared
 */
package id.co.sigma.common.security;

import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author I Gede Mahendra
 * @since Nov 20, 2012, 12:37:40 PM
 * @version $Id
 */
public class LoginResultData implements Serializable,IsSerializable, IJSONFriendlyObject<LoginResultData>{

	private static final long serialVersionUID = 5236017246388213761L;
	
	private String uuid;
	private String username ;
	
	
	/**
	 * data user sign on
	 **/
	private Signon sigonData ; 
	
	private Integer errorCode;
	
	
	 
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * data user sign on
	 **/
	public Signon getSigonData() {
		return sigonData;
	}
	/**
	 * data user sign on
	 **/
	public void setSigonData(Signon sigonData) {
		this.sigonData = sigonData;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("errorCode",getErrorCode());
		  
		 Signon param3 = getSigonData();   
		 if ( param3 != null){ 
		
 //1. Ok tampung dulu variable
			Application param3_application_tmp = param3.getApplication();
			User param3_user_tmp = param3.getUser();
//2. null kan variable 
			param3.setApplication(null);
			param3.setUser(null);
// 3 taruh ke json
			jsonContainer.put("sigonData", param3);
//4. restore lagi 
			param3.setApplication(param3_application_tmp);
			param3.setUser(param3_user_tmp);
		}
		jsonContainer.put("sigonData",getSigonData());
		jsonContainer.put("username",getUsername());
		jsonContainer.put("uuid",getUuid());
	}
	
	@Override
	public LoginResultData instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LoginResultData retval = new LoginResultData();
		retval.setErrorCode( (Integer)jsonContainer.get("errorCode" ,  Integer.class.getName()));
		  
		retval.setSigonData( (Signon)jsonContainer.get("sigonData" ,  Signon.class.getName()));
		retval.setUsername( (String)jsonContainer.get("username" ,  String.class.getName()));
		retval.setUuid( (String)jsonContainer.get("uuid" ,  String.class.getName()));
		return retval; 
	}
}