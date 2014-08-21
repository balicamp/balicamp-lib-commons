package id.co.sigma.common.server.data.security;

import org.springframework.security.core.GrantedAuthority;


/**
 * wrapper authority
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class CoreComponentAuthority implements GrantedAuthority{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4592877226590205303L;
	/**
	 *  kode auth
	 **/
	private String authority ;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	} 

	

}
