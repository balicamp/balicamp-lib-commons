package id.co.sigma.common.security.rpc;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.dto.BranchDTO;



/**
 * Branch list RPC Service
 * @author I Gede Mahendra
 * @since Jan 30, 2013, 3:34:57 PM
 * @version $Id
 */
//@RemoteServiceRelativePath(value="/sigma-rpc/branch-list.app-rpc")
public interface BranchRPCService extends JSONSerializedRemoteService{
	
	/**
	 * Get branch by parameter
	 * @param filter
	 * @param page
	 * @param pageSize
	 * @return pageResultHolder
	 */
	public PagedResultHolder<BranchDTO> getDataByParameter(SimpleQueryFilter[] filter, int page, int pageSize) throws Exception;
	
	/**
	 * Save or update data
	 * @param data
	 * @throws Exception
	 */
	public void saveOrUpdateBranch(Branch data) throws Exception; 
	
	/**
	 * Remove branch
	 * @param id
	 * @throws Exception
	 */
	public void remove(Long id) throws Exception;
}