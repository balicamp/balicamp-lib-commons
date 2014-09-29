package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.UserRole;

import java.util.List;

/**
 * 
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka Darmawan</a>
 */
public interface IUserRoleDao {
	
	public List<UserRole> getUserRoleList() throws Exception;
	
	public List<UserRole> getUserRoleListByUserId(Long userId) throws Exception;

}
