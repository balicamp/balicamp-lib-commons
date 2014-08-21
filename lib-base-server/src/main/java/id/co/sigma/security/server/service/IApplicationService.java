/**
 * 
 */
package id.co.sigma.security.server.service;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.dto.ApplicationDTO;


import java.util.List;

/**
 * service untuk application
 * @author Dode
 * @version $Id
 * @since Dec 19, 2012, 3:02:31 PM
 */
public interface IApplicationService {
	
	/**
	 * mendapatkan semua data application
	 * @return list of application
	 * @throws Exception
	 */
	public List<Application> getApplicationList() throws Exception;
	
	
	
	/**
	 * application current. data detail nya spt apa
	 **/
	public Application getCurrentAppDetailData () ; 
	
	/**
	 * Get all aplication list with paging
	 * @author I Gede Mahendra
	 * @param pagePosition
	 * @param pageSize
	 * @return PagedResultHolder
	 * @throws Exception
	 */
	public PagedResultHolder<ApplicationDTO> getApplicationList(int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Save or update
	 * @param data
	 * @throws Exception
	 */
	public void saveOrUpdate(ApplicationDTO data) throws Exception;
	
	/**
	 * Id app current, refer ke {@link #currentApplicationIdAsString}
	 **/
	public Long getCurrentApplicationId() ; 
}
