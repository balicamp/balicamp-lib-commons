package id.co.sigma.security.server.dao;

import id.co.sigma.common.server.dao.base.BaseJPADao;

import org.springframework.stereotype.Repository;

/**
 * Base generic dao
 * @author I Gede Mahendra
 * @since Aug 17, 2012, 3:24:05 PM
 * @version $Id
 */
@Repository
public class BaseGenericDao extends BaseJPADao{
	
	/**
	 * Find by primary key
	 * @param entity
	 * @param data
	 * @return list of entity
	 * @throws Exception
	 */
	public Object find(Class<?> entity, Object data) throws Exception{
		return getEntityManager().find(entity, data);
	}	
}