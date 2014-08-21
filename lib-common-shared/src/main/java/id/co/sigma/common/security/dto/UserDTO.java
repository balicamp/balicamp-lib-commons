package id.co.sigma.common.security.dto;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;


import java.util.List;

/**
 * DTO untuk user
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 11:02:20 AM
 * @version $Id
 */
public class UserDTO implements  SingleKeyEntityData<Long>, IJSONFriendlyObject<UserDTO>{

	private static final long serialVersionUID = -4051054205378251757L;
	
	private Long idUser;
	private String username;
	private String fullName;
	private String email;
	private List<UserGroupDTO> userGroups;
		
	public UserDTO() {
		super();
	}	
	
	public UserDTO(Long id, String username, String fullName, String email){
		this.idUser = id;
		this.username = username;
		this.fullName = fullName;
		this.email = email;
	}
	
	public UserDTO(Long id, String username, String fullName, String email, List<UserGroupDTO> groups){
		this.idUser = id;
		this.username = username;
		this.fullName = fullName;		
		this.email = email;
		this.userGroups = groups;
	}
	
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<UserGroupDTO> getUserGroups() {
		return userGroups;
	}
	public void setUserGroups(List<UserGroupDTO> userGroups) {
		this.userGroups = userGroups;
	}

	@Override
	public Long getId() {
		return idUser;
	}

	@Override
	public void setId(Long id) {
		this.idUser = id ; 
		
	}	
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("email",getEmail());
		jsonContainer.put("fullName",getFullName());
		jsonContainer.put("id",getId());
		jsonContainer.put("idUser",getIdUser());
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		 
		  List<id.co.sigma.common.security.dto.UserGroupDTO> param6 = getUserGroups() ; 
		 if (  param6 != null && !param6.isEmpty()){ 
			for ( id.co.sigma.common.security.dto.UserGroupDTO scn : param6){
				//1. Ok tampung dulu variable
//2. null kan variable 
// 3 taruh ke json

					jsonContainer.appendToArray("userGroups", scn);
				//4. restore lagi 

				}
		}
		jsonContainer.put("username",getUsername());
	}
	
	@Override
	public UserDTO instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		UserDTO retval = new UserDTO();
		retval.setEmail( (String)jsonContainer.get("email" ,  String.class.getName()));
		retval.setFullName( (String)jsonContainer.get("fullName" ,  String.class.getName()));
		retval.setId( (Long)jsonContainer.get("id" ,  Long.class.getName()));
		retval.setIdUser( (Long)jsonContainer.get("idUser" ,  Long.class.getName()));
		/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe id.co.sigma.common.util.json.IJSONFriendlyObject*/		retval.setUsername( (String)jsonContainer.get("username" ,  String.class.getName()));
		return retval; 
	}
}