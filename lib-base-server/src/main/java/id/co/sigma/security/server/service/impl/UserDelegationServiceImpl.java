package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.security.domain.UserDelegation;
import id.co.sigma.common.security.domain.UserDelegationGroup;
import id.co.sigma.common.security.domain.UserDelegationRole;
import id.co.sigma.security.server.dao.impl.UserDelegationDaoImpl;
import id.co.sigma.security.server.service.IUserDelegationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementasi dari IUserDelegationService
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */

@Service
public class UserDelegationServiceImpl implements IUserDelegationService{
	
	static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDelegationServiceImpl.class.getName());
	
	@Autowired
	private UserDelegationDaoImpl userDelegationDao;
	
	@Override
	@Transactional(readOnly=false)
	public void insert(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception {
		userDelegationDao.insert(data, roles, groups);
	}

	@Override
	@Transactional(readOnly=false)
	public void update(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception {
		userDelegationDao.update(data, roles, groups);
	}

	@Override
	public List<UserDelegationRole> getRoles(Long delegationId) {
		try {
			return userDelegationDao.getRoles(delegationId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UserDelegationGroup> getGroups(Long delegationId) {
		try {
			return userDelegationDao.getGroups(delegationId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
