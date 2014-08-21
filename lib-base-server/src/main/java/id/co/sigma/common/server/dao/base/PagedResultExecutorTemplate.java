package id.co.sigma.common.server.dao.base;

import java.util.List;

import id.co.sigma.common.data.PagedResultHolder;

/**
 * Template untuk select data dalam model paged
 * 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @since 
 **/
public abstract class PagedResultExecutorTemplate<RESULT> {

	
	
	public interface DataCleanUpWorker<SOMEDATA> {
		
		
		
		public void cleanUpData(SOMEDATA data);
	}
	
	
	/**
	 * worker untuk mentrigger data dalam paged
	 **/
	public PagedResultHolder<RESULT> executeQuery (int pageSize , int page) throws Exception{
		Integer itung = count() ; 
		if ( itung==null||itung.intValue()==0)
			return null ; 
		PagedResultHolder<RESULT> retval = new PagedResultHolder<RESULT>() ;
		retval.setPage(page); 
		retval.setPageSize(pageSize); 
		retval.setTotalData(itung.intValue());
		retval.adjustPagination(); 
		List<RESULT> swap = list(pageSize, retval.getFirstRowPosition()) ; 
		retval.setHoldedData(swap);
		DataCleanUpWorker<RESULT> rsltCleaner =  getDataCleaner() ; 
		if ( rsltCleaner!=null){
			for ( RESULT scn : swap){
				rsltCleaner.cleanUpData(  scn);
			}
		}
				
		return retval ; 
	}
	
	
	/**
	 * method ini untuk run count query
	 **/
	protected abstract Integer count ()  throws Exception;
	
	/**
	 * method ini untuk run list data
	 * @param pageSize ukuran page yang akan di baca
	 * @param firstRowPosition posisi pertama data yang akan di baca
	 **/
	protected abstract List<RESULT> list (int pageSize , int firstRowPosition
			)  throws Exception; 
	
	
	/**
	 * ini kalau di perlukan data clean up. ini item untuk mengatasi masalah java assist problem(dto thing)
	 **/
	protected DataCleanUpWorker<RESULT> getDataCleaner(){
		 return null ; 
	 }
}
