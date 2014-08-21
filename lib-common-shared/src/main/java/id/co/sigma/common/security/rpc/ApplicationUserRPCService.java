package id.co.sigma.common.security.rpc;


import java.util.List;

import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.common.security.menu.ApplicationMenuSecurity;



/**
 * Application user RPC Service
 * @author I Gede Mahendra
 * @since Dec 20, 2012, 10:36:11 AM
 * @version $Id
 */
public interface ApplicationUserRPCService extends JSONSerializedRemoteService {
	
	/**
	 * Count application user
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public Integer countApplicationUserByParameter(ApplicationUser parameter) throws Exception;
	
	/**
	 * Insert or update user assignment
	 * @param data
	 * @param applicationId
	 * @param userId
	 * @param currentUser
	 * @throws Exception
	 */
	public void insertOrUpdate(List<UserGroupAssignmentDTO> data, Long applicationId, Long userId, String currentUser) throws Exception;
	
	/**
	 * Delete application user dan user group assignment
	 * @param applicationId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteApplicationUser(Long applicationId, Long userId) throws Exception;
	
	/**
	 * Get application menu berdasarkan user yg login pada signon data
	 * @param data
	 * @return List of ApplicationSecurity
	 * @throws Exception
	 */
	public List<ApplicationMenuSecurity> getApplicationMenu(Signon data) throws Exception;
	
	
	
}