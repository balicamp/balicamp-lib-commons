/**
 * 
 */
package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.security.domain.Application;
import id.co.sigma.security.server.dao.BaseGenericDao;
import id.co.sigma.security.server.dao.IApplicationDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * dao untuk application
 * @author Dode
 * @version $Id
 * @since Dec 19, 2012, 3:00:25 PM
 */
@Repository
public class ApplicationDaoImpl extends BaseGenericDao implements IApplicationDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationList() throws Exception {
		String hql = "SELECT A FROM Application A";
		Query query = getEntityManager().createQuery(hql);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplicationList(int pagePosition, int pageSize) throws Exception {
		String hql = "SELECT a FROM Application a";
		Query qry = getEntityManager().createQuery(hql);
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);		
		return qry.getResultList();
	}
}