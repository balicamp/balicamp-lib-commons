package id.co.sigma.security.server.dao.impl;

import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.security.domain.Branch;
import id.co.sigma.common.security.domain.BranchAssignment;
import id.co.sigma.common.util.BranchTreeGenerator;
import id.co.sigma.security.server.dao.BaseGenericDao;
import id.co.sigma.security.server.dao.IBranchDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

/**
 * Branch dao impl
 * @author I Gede Mahendra
 * @since Jan 30, 2013, 3:47:09 PM
 * @version $Id
 */
@Repository
public class BranchDaoImpl extends BaseGenericDao implements IBranchDao{

	private final String BRANCH_PARENT_CODE = "branchParentId";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> getDataByFilters(SimpleQueryFilter[] filters,int pagePosition, int pageSize) throws Exception {
		String whereSql = "";
		String sql = "SELECT a FROM Branch a WHERE 1=1";
		if(filters != null){
			if(filters[0].getField().equalsIgnoreCase(BRANCH_PARENT_CODE)){
				whereSql = generateCustomWhere();
			}else{
				whereSql = buildWhereStatement("a", filters);
			}
		}
		System.out.println("Sql : " + sql + whereSql);
		Query qry = getEntityManager().createQuery(sql + whereSql);
		if(filters != null){
			if(filters[0].getField().equalsIgnoreCase(BRANCH_PARENT_CODE)){
				qry.setParameter("PARAM", filters[0].getFilter());
			}else{
				putQueryArguments(qry, filters);
			}
		}		
		qry.setFirstResult(pagePosition);
		qry.setMaxResults(pageSize);
		return qry.getResultList();
	}

	@Override
	public Integer countDataByFilters(SimpleQueryFilter[] filters) throws Exception {
		String whereSql = "";
		String sql = "SELECT COUNT(a) FROM Branch a WHERE 1=1";
		if(filters != null){
			if(filters[0].getField().equalsIgnoreCase(BRANCH_PARENT_CODE)){
				whereSql = generateCustomWhere();
			}else{
				whereSql = buildWhereStatement("a", filters);
			}
		}	
		System.out.println("Sql : " + sql + whereSql);
		Query qry = getEntityManager().createQuery(sql + whereSql);
		if(filters != null){
			if(filters[0].getField().equalsIgnoreCase(BRANCH_PARENT_CODE)){
				qry.setParameter("PARAM", filters[0].getFilter());
			}else{
				putQueryArguments(qry, filters);
			}
		}
		Long result = (Long) qry.getResultList().get(0);
		return result.intValue();
	}	
	
	/**
	 * Generate custom where jika user memilih filter branch_parent_code
	 * @return String
	 */
	private String generateCustomWhere(){
		return " AND a.branchParentId=(SELECT b.id FROM Branch b WHERE b.branchCode=:PARAM)";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BranchAssignment> getBranchAssignmentByUserId(Long userId) throws Exception {		
		Query qry = getEntityManager().createQuery("SELECT a FROM BranchAssignment a WHERE a.userId=:USER_ID");
		qry.setParameter("USER_ID", userId);		
		return qry.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> getBranchByCodes(Collection<String> branchCodes) {
		if ( branchCodes== null || branchCodes.isEmpty())
			return null ; 
		String hqlSmt = "select a FROM " + Branch.class.getName() + " a WHERE a.branchCode in (" + genarateInStatement("ID", branchCodes.size()) + ")";
		return fillInParams(  getEntityManager().createQuery(hqlSmt) , "ID" , branchCodes ).getResultList() ; 
	}

	@Override
	public List<Branch> getBranchComboboxByUserLogin(Long defaultBranch,
			SimpleQueryFilter[] additionalFilters, SimpleSortArgument[] sorts)
			throws Exception {
		
		String hql = "SELECT b FROM Branch b "
				+ " LEFT JOIN fetch b.children WHERE 1=1 AND b.id = "+defaultBranch
				+ buildWhereStatement("b", additionalFilters)
				+ buildOrderByStatement("b", sorts);

		Query q = getEntityManager().createQuery(hql);
		q = putQueryArguments(q, additionalFilters);
	    List<Branch> retval = new ArrayList<>();
	    List<Branch> resultList = q.getResultList();
	    if(resultList!=null && resultList.size()>0){
		    List<Branch> tree = new LinkedList<>();
	        BranchTreeGenerator branchTree = new BranchTreeGenerator(resultList.get(0), tree);
	        branchTree.createTree();
	        for (int i = 0; i < tree.size(); i++) {
	            Branch dataBranch = tree.get(i);
	            retval.add(dataBranch);
	        }
	    }else{
	    	retval = null;
	    }
		return retval;
	}
}