package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.Application;

import java.util.List;

/**
 * 
 * @author Dode
 * @version $Id
 * @since Dec 19, 2012, 2:57:39 PM
 */
public interface IApplicationDao {
	
	/**
	 * mendapatkan semua data application
	 * @return list of application
	 * @throws Exception
	 */
	public List<Application> getApplicationList() throws Exception;
	
	/**
	 * get all application list with paging
	 * @author I Gede Mahendra
	 * @param pagePosition
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Application> getApplicationList(int pagePosition, int pageSize) throws Exception;
}
