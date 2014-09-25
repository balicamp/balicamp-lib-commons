/**
 * File Name : Application.java
 * Package   : id.co.sigma.arium.security.shared.domain
 * Project   : security-data
 */
package id.co.sigma.common.security.domain;

import java.util.Date;

import id.co.sigma.common.security.domain.audit.BaseAuditedObject;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entiti untuk tabel : sec_application
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 11:37:26 AM
 * @version $Id
 */
@Entity
@Table(name="sec_application")
public class Application extends BaseAuditedObject implements IJSONFriendlyObject<Application>{	

	private static final long serialVersionUID = 8389686936501266421L;

	@Id
	@Column(name="pk")
	private Long id;
	
	@Column(name="app_code", length=32)
	private String applicationCode;
	
	@Column(name="app_name", length=128)
	private String applicationName;
	
	@Column(name="app_url", length=512)
	private String applicationUrl;
	
	@Column(name="app_auth_login_url" , length=512)
	private String autentificationLoginUrl;
	
	
	
	
	@Column(name="data_status", length=1)
	private String status;
	
	
	
	
	
	
	
	
	/**
	 * ID applikasi<br>
	 * COLUMN : APPLICATION_ID
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * ID applikasi<br>
	 * COLUMN : APPLICATION_ID
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Kode applikasi<br>
	 * COLUMN : APPLICATION_CODE
	 * @return applicationCode
	 */
	public String getApplicationCode() {
		return applicationCode;
	}

	/**
	 * Kode applikasi<br>
	 * COLUMN : APPLICATION_CODE
	 * @param applicationCode
	 */
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	/**
	 * Nama applikasi<br>
	 * COLUMN : APPLICATION_NAME
	 * @return applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}

	/**
	 * Nama applikasi<br>
	 * COLUMN : APPLICATION_NAME
	 * @param applicationName
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	/**
	 * Status, active inactive<br>
	 * COLUMN : STATUS
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Status, active inactive<br>
	 * COLUMN : STATUS
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

	
	/**
	 * URL dari aplikasi<br>
	 * COLUMN : APPLICATION_URL
	 * @return applicationUrl
	 */
	public String getApplicationUrl() {
		return applicationUrl;
	}
	
	/**
	 * URL dari aplikasi<br>
	 * COLUMN : APPLICATION_URL
	 * @param applicationUrl
	 */

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
	
	/**
	 * Login URL<br>
	 * COLUMN : AUT_LOGIN_URL
	 * @return autentificationLoginUrl
	 */

	public String getAutentificationLoginUrl() {
		return autentificationLoginUrl;
	}
	
	/**
	 * Login URL<br>
	 * COLUMN : AUT_LOGIN_URL
	 * @param autentificationLoginUrl
	 */

	public void setAutentificationLoginUrl(String autentificationLoginUrl) {
		this.autentificationLoginUrl = autentificationLoginUrl;
	}
	

	
	
	
	
	public String generateNotificationAuthenticationUrl () {
		String app = this.getApplicationUrl() ; 
		if ( app.endsWith("/") ) 
			app = app.substring(0, app.length()-1);
		String path = "";
		if ( !path.startsWith("/"))
			path ="/" + path ; 
		return app + path ; 
	}
	
	
	
	/**
	 * seharusnya ini login.j, bagaimana mekanisme auto login
	 **/
	public String generateAutomaticLoginUrl () {
		String app = this.getApplicationUrl() ; 
		if ( app.endsWith("/") ) 
			app = app.substring(0, app.length()-1);
		String path = autentificationLoginUrl;
		if ( !path.startsWith("/"))
			path ="/" + path ; 
		return app + path ; 
	}
	
	

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("applicationCode",getApplicationCode());
		jsonContainer.put("applicationName",getApplicationName());
		jsonContainer.put("applicationUrl",getApplicationUrl());
		jsonContainer.put("autentificationLoginUrl",getAutentificationLoginUrl());
		jsonContainer.put("createdBy",getCreatedBy());
		jsonContainer.put("createdOn",getCreatedOn());
		jsonContainer.put("creatorIPAddress",getCreatorIPAddress());
		jsonContainer.put("id",getId());
		
		
		jsonContainer.put("modifiedBy",getModifiedBy());
		jsonContainer.put("modifiedByIPAddress",getModifiedByIPAddress());
		jsonContainer.put("modifiedOn",getModifiedOn());
		
		jsonContainer.put("status",getStatus());
	}
	@Override
	public Application instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		Application retval = new Application();
		retval.setApplicationCode( (String)jsonContainer.get("applicationCode" ,  String.class.getName()));
		retval.setApplicationName( (String)jsonContainer.get("applicationName" ,  String.class.getName()));
		retval.setApplicationUrl( (String)jsonContainer.get("applicationUrl" ,  String.class.getName()));
		retval.setAutentificationLoginUrl( (String)jsonContainer.get("autentificationLoginUrl" ,  String.class.getName()));
		retval.setCreatedBy( (String)jsonContainer.get("createdBy" ,  String.class.getName()));
		retval.setCreatedOn( (Date)jsonContainer.get("createdOn" ,  Date.class.getName()));
		retval.setCreatorIPAddress( (String)jsonContainer.get("creatorIPAddress" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		
		retval.setModifiedBy( (String)jsonContainer.get("modifiedBy" ,  String.class.getName()));
		retval.setModifiedByIPAddress( (String)jsonContainer.get("modifiedByIPAddress" ,  String.class.getName()));
		retval.setModifiedOn( (Date)jsonContainer.get("modifiedOn" ,  Date.class.getName()));
		
		retval.setStatus( (String)jsonContainer.get("status" ,  String.class.getName()));
		return retval; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((applicationCode == null) ? 0 : applicationCode.hashCode());
		result = prime * result
				+ ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result
				+ ((applicationUrl == null) ? 0 : applicationUrl.hashCode());
		result = prime
				* result
				+ ((autentificationLoginUrl == null) ? 0
						: autentificationLoginUrl.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (applicationCode == null) {
			if (other.applicationCode != null)
				return false;
		} else if (!applicationCode.equals(other.applicationCode))
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
		if (autentificationLoginUrl == null) {
			if (other.autentificationLoginUrl != null)
				return false;
		} else if (!autentificationLoginUrl
				.equals(other.autentificationLoginUrl))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}