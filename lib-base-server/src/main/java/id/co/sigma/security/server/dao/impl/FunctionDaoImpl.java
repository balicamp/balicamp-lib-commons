/**
 * 
 */
package id.co.sigma.security.server.dao.impl;


import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.security.server.dao.IFunctionDao;

/**
 * dao untuk function
 * @author Dode
 * @version $Id
 * @since Jan 7, 2013, 10:05:58 AM
 */
@Repository
public class FunctionDaoImpl extends BaseJPADao implements IFunctionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationMenu> getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(
			Long applicationId) throws Exception {
		String hql = "SELECT A FROM Function A WHERE A.applicationId = :APPLICATION_ID ORDER BY A.treeLevelPosition, A.siblingOrder";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("APPLICATION_ID", applicationId);
		return query.getResultList();
	}
	
	
	
	/**
	 * 
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationMenu> getAppMenuByAppIdJoindedWithPageOrderByTreeLevelAndSiblingOrder(
			Long applicationId) throws Exception {
		String hql = "SELECT A FROM Function A inner join fetch A.pageDefinition  WHERE A.applicationId = :APPLICATION_ID ORDER BY A.treeLevelPosition, A.siblingOrder";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("APPLICATION_ID", applicationId);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApplicationMenu> getFunctionByFunctionIdOrderByTreeLevelAndSiblingOrder(List<Long> functionIds)
			throws Exception {
		String hql = "SELECT A FROM Function A WHERE A.id in :FUNCTION_ID_LIST ORDER BY A.treeLevelPosition, A.siblingOrder";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("FUNCTION_ID_LIST", functionIds);
		List<ApplicationMenu> result = query.getResultList();
		return result;
	}



	@Override
	public Long getFunctionByFunctionIdParent(Long parentId)
			throws Exception {
		
		String queryString = "SELECT COUNT(a.id) FROM Function a WHERE a.functionIdParent = :ID_PARENT";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("ID_PARENT", parentId);
		Long retval = (Long) query.getSingleResult();
		return retval;
	}



	@Override
	public void deleteFunctionById(Long id) throws Exception {
		String queryString = "DELETE FROM Function a WHERE a.id = :ID";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("ID", id);
		query.executeUpdate();
	}



	@Override
	public Integer getNextSiblingOrder(Long parentId, Long applicationId) throws Exception {
		String queryString = "SELECT a FROM Function a ";
		String orderBy = "ORDER BY a.siblingOrder DESC";
		String paramString = null;
		Long paramValue = null;
		if (parentId == null) {
			queryString += "WHERE a.functionIdParent is null AND a.applicationId =:APP_ID ";
			paramString = "APP_ID";
			paramValue = applicationId;
		} else {
			queryString += "WHERE a.functionIdParent = :ID_PARENT ";
			paramString = "ID_PARENT";
			paramValue = parentId;
		}
		Query query = getEntityManager().createQuery(queryString+orderBy);
		query.setParameter(paramString, paramValue);
		List<ApplicationMenu> result = query.getResultList();
		Integer retval = 1;
		if (!(result == null || result.isEmpty())) {
			retval = result.get(0).getSiblingOrder() + 1;
		}
		return retval;
	}



	@Override
	public List<ApplicationMenu> getAllFunctionByApplicationId(Long applicationId) {
		
		return null;
	}



	@Override
	public List<PageDefinition> getPageDefinitionByPageIds(
			Collection<Long> pageIds) {
		if ( pageIds== null || pageIds.isEmpty())
			return null ; 
		String getSmt  = "select a from PageDefinition a WHERE a.id in (" + genarateInStatement("ID", pageIds.size()) + ")";
		Query q =  getEntityManager().createQuery(getSmt);
		q = fillInParams(q, "ID", pageIds);
		return q.getResultList();
	}

}
