package id.co.sigma.common.security.menu;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Object yg menampung properti dari user domain dari windows authentification
 * @author I Gede Mahendra
 * @since Nov 29, 2012, 5:06:21 PM
 * @version $Id
 */
public class UserDomain implements Serializable,IsSerializable , SingleKeyEntityData<String>, IJSONFriendlyObject<UserDomain>{

	private static final long serialVersionUID = 4166232331873146397L;
	
	private String username;
	private String fullName;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}	
	
	
	@Override
	public String getId() {
		
		return username;
	}
	
	
	@Override
	public void setId(String id) {
		this.username = id; 
		
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("fullName",getFullName());
		jsonContainer.put("id",getId());
		jsonContainer.put("username",getUsername());
	}
	
	@Override
	public UserDomain instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserDomain retval = new UserDomain();
		retval.setFullName( (String)jsonContainer.get("fullName" ,  String.class.getName()));
		retval.setId( (String)jsonContainer.get("id" ,  String.class.getName()));
		retval.setUsername( (String)jsonContainer.get("username" ,  String.class.getName()));
		return retval; 
	}
}