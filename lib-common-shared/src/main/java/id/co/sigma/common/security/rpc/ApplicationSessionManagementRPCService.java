package id.co.sigma.common.security.rpc;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.ApplicationSessionRegistry;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ApplicationSessionManagementRPCService extends JSONSerializedRemoteService{
	
	/**
	 * membaca data user yang sedang log in
	 * @param usernameWildCard username wild card
	 * @param pageSize ukuran page per pembacaan
	 * @param page page berapa yang hendak di baca
	 *  
	 */
	public PagedResultHolder<ApplicationSessionRegistry> getCurrentlyLogedInUser (String usernameWildCard ,String realNameWildCard , String email ,  int pageSize , int page );
	
	
	/**
	 * paksa session di loggof
	 * @param sessionId id dari session yang di paksa logoff
	 */
	public void forceLogoff( String sessionId ) ; 

}
