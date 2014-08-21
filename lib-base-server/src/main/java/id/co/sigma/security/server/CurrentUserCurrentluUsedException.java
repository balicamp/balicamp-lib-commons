package id.co.sigma.security.server;



/**
 * ini di raise kalau login sedang di pergunakan dan policy tidak mengijinkan 
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class CurrentUserCurrentluUsedException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3376110130332802506L;
	
	
	
	/**
	 * ip saat ini
	 **/
	private String currentIp ;
	
	/**
	 * browser user saat ini
	 **/
	private String currentUserAgent ; 
	
	/**
	 * browser user saat ini
	 **/
	public CurrentUserCurrentluUsedException(String message){
		super(message);
	}
	
	public CurrentUserCurrentluUsedException(String message, String currentIp , String currentBrowser){
		super(message);
	}
	
	

	/**
	 * ip saat ini
	 **/
	public String getCurrentIp() {
		return currentIp;
	}


	/**
	 * ip saat ini
	 **/
	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}


	public String getCurrentUserAgent() {
		return currentUserAgent;
	}

	/**
	 * browser user saat ini
	 **/
	public void setCurrentUserAgent(String currentUserAgent) {
		this.currentUserAgent = currentUserAgent;
	}


	
	
	

}
