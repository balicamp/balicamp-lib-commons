package id.co.sigma.security.server.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IApplicationUserDao;

/**
 * Application User DAO Impl
 * @author I Gede Mahendra
 * @since Dec 20, 2012, 10:28:43 AM
 * @version $Id
 */
@Repository
public class ApplicationUserDaoImpl extends BaseJPADao implements IApplicationUserDao{

	@Override
	public Integer countApplicationUserByParameter(ApplicationUser parameter) throws Exception {
		String sql = "SELECT COUNT(a.applicationUser.userId) FROM ApplicationUser a WHERE a.applicationUser.userId=:USER_ID and a.applicationUser.applicationId=:APPLICATION_ID";
		Query qry = getEntityManager().createQuery(sql);
		qry.setParameter("USER_ID", parameter.getApplicationUser().getUserId());
		qry.setParameter("APPLICATION_ID", parameter.getApplicationUser().getApplicationId());
		Long result = (Long) qry.getSingleResult();
		return result.intValue();
	}

	@Override
	public void delete(ApplicationUser parameter) throws Exception {
		String sql = "DELETE FROM ApplicationUser a WHERE a.applicationUser.userId=:USER_ID and a.applicationUser.applicationId=:APPLICATION_ID";
		Query qry = getEntityManager().createQuery(sql);
		qry.setParameter("USER_ID", parameter.getApplicationUser().getUserId());
		qry.setParameter("APPLICATION_ID", parameter.getApplicationUser().getApplicationId());
		qry.executeUpdate();
	}
}