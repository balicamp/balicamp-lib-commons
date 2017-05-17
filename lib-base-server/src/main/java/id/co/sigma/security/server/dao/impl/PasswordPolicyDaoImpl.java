/**
 * 
 */
package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.security.domain.PasswordPolicy;
import id.co.sigma.security.server.dao.BaseGenericDao;
import id.co.sigma.security.server.dao.IPasswordPolicyDao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * @author Dode
 * @version $Id
 * @since Jan 2, 2013, 4:25:54 PM
 */
@Repository
public class PasswordPolicyDaoImpl extends BaseGenericDao implements
		IPasswordPolicyDao {
	
	public final String ALPHABET_REGEX = "[a-z]";
	public final String NUMERIC_REGEX = "[0-9]";
	public final String ALPHABET_UPPER_REGEX = "[A-Z]";
	@Override
	public List<PasswordPolicy> getPasswordPolicy()
			throws Exception {
		String hql = "SELECT A FROM PasswordPolicy A";
		Query query = getEntityManager().createQuery(hql);
		return query.getResultList();
	}

	@Override
	public Integer countPasswordPolicy() throws Exception {
		String hql = "SELECT COUNT(A.id) FROM PasswordPolicy A";
		Query query = getEntityManager().createQuery(hql);
		Long count = (Long) query.getSingleResult();
		
		return count.intValue();
	}

	@Override
	public PasswordPolicy getPasswordPolicyById(Long id) throws Exception {
		String hql = "SELECT A FROM PasswordPolicy A WHERE A.id = :ID";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("ID", id);
		return (PasswordPolicy) query.getSingleResult();
	}

}
