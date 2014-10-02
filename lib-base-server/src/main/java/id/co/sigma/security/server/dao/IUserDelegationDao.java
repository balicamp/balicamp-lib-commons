package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.UserDelegation;
import id.co.sigma.common.security.domain.UserDelegationGroup;
import id.co.sigma.common.security.domain.UserDelegationRole;

import java.util.List;

/**
 * Interface untuk user delegation DAO
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */
public interface IUserDelegationDao {
	
	public void insert(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception;
	
	public void update(UserDelegation data, List<UserDelegationRole> roles, List<UserDelegationGroup> groups) throws Exception;
	
	public List<UserDelegationRole> getRoles(Long delegationId);
	
	public List<UserDelegationGroup> getGroups(Long delegationId);
	
	public void deleteRoles(Long delegationId) throws Exception;
	
	public void deleteGroups(Long delegationId) throws Exception;
	
}
