package id.co.sigma.common.security.domain;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gwt.user.client.rpc.IsSerializable;

@Entity
@Table(name="sec_application_user")
public class ApplicationUser implements Serializable,IsSerializable, IJSONFriendlyObject<ApplicationUser>{

	private static final long serialVersionUID = -8494783370123006468L;
	
	@EmbeddedId
	private ApplicationUserKey applicationUser;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="application_id", insertable=false, updatable=false)
	private Application application;
	
	/**
	 * ApplicationUserKey 
	 */
	public ApplicationUserKey getApplicationUser() {
		return applicationUser;
	}

	/**
	 * ApplicationUserKey 
	 */
	public void setApplicationUser(ApplicationUserKey applicationUser) {
		this.applicationUser = applicationUser;
	}
	
	/**
	* id user<br/>
	* object User
	**/
	public User getUser() {
		return user;
	}

	/**
	* id user<br/>
	* object User
	**/
	public void setUser(User user) {
		this.user = user;
	}

	/**
	* id application<br/>
	* object aplication
	**/
	public Application getApplication() {
		return application;
	}

	/**
	* id application<br/>
	* object aplication
	**/
	public void setApplication(Application application) {
		this.application = application;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		  
		 Application param1 = getApplication();   
		 if ( param1 != null){ 
		
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("application", param1);
//4. restore lagi 
		}
		jsonContainer.put("application",getApplication());
		//  
		 ApplicationUserKey param2 = getApplicationUser();   
		 if ( param2 != null){ 
		//
 //1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json
			jsonContainer.put("applicationUser", param2);
//4. restore lagi 
		}
		jsonContainer.put("applicationUser",getApplicationUser());
		  
		 User param4 = getUser();   
		 if ( param4 != null){
 //1. Ok tampung dulu variable
			Application param4_defaultApplication_tmp = param4.getDefaultApplication();
//2. null kan variable 
			param4.setDefaultApplication(null);
// 3 taruh ke json
			jsonContainer.put("user", param4);
//4. restore lagi 
			param4.setDefaultApplication(param4_defaultApplication_tmp);
		}
		jsonContainer.put("user",getUser());
	}
	
	@Override
	public ApplicationUser instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		ApplicationUser retval = new ApplicationUser();
		  
		retval.setApplication( (Application)jsonContainer.get("application" ,  Application.class.getName()));
		//  
		retval.setApplicationUser( (ApplicationUserKey)jsonContainer.get("applicationUser" ,  ApplicationUserKey.class.getName()));
		retval.setUser( (User)jsonContainer.get("user" ,  User.class.getName()));
		return retval; 
	}
}