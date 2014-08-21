package id.co.sigma.security.server;

import org.springframework.security.core.GrantedAuthority;

/**
 * Authority
 * @author I Gede Mahendra
 * @since Jan 29, 2013, 11:09:22 AM
 * @version $Id
 */
public class CoreServerUserAuthority implements GrantedAuthority{

	private static final long serialVersionUID = -4592877226590205303L;

	/**
	 *  kode auth
	 **/
	private String authority ;
	
	/**
	 * Constructor
	 */
	public CoreServerUserAuthority() {
		super();
	}
	
	/**
	 * Additional Constructor
	 * @param authority
	 */
	public CoreServerUserAuthority(CoreServerAuthorityEnum authority){
		this.authority = authority.toString();
	}
	
	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	} 
	
}
