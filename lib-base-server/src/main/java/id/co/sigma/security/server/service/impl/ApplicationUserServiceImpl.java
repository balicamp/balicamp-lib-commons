package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.security.domain.ApplicationUserKey;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.dto.UserGroupAssignmentDTO;
import id.co.sigma.security.server.dao.impl.ApplicationUserDaoImpl;
import id.co.sigma.security.server.dao.impl.UserGroupAssignmentDaoImpl;
import id.co.sigma.security.server.service.IApplicationUserService;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application User Service
 * @author I Gede Mahendra
 * @since Dec 20, 2012, 10:34:18 AM
 * @version $Id
 */
@Service
public class ApplicationUserServiceImpl implements IApplicationUserService{

	@Autowired
	private ApplicationUserDaoImpl applicationUserDao;
	
	@Autowired
	private UserGroupAssignmentDaoImpl userGroupDao;
	
	@Transactional(readOnly=true)
	@Override
	public Integer countApplicationUserByParameter(ApplicationUser parameter) throws Exception {		
		return applicationUserDao.countApplicationUserByParameter(parameter);
	}

	@Transactional(readOnly=false)
	@Override
	public void insertApplicationUser(List<UserGroupAssignmentDTO> data, Long applicationId, Long userId, String currentUser) throws Exception {
		//1. CHECK sec_application_user apakah sudah ada atau tidak
		ApplicationUser appUser = new ApplicationUser();				
		ApplicationUserKey appUserKey = new ApplicationUserKey();
		
		appUser.setApplicationUser(appUserKey);
		appUserKey.setApplicationId(applicationId);
		appUserKey.setUserId(userId);
		
		Integer countApplicationUser = applicationUserDao.countApplicationUserByParameter(appUser);
		if(countApplicationUser == 0){
			//1.1 INSERT sec_application_user
			applicationUserDao.insert(appUser);
		}
				
		//2. DELETE sec_group_assignment
		userGroupDao.deleteGroupAssignmentByUserId(userId);
	
		//3. INSERT sec_group_assignment
		List<UserGroupAssignment> listUserGroupAssignment = new ArrayList<UserGroupAssignment>();
		for (UserGroupAssignmentDTO dto : data){
			UserGroupAssignment userGroupAssignment = new UserGroupAssignment();
			userGroupAssignment.setGroupId(dto.getGroupId());
			userGroupAssignment.setUserId(userId);
			userGroupAssignment.setCreatedBy(currentUser);
			userGroupAssignment.setCreatedOn(new Date());
			
			listUserGroupAssignment.add(userGroupAssignment);			
		}
		userGroupDao.inserts(listUserGroupAssignment);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteApplicationUser(Long applicationId, Long userId) throws Exception {
		//1.  DELETE sec_application_user		
		ApplicationUser appUser = new ApplicationUser();		
		ApplicationUserKey appUserKey = new ApplicationUserKey();
		appUser.setApplicationUser(appUserKey);
		
		appUserKey.setApplicationId(applicationId);
		appUserKey.setUserId(userId);
		
		applicationUserDao.delete(appUser);
		
		//2. DELETE sec_group_assignment
		userGroupDao.deleteGroupAssignmentByUserId(userId);
	}
}