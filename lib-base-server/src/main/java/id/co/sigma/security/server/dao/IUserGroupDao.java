package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserGroup;
import id.co.sigma.common.security.domain.UserGroupAssignment;


import java.util.Collection;
import java.util.List;

/**
 * User group Dao
 * @author I Gede Mahendra
 * @since Nov 26, 2012, 5:36:44 PM
 * @version $Id
 */
public interface IUserGroupDao {
	
	/**
	 * Get all user group
	 * @param parameter
	 * @param pagePosition
	 * @param pageSize
	 * @return list of user group
	 * @throws Exception
	 */
	public List<UserGroup> getAllUserGroup(UserGroup parameter, int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Count all user group
	 * @param parameter
	 * @return Integer
	 * @throws Exception
	 */
	public Integer countAllUserGroup(UserGroup parameter) throws Exception;
	
	/**
	 * Get user group by parameter
	 * @param parameter 
	 * @return UserGroup
	 * @throws Exception
	 */
	public UserGroup getUserGroupByParameter(UserGroup parameter) throws Exception;
	
	/**
	 * membaca daftar groups dari user dalam 1 aplikasi
	 * @param userId id user
	 * @param applicationid id applikasi
	 * 
	 **/
	public List<String> getUserGroups(Long userId , Long applicationid); 
	
	/**
	 * Update user group
	 * @param data
	 * @throws Exception
	 */
	public void updateUserGroup(UserGroup data) throws Exception;
	
	
	
	
	/**
	 * membaca data user dengan id dari user
	 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 **/
	public User getUserById (Long userId ) ; 
	
	
	
	
	/**
	 * membaca data user group dengan berdasar pada 
	 * @param codes kode dari group yang di baca
	 */
	public List<UserGroup> getUserGroupByCodes (Collection<String> codes ) ;
	
	
	
	/**
	 * membaca data user group assigment dengan user id list
	 * @param userIds daftar user ids dari user
	 */
	public List<UserGroupAssignment> getUserGroupByUserIds (Collection<Long> userIds ) ; 
	
	public UserGroup getUserGroupByCode(String code) ;
	
}