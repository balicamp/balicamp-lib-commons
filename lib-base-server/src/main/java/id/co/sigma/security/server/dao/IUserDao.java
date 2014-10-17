package id.co.sigma.security.server.dao;

import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.domain.UserPassword;
import id.co.sigma.common.server.dao.IBaseDao;



import java.io.Serializable;
import java.util.List;

/**
 * User Dao Interface
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 1:45:38 PM
 * @version $Id
 */
public interface IUserDao extends IBaseDao{
	
	/**
	 * Get user by parameter
	 * @param parameter
	 * @param pagePosition
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserByParameter(User parameter, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Count user by parameter
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public Integer countUserByParameter(User parameter) throws Exception;
	
	/**
	 * insert ke table user 
	 * @param object
	 * @return untuk mendapatkan Id data user yg baru di insert
	 * @throws Exception
	 */
	public Serializable insertAndFlushWhitReturn(Serializable object) throws Exception;
	
	public void deleteUserRole(Long userId)throws Exception;
	/**
	 * Get application user berdasarkan application Id
	 * @param applicationId
	 * @param parameter
	 * @param pagePosition
	 * @param pageSize
	 * @return List of application user
	 * @throws Exception
	 */
	public List<ApplicationUser> getApplicationUser(Long applicationId, User parameter, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Get user by list of user id
	 * @param userIds
	 * @return list of user
	 * @throws Exception
	 */
	public List<User> getUserByUserId(List<Long> userIds) throws Exception;
	
	/**
	 * Get user group assignment berdasarkan user id
	 * @param userIds
	 * @return list of user group assignment
	 * @throws Exception
	 */
	public List<UserGroupAssignment> getGroupAssignmentByUserId(List<Long> userIds) throws Exception;
	
	/**
	 * get semua data user
	 * @param filters filters untuk querynya
	 * @return list data user
	 * @throws Exception
	 */
	public List<User> getUserByFilters(SimpleQueryFilter[] filters, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * count semua data user di db
	 * @param filters filter untuk query
	 * @return jumlah data user
	 * @throws Exception
	 */
	public Integer countUserByFilters(SimpleQueryFilter[] filters) throws Exception;
	
	/**
	 * get user by id
	 * @param id id user yang dicari
	 * @return data user yang bersesuaian
	 * @throws Exception
	 */
	public User getUserById(Long id) throws Exception;
	
	/**
	 * get user password by id
	 * @param id id Long
	 * @return list user password
	 * @throws Exception
	 */
	public List<UserPassword> getUserPasswordByUserId(Long id) throws Exception;
	
	/**
	 * cek apakah user name exist di database
	 * @param userCode user name yang dicek
	 * @return user data
	 * @throws Exception
	 */
	public User isUserNameExist(String userCode, Integer applicationId) throws Exception;

	public User getUserByUserName(String username);

	/**
	 * Mencari jumlah user dalam yg termasuk di dalam satu kode cabang.
	 * 
	 * @param branchCode default kode cabang
	 * 
	 * @return Jumlah user yg terdaftar dalam suatu kode cabang.
	 */
	public Long selectUserCountByBranchCode(String branchCode);
	
	/**
	 * Get user roles code
	 * @param id
	 * @return
	 */
	public String[] getUserRoles(Long id);
}