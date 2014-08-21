package id.co.sigma.security.server;


import id.co.sigma.security.server.service.BaseSecurityService;

/**
 * User details utils
 * @author I Gede Mahendra
 * @since Jan 16, 2013, 12:05:38 PM
 * @version $Id
 */
public class UserDetailUtils extends BaseSecurityService{
	
	private static UserDetailUtils instance;
	
	/**
	 * Get singleton instance
	 */
	public static UserDetailUtils getInstance(){
		if(instance == null){
			return new UserDetailUtils();
		}
		return instance;
	}
}