package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.domain.ApplicationUser;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.security.domain.UserPassword;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IUserDao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * User dao implementation
 * @author I Gede Mahendra
 * @since Dec 10, 2012, 1:47:10 PM
 * @version $Id
 */
@Repository
public class UserDaoImpl extends BaseJPADao implements IUserDao{

	private final String paramPrefix = "PARAM_";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByParameter(User parameter, int pagePosition, int pageSize) throws Exception {	
		List<User> result = null;
		String qryString = "SELECT a FROM User a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString + " ORDER BY a.id");
		setParameterAtWhereString(qry, parameter);
		
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);
		result = qry.getResultList();
		return result;
	}
	
	@Override
	public Integer countUserByParameter(User parameter) throws Exception {		
		String qryString = "SELECT COUNT(a) FROM User a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString /*+ " ORDER BY a.id"*/);
		setParameterAtWhereString(qry, parameter);
		
		Long result = (Long) qry.getSingleResult();		
		return result.intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationUser> getApplicationUser(Long applicationId,User parameter,int pagePosition, int pageSize) throws Exception {
		List<ApplicationUser> result = null;
		String sqlString = "SELECT a FROM ApplicationUser a WHERE a.applicationUser.applicationId=:APPLICATION_ID ";
		String whereString = "";
		
		if(parameter != null){
			if(parameter.getUserCode() != null){
				whereString += " AND a.user.userCode LIKE :USER_NAME ";
			}else if(parameter.getRealName() != null){
				whereString += " AND a.user.realName LIKE :FULL_NAME ";
			}else if(parameter.getEmail() != null){
				whereString += " AND a.user.email LIKE :EMAIL ";
			}
		}		
				
		Query qry = getEntityManager().createQuery(sqlString + whereString + " ORDER BY a.applicationUser.userId ASC");
		
		if(parameter != null){
			if(parameter.getUserCode() != null){			
				qry.setParameter("USER_NAME", "%" + parameter.getUserCode() + "%");
			}else if(parameter.getRealName() != null){			
				qry.setParameter("FULL_NAME", "%" + parameter.getRealName() + "%");
			}else if(parameter.getEmail() != null){				
				qry.setParameter("EMAIL", "%" + parameter.getEmail() + "%");
			}
		}		
		
		qry.setParameter("APPLICATION_ID", applicationId);
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);
		
		result = qry.getResultList();		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByUserId(List<Long> userIds) throws Exception {
		List<User> result = null;
		Query qry = getEntityManager().createQuery("SELECT a FROM User a WHERE a.id IN :USER_IDS ORDER BY a.id ASC");
		qry.setParameter("USER_IDS", userIds);		
		result = qry.getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupAssignment> getGroupAssignmentByUserId(List<Long> userIds) throws Exception {
		List<UserGroupAssignment> result = null;
		Query qry = getEntityManager().createQuery("SELECT a FROM UserGroupAssignment a WHERE a.userId IN :USER_ID ORDER BY a.userId ASC");
		qry.setParameter("USER_ID", userIds);
		result = qry.getResultList();
		return result;
	}
	
	/**
	 * Generate where string yg akan di concat dg sintak query sebelumnya
	 * @param parameter
	 * @return String
	 */
	private String generateWhereString(User parameter){
		String whereString = "";
		if(parameter != null){
			if(parameter.getUserCode() != null){
				whereString += " AND a.userCode LIKE :USERNAME";
			}
			if(parameter.getRealName() != null){
				whereString += " AND a.realName LIKE :REALNAME";
			}
			if(parameter.getId() != null){
				whereString += " AND a.id=:ID";
			}
			if(parameter.getEmail() != null){
				whereString += " AND a.email LIKE :EMAIL";
			}
			if(parameter.getDefaultApplicationId() != null){
				whereString += " AND a.defaultApplicationId=:APPLICATION_ID";
			}
		}		
		return whereString;
	}
	
	/**
	 * Set parameter ke dalam query where string
	 * @param qry
	 * @param parameter
	 */
	private void setParameterAtWhereString(Query qry, User parameter){
		if(parameter != null){
			if(parameter.getUserCode() != null){
				qry.setParameter("USERNAME", "%" + parameter.getUserCode() + "%");
			}
			if(parameter.getRealName() != null){
				qry.setParameter("REALNAME", "%" + parameter.getRealName() + "%");
			}
			if(parameter.getId() != null){			
				qry.setParameter("ID", parameter.getId());
			}
			if(parameter.getEmail() != null){
				qry.setParameter("EMAIL", "%" + parameter.getEmail() + "%");				
			}
			if(parameter.getDefaultApplicationId() != null){				
				qry.setParameter("APPLICATION_ID", parameter.getDefaultApplicationId());
			}
		}		
	}
	
	/**
	 * generate where string berdasarkan array queryfilter
	 * @param filters filters untuk querynya
	 * @param alias alias dari tabelnya
	 * @return
	 */
	private String generateWhereString(SimpleQueryFilter[] filters, String alias) {
		if (filters == null)
			return "";
		String whereString = "";
		for (int i = 0; i < filters.length; i++) {
			whereString += " AND " + alias + "." + filters[i].getField() + " " 
					+ filters[i].getOperator() + "_" + i; 
		}
		return whereString;
	}
	
	/**
	 * set parameter di where clause nya
	 * @param query query dengan patameter
	 * @param filters filter untuk querynya
	 */
	private void setParameterAtWhereString(Query query, SimpleQueryFilter[] filters) {
		if (filters == null)
			return ;
		for (int i = 0; i < filters.length; i++) {
			query.setParameter(paramPrefix + i, filters[i].getFilter());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByFilters(SimpleQueryFilter[] filters, int pagePosition, int pageSize) throws Exception {
		String hql = "SELECT A FROM User A WHERE 1=1";
		Query query = getEntityManager().createQuery(hql + generateWhereString(filters, "A"));
		setParameterAtWhereString(query, filters);
		query.setFirstResult(pagePosition * pageSize);
		query.setMaxResults(pageSize);
		List<User> result = query.getResultList();
		if (result == null | result.isEmpty()) {
			return null;
		}
		for (User user : result) {
			if ( user.getDefaultApplicationId()== null)
				continue ; 
			Application defaultApp = get(Application.class, new BigInteger(user.getDefaultApplicationId().toString()));
			user.setDefaultApplication(defaultApp);
		}
		return result;
	}

	@Override
	public Integer countUserByFilters(SimpleQueryFilter[] filters)
			throws Exception {
		String hql = "SELECT COUNT(A) FROM User A WHERE 1=1";
		String whereString = generateWhereString(filters, "A");
		Query query = getEntityManager().createQuery(hql + whereString);
		setParameterAtWhereString(query, filters);
		
		Long count = (Long) query.getSingleResult();
		
		return count.intValue();
	}

	@Override
	public User getUserById(Long id) throws Exception {
		String hql = "SELECT A FROM User A WHERE id = :ID";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("ID", id);
		return (User) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserPassword> getUserPasswordByUserId(Long id)
			throws Exception {
		String hql = "SELECT A FROM UserPassword A WHERE userId = :USER_ID";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("USER_ID", id);
		return query.getResultList();
	}

	@Override
	public User isUserNameExist(String userCode, Integer applicationId) throws Exception {
		String hql = "SELECT A FROM User A WHERE A.userCode = :USER_CODE AND A.defaultApplicationId = :APP_ID";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("USER_CODE", userCode);
		query.setParameter("APP_ID", applicationId);
		return (User) query.getSingleResult();
	}

	@Override
	public User getUserByUserName(String username) {
		String hql = "SELECT A FROM User A WHERE A.userCode = :USER_CODE ";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("USER_CODE", username);
		List<User> sss = query.getResultList(); 
		return sss!= null&&!sss.isEmpty() ? sss.get(0) : null;
	}
	
	@Override
	public Long selectUserCountByBranchCode(String branchCode) {
		String hql = "select count(u) from User u where u.defaultBranchCode = :defaultBranchCode";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("defaultBranchCode", branchCode);
		return (Long) query.getSingleResult();
	}

	@Override
	public Serializable insertAndFlushWhitReturn(Serializable object)
			throws Exception {
		Serializable data = getEntityManager().merge(object);
		getEntityManager().flush();
		return data;
	}
}