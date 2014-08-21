package id.co.sigma.security.server.service;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.dto.UserDTO;
import id.co.sigma.common.security.exception.PasswordPolicyException;


/**
 * User service
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 1:58:42 PM
 * @version $Id
 */
public interface IUserService {
	
	/**
	 * Get all user
	 * @param parameter
	 * @param pagePosition
	 * @param pageSize
	 * @return PageResultHolder
	 * @throws Exception
	 */
	public PagedResultHolder<UserDTO> getUserByParameter(SimpleQueryFilter[] filter, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Get user yg diperuntukkan untuk worklist user
	 * @param filter
	 * @param pagePosition
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PagedResultHolder<UserDTO> getUserAtWorklistByParam(Long applicationId,SimpleQueryFilter[] filter, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * get user by filter
	 * @param filters filter searching
	 * @param page page data yang ingin di tampilkan
	 * @param pageSize size data per page
	 * @return data user
	 * @throws Exception
	 */
	public PagedResultHolder<User> getUserByFilter(SimpleQueryFilter[] filters, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * insert data user
	 * @param user data user yang di insert
	 * @throws Exception
	 */
	public void insert(User data) throws Exception, PasswordPolicyException;
	
	/**
	 * update data user
	 * @param user data user yang di update
	 * @throws Exception
	 */
	public void update(User data) throws Exception;
	
	/**
	 * remove user
	 * @param id id user yang di remove
	 * @throws Exception
	 */
	public void remove(Long id) throws Exception;
	
	/**
	 * update password user dan insert password history user
	 * @param user
	 * @throws Exception
	 */
	public void updateUserPassword(User data) throws Exception, PasswordPolicyException;
	
	/**
	 * cek apakah user name sudah ada di db
	 * @param userCode user name yang dicari
	 * @return true jika sudah ada, false jika salah
	 * @throws Exception
	 */
	public Boolean isUserCodeExist(String userCode, Integer applicationId) throws Exception;
}
