package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.UserGroup;
import id.co.sigma.common.security.dto.UserGroupDTO;
import id.co.sigma.security.server.dao.impl.FunctionAssignmentDaoImpl;
import id.co.sigma.security.server.dao.impl.UserGroupAssignmentDaoImpl;
import id.co.sigma.security.server.dao.impl.UserGroupDaoImpl;
import id.co.sigma.security.server.service.IUserGroupService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User group service
 * @author I Gede Mahendra
 * @since Nov 26, 2012, 6:11:17 PM
 * @version $Id
 */
@Service
public class UserGroupService implements IUserGroupService{

	@Autowired
	private UserGroupDaoImpl userGroupDao;
	
	@Autowired
	private UserGroupAssignmentDaoImpl userGroupAssignmentDao;
	
	@Autowired
	private FunctionAssignmentDaoImpl functionAssignmentDao;
	
	@Transactional(readOnly=true)
	@Override
	public PagedResultHolder<UserGroupDTO> getAllUserGroup(UserGroup parameter,int pagePosition, int pageSize) throws Exception {
		
		PagedResultHolder<UserGroupDTO> retval = new PagedResultHolder<UserGroupDTO>();
		List<UserGroup> actualData = new ArrayList<UserGroup>();
		
		actualData = userGroupDao.getAllUserGroup(parameter, pagePosition, pageSize);
		Integer count = userGroupDao.countAllUserGroup(parameter);
		
		List<UserGroupDTO> holderData = new ArrayList<UserGroupDTO>();
		if(count == null || count == 0){
			return null;
		}
		
		for (UserGroup userGroup : actualData) {
			UserGroupDTO dto = new UserGroupDTO();
			dto.setId(userGroup.getId().intValue());
			dto.setGroupCode(userGroup.getGroupCode());
			dto.setGroupName(userGroup.getGroupName());
//			dto.setStatus(userGroup.getActiveFlag());
			dto.setSuperGroup(userGroup.getSuperGroup());
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
	public void insert(UserGroup parameter) throws Exception {
		parameter.setCreatedOn(new Date());
		userGroupDao.insert(parameter);
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(Long paramater) throws Exception {		
		/*DELETE sec_function_assignment*/
		functionAssignmentDao.deleteFunctionByGroupId(paramater);
		
		/*DELETE sec_group_assignment*/
		userGroupAssignmentDao.deleteGroupAssignmentByGroupId(paramater);
		
		/*DELETE sec_group*/
		UserGroup data = (UserGroup) userGroupDao.find(UserGroup.class, paramater);
		userGroupDao.delete(data);
	}

	@Transactional(readOnly=true)
	@Override
	public UserGroupDTO getUserGroupByParameter(UserGroup parameter) throws Exception {	
		UserGroup userGroup = userGroupDao.getUserGroupByParameter(parameter);
		UserGroupDTO dto = new UserGroupDTO();
		dto.setId(userGroup.getId().intValue());
		dto.setGroupCode(userGroup.getGroupCode());
		dto.setGroupName(userGroup.getGroupName());
		dto.setStatus(userGroup.getActiveFlag());
		dto.setSuperGroup(userGroup.getSuperGroup());
		dto.setApplicationId(userGroup.getApplicationId());
		return dto;
	}

	@Transactional(readOnly=false)
	@Override
	public void update(UserGroup parameter) throws Exception {		
		parameter.setModifiedOn(new Date());
		userGroupDao.updateUserGroup(parameter);
	}
}