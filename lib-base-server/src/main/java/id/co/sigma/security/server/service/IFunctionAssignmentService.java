/**
 * 
 */
package id.co.sigma.security.server.service;

import id.co.sigma.common.security.domain.ApplicationMenuAssignment;


import java.util.List;



/**
 * service untuk function assignment
 * @author Dode
 * @version $Id
 * @since Jan 8, 2013, 2:33:43 PM
 */
public interface IFunctionAssignmentService {
	
	/**
	 * get function id by group id
	 * @param groupId group id
	 * @return list of function id
	 * @throws Exception
	 */
	public List<Long> getFunctionIdByGroupId(Long groupId) throws Exception;
	
	/**
	 * add and remove function assignment data
	 * @param addedData list of added data
	 * @param removedData list of removed data
	 * @throws Exception
	 */
	public void addRemoveFunctionAssignment(List<ApplicationMenuAssignment> addedData, List<ApplicationMenuAssignment> removedData) throws Exception;
}
