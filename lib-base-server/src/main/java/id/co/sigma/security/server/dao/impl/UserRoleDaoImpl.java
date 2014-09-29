package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.security.domain.UserRole;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IUserRoleDao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka Darmawan</a>
 */
@Repository
public class UserRoleDaoImpl extends BaseJPADao implements IUserRoleDao {

	@Override
	public List<UserRole> getUserRoleList() throws Exception {
		List<UserRole> listData = new ArrayList<UserRole>();
		String sql = "Select u From UserRole u Where 1=1";
		Query qry = getEntityManager().createQuery(sql);

		if(qry.getResultList()!=null && !qry.getResultList().isEmpty()){
			listData = qry.getResultList();
			return listData;
		}else{
			return null;
		}
		
	}

	@Override
	public List<UserRole> getUserRoleListByUserId(Long userId) throws Exception {
		List<UserRole> listData = new ArrayList<UserRole>();
		String sql = "Select u From UserRole u Where 1=1 And u.userId=:USER_ID";
		Query qry = getEntityManager().createQuery(sql);
		qry.setParameter("USER_ID", userId);

		if(qry.getResultList()!=null && !qry.getResultList().isEmpty()){
			listData = qry.getResultList();
			return listData;
		}else{
			return null;
		}
	}

	@Override
	public void deleteUserRoleByUserId(Long userId) {
		Query qry = getEntityManager().createQuery("DELETE FROM UserRole a WHERE a.userId=:USER_ID");
		qry.setParameter("USER_ID", userId);
		qry.executeUpdate();
		
	}
	
}
