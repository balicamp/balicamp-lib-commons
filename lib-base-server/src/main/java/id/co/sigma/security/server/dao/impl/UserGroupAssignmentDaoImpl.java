package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.security.domain.UserGroup;
import id.co.sigma.common.security.domain.UserGroupAssignment;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IUserGroupAssignmentDao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * User group assignment dao implementation
 * @author I Gede Mahendra
 * @since Dec 7, 2012, 11:43:19 AM
 * @version $Id
 */
@Repository
public class UserGroupAssignmentDaoImpl extends BaseJPADao implements IUserGroupAssignmentDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupAssignment> getDataByParameter(UserGroupAssignment parameter,int pagePosition, int pageSize) throws Exception {
		List<UserGroupAssignment> result = null;
		String qryString = "SELECT a FROM UserGroupAssignment a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString + " ORDER BY a.id");
		setParameterAtWhereString(qry, parameter);
		
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);
		result = qry.getResultList();
		return result;
	}
	
	@Override
	public Integer countAllUserGroupAssignment(UserGroupAssignment parameter) throws Exception {
		String qryString = "SELECT COUNT(a) FROM UserGroupAssignment a WHERE 1=1";
		String whereString = generateWhereString(parameter);
		
		Query qry = getEntityManager().createQuery(qryString + whereString);
		setParameterAtWhereString(qry,parameter);
		
		Long result = (Long) qry.getSingleResult();		
		return result.intValue();
	}
	
	@Override
	public void deleteGroupAssignmentByGroupId(Long groupId) throws Exception {
		Query qry = getEntityManager().createQuery("DELETE FROM UserGroupAssignment a WHERE a.groupId=:GROUP_ID");
		qry.setParameter("GROUP_ID", groupId);
		qry.executeUpdate();
	}
	
	@Override
	public void deleteGroupAssignmentByUserId(Long userId) throws Exception {
		Query qry = getEntityManager().createQuery("DELETE FROM UserGroupAssignment a WHERE a.userId=:USER_ID");
		qry.setParameter("USER_ID", userId);
		qry.executeUpdate();
	}
	
	@Override
	public void deleteGroupAssignmenyByUserGroupId(Long userId, Long groupId) throws Exception {
		Query qry = getEntityManager().createQuery("DELETE FROM UserGroupAssignment a WHERE a.groupId=:GROUP_ID AND a.userId=:USER_ID");
		qry.setParameter("GROUP_ID", groupId);
		qry.setParameter("USER_ID", userId);
		qry.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserGroupAssignment> getDataByUserId(Long userId) throws Exception {
		List<UserGroupAssignment> result = null;
		String qryString = "SELECT a FROM UserGroupAssignment a inner join fetch a.userGroup inner join fetch a.user WHERE a.userId=:USER_ID ORDER BY a.id";		
		
		Query qry = getEntityManager().createQuery(qryString);
		qry.setParameter("USER_ID", userId);
				
		result = qry.getResultList();
		return result;
	}
	
	/**
	 * Find by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserGroupAssignment find(Long id) throws Exception{
		return getEntityManager().find(UserGroupAssignment.class, id);
	}
	
	/**
	 * Generate where string yg akan di concat dg sintak query sebelumnya
	 * @param parameter
	 * @return String
	 */
	private String generateWhereString(UserGroupAssignment parameter){
		String whereString = "";
		if(parameter.getGroupId() != null){
			whereString += " AND a.groupId=:GROUP_ID";
		}		
		if(parameter.getId() != null){
			whereString += " AND a.id=:ID";
		}
		return whereString;
	}
	
	/**
	 * Set parameter ke dalam query where string
	 * @param qry
	 * @param parameter
	 */
	private void setParameterAtWhereString(Query qry, UserGroupAssignment parameter){
		if(parameter.getGroupId() != null){
			qry.setParameter("GROUP_ID", parameter.getGroupId());
		}
		if(parameter.getId() != null){			
			qry.setParameter("ID", parameter.getId());
		}
	}

	@Override
	public boolean isAnyUserWithGroup(UserGroup group) {
		String hql ="SELECT a from " + UserGroupAssignment.class.getName() + " a WHERE a.groupId=:ID " ;
		@SuppressWarnings("unchecked")
		List<UserGroupAssignment> usrs =  getEntityManager()
			.createQuery(hql)
			.setParameter("ID", group.getId())
			.setMaxResults(1)
			.getResultList(); 
		return usrs!= null && !usrs.isEmpty();
	}
}