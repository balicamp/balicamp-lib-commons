package id.co.sigma.security.server.service;

import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;


import java.util.List;

/**
 * Interface application user service
 * @author I Gede Mahendra
 * @since Dec 20, 2012, 10:32:27 AM
 * @version $Id
 */
public interface IApplicationUserService {
	
	/**
	 * Meng-count data pada tabel sec_application_user
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public Integer countApplicationUserByParameter(ApplicationUser parameter) throws Exception;
	
	/**
	 * Insert application user.Prosesnya : Insert ke tabel sec_application_user & sec_group_assignment
	 * @param data
	 * @param appicationId
	 * @param userId
	 * @param currentUser
	 * @throws Exception
	 */
	public void insertApplicationUser(List<UserGroupAssignmentDTO> data,  Long applicationId, Long userId, String currentUser) throws Exception;
	
	/**
	 * Delete application user. Prosesnya : Delete ke tabel sec_application_user & sec_group_assignment
	 * @param applicationId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteApplicationUser(Long applicationId, Long userId) throws Exception;
}