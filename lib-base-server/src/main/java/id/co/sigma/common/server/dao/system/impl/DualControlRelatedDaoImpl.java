package id.co.sigma.common.server.dao.system.impl;

import javax.persistence.Query;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.CommonDualControlContainerTable;
import id.co.sigma.common.data.app.DualControlApprovalStatusCode;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.common.server.dao.system.DualControlRelatedDao;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DualControlRelatedDaoImpl extends BaseJPADao implements DualControlRelatedDao{

	

	@Override
	public PagedResultHolder<CommonDualControlContainerTable> getRejectList(
			SimpleQueryFilter[] filters, SimpleSortArgument[] sorts,
			String username, int page, int pageSize) {
		
		
				
		String 		defaultWhere =  " from CommonDualControlContainerTable a  WHERE 1=1 AND ("
				+ "  a.creatorUserId = :USERNAME  "
				+ " or "
				+ "  a.approverUserId = :USERNAME ) "
				+ " AND  "
				+ "  a.approvalStatus in ("
				+ "'" + DualControlApprovalStatusCode.REJECTED_BULK_DATA.toString() + "',"
				+ "'" + DualControlApprovalStatusCode.REJECTED_CREATE_DATA.toString() + "',"
				+ "'" + DualControlApprovalStatusCode.REJECTED_DELETE_DATA.toString() + "',"
				+ "'" + DualControlApprovalStatusCode.REJECTED_UPDATE_DATA.toString() +"'"
				+ ")"
				+ " " + buildWhereStatement("a", filters);
		String hqlCountSmt = "SELECT count(a.id) "  + defaultWhere  ;
		String hqlList = "SELECT a "  + defaultWhere + this.buildOrderByStatement("a", sorts); 
		Query qcount = getEntityManager().createQuery(hqlCountSmt); 
		qcount =  this.putQueryArguments(qcount, filters).setParameter("USERNAME", username);
		
		//qcount.getResultList();
		Long cnt = null  ; 
		try {
			cnt = (Long) qcount.getSingleResult();
		} catch (Exception e) {
			cnt = 0L ; 
		}
		
		PagedResultHolder<CommonDualControlContainerTable> retval = new PagedResultHolder<>(); 
		retval.adjustPagination(page, pageSize, cnt.intValue());
		Query qList = getEntityManager().createQuery(hqlList).setMaxResults(pageSize).setFirstResult(retval.getFirstRowPosition());
		qList =  this.putQueryArguments(qList, filters).setParameter("USERNAME", username);
		retval.setHoldedData(qList.getResultList());
		return retval;
	}

}
