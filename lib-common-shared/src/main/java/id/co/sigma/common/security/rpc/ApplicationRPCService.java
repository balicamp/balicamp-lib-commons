/**
 * 
 */
package id.co.sigma.common.security.rpc;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.dto.ApplicationDTO;

import java.util.List;

/**
 * @author Dode
 * @version $Id
 * @since Dec 19, 2012, 2:47:42 PM
 */
//@RemoteServiceRelativePath(value="/sigma-rpc/application.app-rpc")
public interface ApplicationRPCService extends JSONSerializedRemoteService {
	
	/**
	 * get all application data
	 * @return list application
	 * @throws Exception
	 */
	public List<Application> getApplicationList() throws Exception;
	
	/**
	 * Get all application
	 * @author I Gede Mahendra
	 * @param pagePosition
	 * @param pageSize
	 * @return PagedResultHolder
	 * @throws Exception
	 */
	public PagedResultHolder<ApplicationDTO> getApplicationList(int pagePosition, int pageSize) throws Exception;
	
	/**
	 * Save atau update data
	 * @param data
	 * @throws Exception
	 */
	public void saveOrUpdate(ApplicationDTO data) throws Exception;
	
	
	/**
	 * current app, data detail nya spt bagaimana : 
	 * <ol>
	 * <li>Id applikasi</li>
	 * <li>tanggal aplikasi</li>
	 * 
	 * </ol>
	 **/
	public ApplicationDTO getCurrentAppApplicationInfo  () ; 
	
	
}
