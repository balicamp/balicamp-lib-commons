package id.co.sigma.security.server.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.common.security.dto.UserGroupDTO;
import id.co.sigma.security.server.dao.impl.UserGroupAssignmentDaoImpl;
import id.co.sigma.security.server.service.IUserGroupAssignmentService;

/**
 * 
 * @author I Gede Mahendra
 * @since Dec 7, 2012, 12:39:52 PM
 * @version $Id
 */
@Service
public class UserGroupAssignmentServiceImp implements IUserGroupAssignmentService{
	
	@Autowired
	private UserGroupAssignmentDaoImpl userGroupAssignmentDao;
	
	@Transactional(readOnly=true)
	@Override
	public PagedResultHolder<UserGroupAssignmentDTO> getGroupAssignmentByParameter(UserGroupAssignment parameter, int pagePosition, int pageSize) throws Exception {
		
		PagedResultHolder<UserGroupAssignmentDTO> retval = new PagedResultHolder<UserGroupAssignmentDTO>();
		List<UserGroupAssignment> actualData = new ArrayList<UserGroupAssignment>();
		
		actualData = userGroupAssignmentDao.getDataByParameter(parameter, pagePosition, pageSize);
		Integer count = userGroupAssignmentDao.countAllUserGroupAssignment(parameter);
		
		List<UserGroupAssignmentDTO> holderData = new ArrayList<UserGroupAssignmentDTO>();
		if(count == null || count == 0){
			return null;
		}
		
		for (UserGroupAssignment userGroup : actualData) {			
			UserGroupAssignmentDTO dto = new UserGroupAssignmentDTO();
			dto.setId(userGroup.getId());
			dto.setFullname(userGroup.getUser().getRealName());
			dto.setUsername(userGroup.getUser().getUserCode());
			holderData.add(dto);
		}
		
		retval.setHoldedData(holderData);
		retval.setPage(pagePosition);		
		retval.setPageSize(pageSize);
		retval.setTotalData(count);	
		retval.adjustPagination();	
		
		return retval;
	}

	@Transactional(readOnly=false)
	@Override
	public void insert(UserGroupAssignment data) throws Exception {
		data.setCreatedOn(new Date());
		userGroupAssignmentDao.insert(data);		
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(UserGroupAssignment data) throws Exception {
		UserGroupAssignment result = (UserGroupAssignment) userGroupAssignmentDao.find(data.getId());
		userGroupAssignmentDao.delete(result);	
	}

	@Transactional(readOnly=true)
	@Override
	public List<UserGroupAssignmentDTO> getGroupByUserId(Long userId) throws Exception {
		List<UserGroupAssignmentDTO> result = null;
		List<UserGroupAssignment> resultData = userGroupAssignmentDao.getDataByUserId(userId);
		
		if(!resultData.isEmpty()){
			result = new ArrayList<UserGroupAssignmentDTO>();
			for (UserGroupAssignment userGroupAssignment : resultData) {
				UserGroupAssignmentDTO dto = new UserGroupAssignmentDTO();
				dto.setId(userGroupAssignment.getId());
				dto.setGroupId(userGroupAssignment.getGroupId());
				dto.setGroupCode(userGroupAssignment.getUserGroup().getGroupCode());
				dto.setGroupName(userGroupAssignment.getUserGroup().getGroupName());
				result.add(dto);
			}
		}
		return result;
	}

	@Transactional(readOnly=true)
	@Override
	public List<UserGroupDTO> getUserGroupByUserId(Long userId) throws Exception {
		List<UserGroupDTO> result = null;
		List<UserGroupAssignment> tempResult = userGroupAssignmentDao.getDataByUserId(userId);
		if(!tempResult.isEmpty()){
			result = new ArrayList<UserGroupDTO>();
			for (UserGroupAssignment data : tempResult) {
				UserGroupDTO dto = new UserGroupDTO();
				dto.setId(data.getUserGroup().getId().intValue());
				dto.setGroupCode(data.getUserGroup().getGroupCode());
				dto.setGroupName(data.getUserGroup().getGroupName());
				result.add(dto);
			}
		}
		return result;
	}
}