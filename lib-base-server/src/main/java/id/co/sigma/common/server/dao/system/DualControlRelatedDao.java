package id.co.sigma.common.server.dao.system;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.IBaseDao;

/**
 * dao untuk pekerjan dual control table
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface DualControlRelatedDao extends IBaseDao{
	
	
	
	
	
	
	/**
	 * membaca data reject. data diambil dengan rule :
	 *  <ol>
	 * <li>creator = username</li>
	 * <li>approver = username</li>
	 * </ol>
	 * @param filters filters data. ini tanpa user name
	 * @param sorts sort order
	 */
	public PagedResultHolder<CommonDualControlContainerTable> getRejectList (SimpleQueryFilter[] filters , SimpleSortArgument[] sorts , String username , int page , int pageSize) ;
	
	
}
