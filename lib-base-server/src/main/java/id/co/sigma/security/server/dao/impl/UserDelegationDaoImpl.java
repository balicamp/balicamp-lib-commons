package id.co.sigma.security.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import id.co.sigma.common.security.domain.UserDelegation;
import id.co.sigma.common.security.domain.UserDelegationGroup;
import id.co.sigma.common.security.domain.UserDelegationRole;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IUserDelegationDao;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * Tulis keterangan class di sini
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */

@Repository
public class UserDelegationDaoImpl extends BaseJPADao implements IUserDelegationDao {
	static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDelegationDaoImpl.class.getName());

	@Override
	public void insert(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception {
		getEntityManager().persist(data);
		
		if(roles!=null && !roles.isEmpty()){
			for(UserDelegationRole role : roles){
				role.setUserDelegateId(data.getId());
				getEntityManager().persist(role);
			}
		}
		
		if(groups!=null && !groups.isEmpty()){
			for(UserDelegationGroup group : groups){
				group.setUserDelegateId(data.getId());
				getEntityManager().persist(group);
			}
		}
	}

	@Override
	public void update(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception {
		getEntityManager().merge(data);
		
		if(roles!=null && !roles.isEmpty()){
			deleteRoles(data.getId());
			for(UserDelegationRole role : roles){
				UserDelegationRole newRole = new UserDelegationRole();
				BeanUtils.copyProperties(role, newRole);
				newRole.setUserDelegateId(data.getId());
				getEntityManager().merge(newRole);
			}
		}
		
		if(groups!=null && !groups.isEmpty()){
			deleteGroups(data.getId());
			for(UserDelegationGroup group : groups){
				UserDelegationGroup newGroup = new UserDelegationGroup();
				BeanUtils.copyProperties(group, newGroup);
				getEntityManager().merge(newGroup);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDelegationRole> getRoles(Long delegationId) {
		Query qry = getEntityManager().createQuery("SELECT udr FROM UserDelegationRole udr WHERE udr.userDelegateId=:DELEGATE_ID");
		qry.setParameter("DELEGATE_ID", delegationId);
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDelegationGroup> getGroups(Long delegationId) {
		Query qry = getEntityManager().createQuery("SELECT udg FROM UserDelegationGroup udg WHERE udg.userDelegateId=:DELEGATE_ID");
		qry.setParameter("DELEGATE_ID", delegationId);
		return qry.getResultList();
	}

	@Override
	public void deleteRoles(Long delegationId) throws Exception {
		Query qry = getEntityManager().createQuery("DELETE FROM UserDelegationRole udr WHERE udr.userDelegateId=:DELEGATE_ID");
		qry.setParameter("DELEGATE_ID", delegationId);
		qry.executeUpdate();
	}

	@Override
	public void deleteGroups(Long delegationId) throws Exception {
		Query qry = getEntityManager().createQuery("DELETE FROM UserDelegationGroup udg WHERE udg.userDelegateId=:DELEGATE_ID");
		qry.setParameter("DELEGATE_ID", delegationId);
		qry.executeUpdate();
	}
	
}
