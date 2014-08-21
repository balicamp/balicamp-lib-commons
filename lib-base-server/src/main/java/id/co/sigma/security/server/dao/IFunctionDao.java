/**
 * 
 */
package id.co.sigma.security.server.dao;

import id.co.sigma.common.security.domain.ApplicationMenu;
import id.co.sigma.common.security.domain.PageDefinition;
import id.co.sigma.common.server.dao.IBaseDao;


import java.util.Collection;
import java.util.List;

/**
 * dao untuk function
 * @author Dode
 * @version $Id
 * @since Jan 4, 2013, 7:15:57 PM
 */
public interface IFunctionDao extends IBaseDao {
	
	/**
	 * get function data by tree level position dan sibling order by application id
	 * @param applicationId application id
	 * @return list of function
	 * @throws Exception
	 */
	public List<ApplicationMenu> getFunctionByApplicationIdOrderByTreeLevelAndSiblingOrder(Long applicationId) throws Exception;
	
	
	/**
	 * select ke dalam table application menu, 
	 * @param applicationId id dari app yang perlu di load
	 **/
	public List<ApplicationMenu> getAppMenuByAppIdJoindedWithPageOrderByTreeLevelAndSiblingOrder(Long applicationId) throws Exception;
	
	/**
	 * get function data by function id order by tree level position dan sibling order by application id
	 * @param functionIds list of function id
	 * @return list of function
	 * @throws Exception
	 */
	public List<ApplicationMenu> getFunctionByFunctionIdOrderByTreeLevelAndSiblingOrder(List<Long> functionIds) throws Exception;
	
	/**
	 * add by dode
	 * get function by parent id
	 * @param parentId parent
	 * @return number of child function
	 * @throws Exception
	 */
	public Long getFunctionByFunctionIdParent(Long parentId) throws Exception;
	
	
	
	
	/**
	 * membaca data dengan id dari aplikasi
	 * @param applicationId id dari aplikasi
	 */
	public List<ApplicationMenu> getAllFunctionByApplicationId (Long applicationId ) ; 
	
	/**
	 * add by dode
	 * delete function by id
	 * @param id function id
	 * @throws Exception
	 */
	public void deleteFunctionById(Long id) throws Exception;
	
	/**
	 * 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public Integer getNextSiblingOrder(Long parentId, Long applicationId) throws Exception;
	
	
	
	/**
	 * membaca page def dengan id dari page
	 */
	public List<PageDefinition> getPageDefinitionByPageIds ( Collection<Long> pageIds ) ; 
}
