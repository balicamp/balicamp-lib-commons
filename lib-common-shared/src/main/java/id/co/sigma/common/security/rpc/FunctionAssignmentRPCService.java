/**
 * 
 */
package id.co.sigma.common.security.rpc;


import id.co.sigma.common.rpc.JSONSerializedRemoteService;
import id.co.sigma.common.security.domain.ApplicationMenuAssignment;


import java.util.List;

/**
 * rpc untuk function assignment
 * @author Dode
 * @version $Id
 * @since Jan 8, 2013, 2:14:30 PM
 */
public interface FunctionAssignmentRPCService extends JSONSerializedRemoteService {
	
	
	
	
	/**
	 * get function id by group id
	 * @param groupId group id
	 * @return list of function id
	 * @throws Exception
	 */
	public List<Long> getFunctionIdByGroupId(Long groupId) throws Exception;
	
	/**
	 * added by dode
	 * add and remove menu item from group menu item
	 * @param addedMenuItem list of added menu item
	 * @param removedMenuItem list of removed menu item
	 * @throws Exception
	 */
	public void addRemoveFunctionAssignment(List<ApplicationMenuAssignment> addedMenuItem, List<ApplicationMenuAssignment> removedMenuItem) throws Exception;
}
