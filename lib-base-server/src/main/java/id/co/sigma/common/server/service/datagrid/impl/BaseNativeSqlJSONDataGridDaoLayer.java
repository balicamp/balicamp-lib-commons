/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.sigma.common.server.service.datagrid.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import id.co.sigma.common.data.query.BaseGridDataProviderNativeSql;
import id.co.sigma.common.data.query.SimpleQueryFilter;
import id.co.sigma.common.data.query.SimpleSortArgument;
import id.co.sigma.common.server.service.datagrid.IJSONDataGridProvider;
import id.co.sigma.common.server.service.datagrid.SimplifiedGridDataRowWrapper;
import id.co.sigma.common.server.service.datagrid.SimplifiedGridDataWrapper;
import id.co.sigma.common.server.service.datagrid.impl.QueryNormalizeResult.QueryReadyData;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

/**
 *
 * Dao yang comply dengan {@link IJSONDataGridProvider}, ini menyediakan query dengan hasil {@link Object}[] , sehingga siap di konsumsi oleh aplikasi
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 */
public abstract class BaseNativeSqlJSONDataGridDaoLayer implements  IJSONDataGridProvider{

    
	
	
	private static final Logger  logger = LoggerFactory.getLogger(BaseNativeSqlJSONDataGridDaoLayer.class); 
	 
	
    @Autowired
    private DataSource dataSource ; 
    
    
   
    @Override
    public SimplifiedGridDataWrapper getData(String[] fetchedFieldNames,
    		int pageSize, int pagePosition,
    		SimpleQueryFilter[] queryFilters,
    		SimpleSortArgument[] sortArguments) {
    	QueryNormalizeResult normalizer = new QueryNormalizeResult(queryFilters); 
    	Long cnt = countData(queryFilters, normalizer); 
    	if ( cnt==null||cnt.longValue()==0)
    		return null ; 
    	
    	SimplifiedGridDataWrapper retval = new SimplifiedGridDataWrapper();
    	int f1stRow =  retval.adjustPagination(pagePosition, pageSize, cnt.intValue());
    	
    	return null;
    }
    
    
    
    /**
     * count berapa data yang match dengan query yang di request
     * @param queryFilters filter query 
     * @param sortArguments sort arguments untuk data. data di sort berdasarkan apa
     * 
     **/
    protected Long countData (SimpleQueryFilter[] queryFilters , QueryNormalizeResult  resultHolder) {
    	String orgQuery  = getQueryProvider().generateCountSql(queryFilters, "cnt") ;
    	QueryReadyData readyData =  resultHolder.normalizeQuery(orgQuery) ; 
    	Long data =  (new JdbcTemplate(dataSource)).queryForLong(readyData.getNormalizeQuery()	 ,readyData.getQueryParameters()  ,  readyData.getQueryParameterSqlTypes());
    	return data ; 
    }
    
    
    protected List<SimplifiedGridDataRowWrapper> queryData (QueryNormalizeResult  resultHolder , String[] fetchedFieldNames , int fetchSize , int firstRowPositionToFetch , SimpleQueryFilter[] queryFilters , SimpleSortArgument[] sortArguments) {
    	String orgQuery  = getQueryProvider().generateDataSql("GRID_DATA_ID___________"  ,  fetchedFieldNames, fetchSize, firstRowPositionToFetch, queryFilters, sortArguments); 
    	QueryReadyData readyData =  resultHolder.normalizeQuery(orgQuery) ;
    	
    	
    	 SqlRowSet sqlRowSet = (new JdbcTemplate(dataSource)).queryForRowSet(readyData.getNormalizeQuery() , readyData.getQueryParameters(), readyData.getQueryParameterSqlTypes()) ;
    	 
    	  
    	 
    	 if ( sqlRowSet.next()){
    		 SqlRowSetMetaData metadata =  sqlRowSet.getMetaData();
    		 fetchMetaData(fetchedFieldNames, metadata); 
    	 }
    	 else
    		 return null ; 
    	 while ( sqlRowSet.next()){
    		 
    	 }
    	 
		
		
    	
    	return null ;  
    } 
    
    
    
    
    
    /**
     * membaca metaadata dari column
     * @param fetchedFieldNames field-field yang perlu di baca metadatanya dari hasil select
     * @param metadata metadata query result
     **/
    protected Class<?>[] fetchMetaData ( String[] fetchedFieldNames  ,  SqlRowSetMetaData metadata) {
    	if ( metadata==null|| fetchedFieldNames ==null ||fetchedFieldNames.length==0)
    		return null ; 
    	Map<String, String> indexedDataType = new HashMap<String, String>() ; 
    	for ( int i=1; i <= metadata.getColumnCount(); i++){
    		String clsType =  metadata.getColumnClassName(i) ;
    		String colName = metadata.getColumnName(i); 
    		indexedDataType.put(colName.toUpperCase(), clsType) ; 
    	}
    	Class<?>[] retval = new Class<?>[indexedDataType.size()] ;
    	
    	  
    	
    	for ( int i = 0 ; i< fetchedFieldNames.length ; i++){
    		String col = fetchedFieldNames[i].toUpperCase();
    		if ( indexedDataType.containsKey(col)){
    			try {
					retval[i] =   Class.forName(  indexedDataType.get(col));
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				}
    		}
    			 
    	}
    	return retval ; 
    	
    }
    
    
    
    
    /**
     * method ini generate Native SQL statement untuk query ke dalam data list
     **/
    protected abstract BaseGridDataProviderNativeSql getQueryProvider() ;  
}
