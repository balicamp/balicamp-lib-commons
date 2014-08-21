/**
 * 
 */
package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.security.domain.ApplicationMenuAssignment;
import id.co.sigma.security.server.dao.impl.FunctionAssignmentDaoImpl;
import id.co.sigma.security.server.service.IFunctionAssignmentService;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * service untuk function assignment 
 * @author Dode
 * @version $Id
 * @since Jan 8, 2013, 2:37:12 PM
 */
@Service
public class FunctionAssignmentServiceImpl implements
		IFunctionAssignmentService {

	@Autowired
	private FunctionAssignmentDaoImpl functionAssignmentDao;
	
	@Override
	public List<Long> getFunctionIdByGroupId(Long groupId)
			throws Exception {
		List<Long> groupIds = new ArrayList<Long>();
		groupIds.add(groupId);
		return functionAssignmentDao.getFunctionIdByGroupId(groupIds);
	}

	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	@Override
	public void addRemoveFunctionAssignment(List<ApplicationMenuAssignment> addedData,
			List<ApplicationMenuAssignment> removedData) throws Exception {
		//add data
		if (addedData != null && !addedData.isEmpty())
			functionAssignmentDao.inserts(addedData);
		
		//remove data
		if (removedData != null && !removedData.isEmpty()) {
			List<ApplicationMenuAssignment> dbRemovedData = functionAssignmentDao.getFunctionAssignmentByIdAndGroupId(removedData);
			functionAssignmentDao.delete(dbRemovedData);
		}
	}
}
