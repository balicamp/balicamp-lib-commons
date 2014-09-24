package id.co.sigma.common.server.controllers;

import java.util.HashMap;
import java.util.Map;



import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.query.BaseGridDataProviderNativeSql;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.data.query.SimpleQueryFilterOperator;
import id.co.sigma.common.server.service.datagrid.impl.CommonNativeSqlJSONDataGridDaoLayer;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * controller provider data untuk grid
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
//@Controller
public class UnifiedGridDataProviderController {
	
	
	
	
	@Autowired
	@Qualifier(value="common-native-sql-jsondatagrid-dao")
	private CommonNativeSqlJSONDataGridDaoLayer commonNativeSqlJSONDataGridDaoLayer ; 
	
	
	private Map<String, BaseGridDataProviderNativeSql> nativeSqlProviderMap = new HashMap<String, BaseGridDataProviderNativeSql>() ; 
	
	
	
	/**
	 * 
	 * format dari json untuk jqgrid adalah sbb : 
	 * { 
  "totalPageKey": "xxx",  	- ini berapa total page
  "pagesKey": "yyy", 		- ini page ke berapa
  "recordsCountKey": "zzz", - ini berapa record di temukan based on current query
  "rootData" : [			- ini untuk data
    {"id" :"1", "cell" :["cell11", "cell12", "cell13"]},
    {"id" :"2", "cell" :["cell21", "cell22", "cell23"]},
      ...
  ]
}
	 * 
	 * json provider untuk select data dengan fqcn
	 * 
	 * 
	 * @param sqlProviderfqcn class (full quailified class name) provider query. ini harus visible bagi server code
	 * @param pageSize ukuran page per pembacaan data
	 * @param pagePoisition posisi page data di baca(basis 0)
	 * @param fetchedFieldNames nama-nama fields yang perlu di select
	 * @param sortArgumentsJsonString string json untuk proses sorting. Sorting ngikut pada : {@link id.co.sigma.common.data.query.SimpleSortArgument} di json -kan
	 * @param filterArgumentsJsonString filters di json kan
	 * @param rootDataKey key json untuk root data
	 * @param pagesKey key json untuk page position
	 * @param totalPageKey key json untuk total page
	 * @param recordsCountKey key json untuk record count
	 *  
	 * @return json dengan format spt ini :  
	 * 
	 **/
	@RequestMapping(value={
			"/common-data-layer/nativesql-grid-by-fqcn.json" , 
			"/common-data-layer/nativesql-grid-by-fqcn.app-rpc",
			"/common-data-layer/nativesql-grid-by-fqcn.jsp"
	})
	public @ResponseBody String getGridDataWithNativeSql (
			@RequestParam(value="fqcn")  String sqlProviderfqcn , 
			@RequestParam(value="pageSize" , defaultValue="10") int pageSize , 
			@RequestParam(value="page" , defaultValue="1")int pagePoisition, 
			@RequestParam(value="fields")String fetchedFieldNames[],  
			@RequestParam(value="sorts")String sortArgumentsJsonString , 
			@RequestParam(value="filters")String filterArgumentsJsonString, 
			@RequestParam(value="rootDataKey" ,  defaultValue =  "rootData") String rootDataKey , 
			@RequestParam(value="pagesKey" ,  defaultValue =  "pages") String pagesKey ,
			@RequestParam(value="totalPageKey" ,  defaultValue =  "totalPages") String totalPageKey ,
			@RequestParam(value="recordsCountKey" ,  defaultValue =  "recordsCount") String recordsCountKey 
			){
		try {
			// 1 pastikan dulu class nya ada
			if ( !nativeSqlProviderMap.containsKey(sqlProviderfqcn)){
				BaseGridDataProviderNativeSql prv = (BaseGridDataProviderNativeSql) BeanUtils.instantiate(  Class.forName(sqlProviderfqcn));
				nativeSqlProviderMap.put(sqlProviderfqcn, prv); 
			}
			commonNativeSqlJSONDataGridDaoLayer.setQueryProvider(nativeSqlProviderMap.get(sqlProviderfqcn));
			SimpleSortArgument[] sorts = generateSortArguments(sortArgumentsJsonString); 
			SimpleQueryFilter [] filters = generateFilters(filterArgumentsJsonString);
			//PagedResultHolder<Object[]> readedData =  commonNativeSqlJSONDataGridDaoLayer.getData(fetchedFieldNames, pageSize, pagePoisition, filters, sorts);
		 
			 
		} catch (BeanInstantiationException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}  
		return null ; 
	}
	
	
	private SimpleQueryFilter [] generateFilters(String filterArgumentsJsonString) {
		if ( filterArgumentsJsonString==null||filterArgumentsJsonString.trim().length()==0)
			return null ; 
		JsonParser p = new JsonParser(); 
		JsonElement elems =  p.parse(filterArgumentsJsonString);
		JsonArray arr =  elems.getAsJsonArray() ;
		 
		
		if ( !arr.isJsonNull() ){
			
			int max = arr.size();
			SimpleQueryFilter [] retval = new SimpleQueryFilter [max];
			for ( int i = 0 ; i < max ; i++){
				JsonElement elem =  arr.get(i);
				if ( elem.isJsonNull()){
					retval[i]  = null ;
					continue ; 
				}
				SimpleQueryFilter f = new SimpleQueryFilter();
				retval[i] = f ; 
				
				
				JsonObject obj =  elem.getAsJsonObject(); 
				f.setField(obj.get("field").getAsString()); 
				 
				f.setOperator( SimpleQueryFilterOperator.generateFromString(   obj.get("operator").getAsString()));
				f.setFilter(obj.get("filter").getAsString());
				f.setFilterTypeClass(obj.get("filterTypeClass").getAsString());
				
					
			}
			return retval ; 
		}
		return null ; 
	}
	
	
	
	
	
	private SimpleSortArgument[] generateSortArguments(String sortArgumentsJsonString) {
		if ( sortArgumentsJsonString==null||sortArgumentsJsonString.trim().length()==0)
			return null ;
		
		
		JsonParser p = new JsonParser(); 
		JsonElement elems =  p.parse(sortArgumentsJsonString);
		JsonArray arr =  elems.getAsJsonArray() ;
		if ( arr.isJsonNull())
			return null ; 
		int max = arr.size();
		SimpleSortArgument [] retval = new SimpleSortArgument [max];
		for ( int i = 0 ; i < max ; i++){
			JsonElement elem =  arr.get(i);
			if ( elem.isJsonNull()){
				retval[i]  = null ;
				continue ; 
			}
			
			JsonObject obj =  elem.getAsJsonObject(); 
			
			
			SimpleSortArgument s = new SimpleSortArgument(); 
			retval[i] = s ; 
			s.setSortField(obj.get("sortField").getAsString());
			s.setAscendingSort(obj.get("ascendingSort").getAsBoolean());
		}
		return retval ; 
	}

}
