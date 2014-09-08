package id.co.sigma.common.server.service;

import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.DualControlEnabledData;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;

/**
 * 
 * ini di pergunakan kalau ID dari data adalah composite, dan tidak bisa diakses dengan 1 ID tunggal. user code berkewajiban untuk load data ini
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ICustomTargetDataLoader<DATA extends DualControlEnabledData<DATA, ?>> {
	
	
	/**
	 * target class yang perlu di load class ini
	 **/
	public Class<DATA> getTargetClass () ; 
	
	
	/**
	 * load data dengan data di pack menjadi 1. proses pact data menjadi tanggung jawab masing-masing
	 **/
	public DATA loadDataByPackedId(String packedId) throws Exception; 
	
	
	
	
	
	/**
	 * custom loader data berupa grid. Kalau data perlu di load dengan join ke table-table tertentu, proses nya di taruh di sini
	 **/
	public PagedResultHolder<DATA> listDataRaw(  
			int page, int pageSize, SimpleQueryFilter[] filters,
			SimpleSortArgument[] sortArguments) throws Exception;
	

}
