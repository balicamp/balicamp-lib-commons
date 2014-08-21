package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.UserGroup;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.security.server.dao.BaseGenericDao;
import id.co.sigma.security.server.dao.IUserGroupDao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * Dao untuk proses user group
 * @author I Gede Mahendra
 * @since Dec 6, 2012, 10:05:27 AM
 * @version $Id
 */
@Repository
public class UserGroupDaoImpl extends BaseGenericDao implements IUserGroupDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> getAllUserGroup(UserGroup parameter, int pagePosition, int pageSize) throws Exception {
		List<UserGroup> result = null;
		String qryString = "SELECT a FROM UserGroup a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString + " ORDER BY a.groupName");
		setParameterAtWhereString(qry,parameter);
		
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);
		result = qry.getResultList();
		return result;
	}

	@Override
	public Integer countAllUserGroup(UserGroup parameter) throws Exception {		
		String qryString = "SELECT COUNT(a) FROM UserGroup a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString /*+ " ORDER BY a.groupName"*/);
		setParameterAtWhereString(qry,parameter);
		
		Long result = (Long) qry.getSingleResult();		
		return result.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserGroup getUserGroupByParameter(UserGroup parameter) throws Exception {
		UserGroup result = null;
		String qryString = "SELECT a FROM UserGroup a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString + " ORDER BY a.groupName");
		setParameterAtWhereString(qry,parameter);
		
		/*qry.setFirstResult(0);
		qry.setMaxResults(10);*/
		List<UserGroup> resultList = qry.getResultList();
		if(resultList.size() > 0){
			result = resultList.get(0);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserGroups(Long userId,
			Long applicationid) {
		String hqlSmt ="select a.userGroup.groupCode " +
				" from UserGroupAssignment a " +
				" where " +
				"	a.userId=:USERID and a.userGroup.applicationId=:APP_ID";
		return getEntityManager().createQuery(hqlSmt)
			.setParameter("USERID", userId)
			.setParameter("APP_ID", applicationid).getResultList();	
	}
	
	@Override
	public void updateUserGroup(UserGroup data) throws Exception {
		UserGroup resultFind = (UserGroup) find(UserGroup.class, data.getId());
		if(resultFind != null){
			resultFind.setGroupCode(data.getGroupCode());
			resultFind.setGroupName(data.getGroupName());
			resultFind.setStatus(data.getStatus());
			resultFind.setSuperGroup(data.getSuperGroup());			
			update(resultFind);
		}
	}
	
	/**
	 * Generate where string yg akan di concat dg sintak query sebelumnya
	 * @param parameter
	 * @return String
	 */
	private String generateWhereString(UserGroup parameter){
		String whereString = "";
		
		if(parameter != null){
			if(parameter.getGroupCode() != null){
				whereString += " AND a.groupCode LIKE :GROUP_CODE";
			}		
			if(parameter.getGroupName() != null){
				whereString += " AND a.groupName LIKE :GROUP_NAME";
			}
			if(parameter.getApplicationId() != null){
				whereString += " AND a.applicationId=:APPLICATION_ID";
			}
			if(parameter.getId() != null){
				whereString += " AND a.id=:ID";
			}
		}		
		return whereString;
	}
	
	/**
	 * Set parameter ke dalam query where string
	 * @param qry
	 * @param parameter
	 */
	private void setParameterAtWhereString(Query qry, UserGroup parameter){
		if(parameter != null){
			if(parameter.getGroupCode() != null){
				qry.setParameter("GROUP_CODE", "%" + parameter.getGroupCode() + "%");
			}
			if(parameter.getGroupName() != null){
				qry.setParameter("GROUP_NAME", "%" + parameter.getGroupName() + "%");
			}
			if(parameter.getApplicationId() != null){
				qry.setParameter("APPLICATION_ID", parameter.getApplicationId());
			}
			if(parameter.getId() != null){			
				qry.setParameter("ID", parameter.getId());
			}
		}		
	}

	@Override
	public User getUserById(Long userId) {
		try {
			return getEntityManager().find(User.class, userId);
		} catch (Exception e) {
			return null ; 
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroup> getUserGroupByCodes(Collection<String> codes) {
		if ( codes== null || codes.isEmpty())
			return null ;
		String hql = "SELECT a from " +UserGroup.class.getName()+" a WHERE a.groupCode in(" + genarateInStatement("CODE", codes.size()) + ") ";
		return fillInParams(  getEntityManager().createQuery( hql)  , "CODE" , codes).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupAssignment> getUserGroupByUserIds(
			Collection<Long> userIds) {
		if (userIds== null || userIds.isEmpty())
			return null ; 
		String hqlSmt ="SELECT a from UserGroupAssignment a  inner join fetch a.userGroup  WHERE a.userId in ( " + genarateInStatement("IDS", userIds.size()) +" ) ";
		return fillInParams( getEntityManager().createQuery(hqlSmt) , "IDS" , userIds).getResultList(); 
	}

	@Override
	public UserGroup getUserGroupByCode(String code) {
		List<String> codes = new ArrayList<String>();
		codes.add(code);
		
		List<UserGroup> groups = getUserGroupByCodes(codes);
		
		return groups == null || groups.isEmpty() ? null : groups.get(0);
	}
	
	
}