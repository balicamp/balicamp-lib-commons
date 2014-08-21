package id.co.sigma.common.data.app.security;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * client data security. ini untuk keperluan 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class ClientSecurityData implements IsSerializable, IJSONFriendlyObject<ClientSecurityData> {
	
	
	/**
	 * username, yg di pergunakan untuk login
	 **/
	private String userName ; 
	
	/**
	 * fullname dari user
	 **/
	private String fullName ;
	/**
	 * kode unit
	 **/
	private String unitCode ;
	/**
	 * nama unit
	 **/
	private String unitName;
	/**
	 * token, ini untuk request security data
	 **/
	private String securityToken ;
	/**
	 * current application date
	 **/
	private Date currentApplicationDate ;
	
	/**
	 * username, yg di pergunakan untuk login
	 **/
	public String getUserName() {
		return userName;
	}
	/**
	 * username, yg di pergunakan untuk login
	 **/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * fullname dari user
	 **/
	public String getFullName() {
		return fullName;
	}
	/**
	 * fullname dari user
	 **/
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * kode unit
	 **/
	public String getUnitCode() {
		return unitCode;
	}
	/**
	 * kode unit
	 **/
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	/**
	 * nama unit
	 **/
	public String getUnitName() {
		return unitName;
	}
	/**
	 * nama unit
	 **/
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * token, ini untuk request security data
	 **/
	public String getSecurityToken() {
		return securityToken;
	}
	/**
	 * token, ini untuk request security data
	 **/
	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	/**
	 * current application date
	 **/
	public Date getCurrentApplicationDate() {
		return currentApplicationDate;
	}
	/**
	 * current application date
	 **/
	public void setCurrentApplicationDate(Date currentApplicationDate) {
		this.currentApplicationDate = currentApplicationDate;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("currentApplicationDate",getCurrentApplicationDate());
		jsonContainer.put("fullName",getFullName());
		jsonContainer.put("securityToken",getSecurityToken());
		jsonContainer.put("unitCode",getUnitCode());
		jsonContainer.put("unitName",getUnitName());
		jsonContainer.put("userName",getUserName());
	}
	
	@Override
	public ClientSecurityData instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ClientSecurityData retval = new ClientSecurityData();
		retval.setCurrentApplicationDate( (Date)jsonContainer.get("currentApplicationDate" ,  Date.class.getName()));
		retval.setFullName( (String)jsonContainer.get("fullName" ,  String.class.getName()));
		retval.setSecurityToken( (String)jsonContainer.get("securityToken" ,  String.class.getName()));
		retval.setUnitCode( (String)jsonContainer.get("unitCode" ,  String.class.getName()));
		retval.setUnitName( (String)jsonContainer.get("unitName" ,  String.class.getName()));
		retval.setUserName( (String)jsonContainer.get("userName" ,  String.class.getName()));
		return retval; 
	}
	
	
	
	
}
