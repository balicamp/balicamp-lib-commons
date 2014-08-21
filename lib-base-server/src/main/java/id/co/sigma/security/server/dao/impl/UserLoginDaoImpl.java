/**
 * File Name : UserLoginDaoImpl.java
 * Package   : id.co.sigma.arium.security.server.dao.impl
 * Project   : security-server
 */
package id.co.sigma.security.server.dao.impl;


import java.util.List;

import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.domain.Signon;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserPassword;
import id.co.sigma.security.server.dao.BaseGenericDao;
import id.co.sigma.security.server.dao.IUserLoginDao;

/**
 * Dao untuk proses login
 * @author I Gede Mahendra
 * @since Nov 19, 2012, 11:55:33 AM
 * @version $Id
 */
@Repository
public class UserLoginDaoImpl extends BaseGenericDao implements IUserLoginDao{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserLoginDaoImpl.class); 
	
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) throws Exception {
		User result = null;
		Query qry = getEntityManager().createNamedQuery("SECURITY-LOGIN-GET-USERID");
		qry.setParameter("USERNAME", username);
		List<User> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Signon> getSignOnByUserId(Long userId) throws Exception {
		List<Signon> result = null;
		Query qry = getEntityManager().createNamedQuery("SECURITY-LOGIN-CHECK-SIGNON");
		qry.setParameter("USER_ID", userId);
		result = qry.getResultList();
		if(result.size() == 0){
			result = null;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Application getApplicationData(Long applicationId) throws Exception {
		Application result = null;
		Query qry = getEntityManager().createNamedQuery("SECURITY-LOGIN-GET-APPLICATION");
		qry.setParameter("APPLICATION_ID", applicationId);
		List<Application> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserPassword getPasswordAtHistory(Long userId) throws Exception {
		UserPassword result = null;
		Query qry = getEntityManager().createNamedQuery("SECURITY-LOGIN-CHECK-PASSWORD");
		qry.setParameter("USER_ID", userId);
		qry.setMaxResults(1);
		List<UserPassword> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}

	@Override
	public Signon getSigonData(Long sigonId) {
		String hqlSmt ="SELECT a from Signon a inner join fetch a.user inner join fetch a.application where a.id=:ID " ;
		try {
			return (Signon) getEntityManager().createQuery(hqlSmt).setParameter("ID", sigonId).getSingleResult();
		} catch (Exception e) {
			logger.error("gagal membaca data signon berdasarkan signon id : " + sigonId + ", error :" + e.getMessage());
			e.printStackTrace(); 
			return null ; 
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Signon getSignonData(String uuid) throws Exception{
		Signon result = null;
		
		String hql = "SELECT a FROM Signon a WHERE a.uuid=:UUID";
		Query qry = getEntityManager().createQuery(hql);
		qry.setParameter("UUID", uuid);
		qry.setMaxResults(1);
		List<Signon> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Signon getLastLoginTime(Long userId) throws Exception {
		Signon result = null;
		
		String hql = "SELECT a FROM Signon a WHERE a.userId=:USERID ORDER BY a.logonTime DESC";
		Query qry = getEntityManager().createQuery(hql);
		qry.setParameter("USERID", userId);
		qry.setMaxResults(1);
		List<Signon> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}		
		return result;
	}	
}