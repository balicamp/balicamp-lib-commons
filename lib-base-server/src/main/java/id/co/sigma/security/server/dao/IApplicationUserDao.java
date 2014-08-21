package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.ApplicationUser;

/**
 * Interface Application User
 * @author I Gede Mahendra
 * @since Dec 20, 2012, 10:25:40 AM
 * @version $Id
 */
public interface IApplicationUserDao {
	
	/**
	 * Meng-count data di tabel sec_application_user berdasarkan parameter yg dimasukkan
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public Integer countApplicationUserByParameter(ApplicationUser parameter) throws Exception;
	
	/**
	 * Delete data dalam sec_application_user
	 * @param parameter
	 * @throws Exception
	 */
	public void delete(ApplicationUser parameter) throws Exception;
}