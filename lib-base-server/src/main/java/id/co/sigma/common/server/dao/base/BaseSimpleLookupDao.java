package id.co.sigma.common.server.dao.base;

import java.util.List;

import javax.persistence.Query;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;


/**
 * DAO untuk simple Lookup
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $If
 **/
public abstract class BaseSimpleLookupDao<ENTITY> extends BaseJPADao{

	
	/**
	 * membaca list data dengan entity class
	 * @param filters filter standard. operator AND
	 * @param page page berapa di baca
	 * @param pageSize ukuran page pembacaan data
	 * @param sortArguments sort arguments,di sort dengan field apa saja 
	 * @param includedNonBasicFields fields non basic yang di included. 
	 *  
	 **/
	public  PagedResultHolder<ENTITY> getDataWorker (/*String[] includedNonBasicFields , */ SimpleQueryFilter[] filters ,  int page , int pageSize  ) throws Exception{
		
		String countSmt = "select count(a.id) from "  + generateCountSelectStatement("a") + " WHERE 1=1 " + buildWhereStatement("a", filters);
		System.out.println("count smt :>>" + countSmt);
		Query q = getEntityManager().createQuery(countSmt);
		q= putQueryArguments(q, filters);
		Long cnt=null ;
		try {
			cnt =  (Long) q.getSingleResult();
			if (cnt==null||cnt.longValue()==0)
				return null ; 
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		PagedResultHolder<ENTITY> retval = new PagedResultHolder<ENTITY>();
		retval.setPageSize(pageSize);
		retval.setTotalData(cnt.intValue());
		retval.setPage(page);
		retval.adjustPagination();
		String ord = generateOrderStatement("a");
		if ( ord!=null&&ord.length()>0)
			ord = " order by " + ord ; 
		String slctAllSmt = "SELECT a from "  +  generateDataSelectStatement("a") + " WHERE 1=1 " + buildWhereStatement("a", filters) + ord;
		System.out.println("select all smt : >>" + slctAllSmt);
		Query qLst  = getEntityManager().createQuery(slctAllSmt);
		qLst = this.putQueryArguments(qLst, filters);
		System.out.println("lookup >>page size :" +pageSize + ",1st row postion :" + retval.getFirstRowPosition() );
		@SuppressWarnings("unchecked")
		List<ENTITY> rslts =qLst.setMaxResults(pageSize).setFirstResult(retval.getFirstRowPosition()).getResultList();
		if(rslts!=null&&!rslts.isEmpty()){
			for ( ENTITY scn : rslts){
				detachUnUsedFieldAndMakeRealObjectOnAssociation(scn);
			}
		}
		retval.setHoldedData(rslts);
		return  retval;
	}
	
	
	
	/**
	 * generate from statement. anda berkewajiban menyiapkan join statement
	 * @param primaryTableAlias alias untuk primary table
	 **/
	protected abstract String generateDataSelectStatement (String primaryTableAlias ) ; 
	
	
	protected abstract String generateCountSelectStatement (String primaryTableAlias ) ;
	
	
	
	
	/**
	 * ini untuk generate order by statements
	 **/
	protected abstract String generateOrderStatement(String primaryTableAlias); 
	
	
	/**
	 * pekerjaan method ini : 
	 * <ol>
	 * <li>set null untuk field yang tidak di akses *yang ada association *</li>
	 * <li>ganti object association dengan real object. karena bisa dapat nya bytecode enhancement</li>
	 * </ol>
	 **/
	protected abstract void detachUnUsedFieldAndMakeRealObjectOnAssociation(ENTITY data);
	
}
