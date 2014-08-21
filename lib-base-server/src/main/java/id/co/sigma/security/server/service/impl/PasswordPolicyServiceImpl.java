/**
 * 
 */
package id.co.sigma.security.server.service.impl;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.security.domain.PasswordPolicy;
import id.co.sigma.security.server.dao.impl.PasswordPolicyDaoImpl;
import id.co.sigma.security.server.service.IPasswordPolicyService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dode
 * @version $Id
 * @since Jan 30, 2013, 3:47:47 PM
 */
@Service
public class PasswordPolicyServiceImpl implements IPasswordPolicyService {

	@Autowired
	private PasswordPolicyDaoImpl passwordPolicyDao;
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public PagedResultHolder<PasswordPolicy> getPasswordPolicy(int pagePosition, int pageSize) throws Exception {
		
		PagedResultHolder<PasswordPolicy> retval = new PagedResultHolder<PasswordPolicy>();
		
		Integer count = passwordPolicyDao.countPasswordPolicy();
		if (count == null || count == 0)
			return null;
		
		List<PasswordPolicy> holderData = passwordPolicyDao.getPasswordPolicy();
		
		retval.setHoldedData(holderData);
		retval.setPage(pagePosition);		
		retval.setPageSize(pageSize);
		retval.setTotalData(count);	
		retval.adjustPagination();
		
		return retval;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void insert(PasswordPolicy data) throws Exception {
		passwordPolicyDao.insert(data);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void update(PasswordPolicy data) throws Exception {
		passwordPolicyDao.update(data);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void remove(Long id) throws Exception {
		PasswordPolicy dataToRemove = passwordPolicyDao.getPasswordPolicyById(id);
		passwordPolicyDao.delete(dataToRemove);
	}

}
