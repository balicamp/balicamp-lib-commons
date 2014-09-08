package id.co.sigma.common.server.service.datagrid.impl;

import id.co.sigma.common.data.DataConverter;
import id.co.sigma.common.data.impl.BigIntegerDataConverter;
import id.co.sigma.common.data.impl.DoubleDataConverter;
import id.co.sigma.common.data.impl.FloatDataConverter;
import id.co.sigma.common.data.impl.IntegerDataConverter;
import id.co.sigma.common.data.impl.LongDataConverter;
import id.co.sigma.common.data.query.SimpleQueryFilter;

import java.math.BigInteger;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * class untuk wrap hasil proses normalisasi query
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class QueryNormalizeResult {
	
	
	/**
	 * class holder untuk proses normalisasi query. hasilnya sql statement dan parameter untuk query
	 **/
	public class QueryReadyData {
		/**
		 * string query untuk di trigger
		 **/
		private String normalizeQuery ; 
		/**
		 * parameter untuk query
		 **/
		private Object[] queryParameters ;
		/**
		 * tipe data dari object parameter query. ini ikut dengan {@link Types}
		 **/
		private int  [] queryParameterSqlTypes ;
		
		/**
		 * string query untuk di trigger
		 **/
		public String getNormalizeQuery() {
			return normalizeQuery;
		}
		/**
		 * string query untuk di trigger
		 **/
		public void setNormalizeQuery(String normalizeQuery) {
			this.normalizeQuery = normalizeQuery;
		}
		
		/**
		 * parameter untuk query
		 **/
		public Object[] getQueryParameters() {
			return queryParameters;
		}
		/**
		 * parameter untuk query
		 **/
		public void setQueryParameters(Object[] queryParameters) {
			this.queryParameters = queryParameters;
		}
		
		/**
		 * tipe data dari object parameter query. ini ikut dengan {@link Types}
		 **/
		public int[] getQueryParameterSqlTypes() {
			return queryParameterSqlTypes;
		}
		/**
		 * tipe data dari object parameter query. ini ikut dengan {@link Types}
		 **/
		public void setQueryParameterSqlTypes(int[] queryParameterSqlTypes) {
			this.queryParameterSqlTypes = queryParameterSqlTypes;
		}
				
	}
	
	
	
	/**
	 * converter data dari string ke representasi asli dari data. karena data di kirimkan dari client dalam bentuk string. jadinya agar JDBC friendly, musti di kembalikan ke object java lagi
	 * 
	 **/
	private static final Map<String, DataConverter<?>> CONVERTERS = new HashMap<String, DataConverter<?>>();
	
	
	/**
	 * tipe object parameter vs tipe sql. ini untuk feed query agar presisi. 
	 * ini dalam kasus null, query bisa error
	 **/
	private static final Map<String , Integer > SQL_TYPE_MAP = new HashMap<String, Integer>(); 
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat(SimpleQueryFilter.DATE_ONLY_STRING_SERIALIZATION_PATTERN);
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(QueryNormalizeResult.class); 
	
	
	
	static {
		SQL_TYPE_MAP.put(java.lang.Long.class.getName(), Types.INTEGER); 
		SQL_TYPE_MAP.put(java.lang.Integer.class.getName(), Types.INTEGER);
		SQL_TYPE_MAP.put(java.lang.Short.class.getName(), Types.SMALLINT);
		SQL_TYPE_MAP.put(java.lang.String.class.getName(), Types.VARCHAR);
		SQL_TYPE_MAP.put(java.util.Date.class.getName(), Types.DATE);
		
		SQL_TYPE_MAP.put(java.lang.Float.class.getName(), Types.FLOAT);
		SQL_TYPE_MAP.put(java.lang.Double.class.getName(), Types.DOUBLE);
		SQL_TYPE_MAP.put(java.math.BigInteger.class.getName(), Types.BIGINT);
		SQL_TYPE_MAP.put(java.math.BigDecimal.class.getName(), Types.DECIMAL);
		SQL_TYPE_MAP.put(java.lang.Boolean.class.getName(), Types.BOOLEAN);
		
	}
	
	
	
	static {
		CONVERTERS.put(BigInteger.class.getName(), new BigIntegerDataConverter()); 
		CONVERTERS.put(Long.class.getName(), new LongDataConverter());
		CONVERTERS.put(Integer.class.getName(), new IntegerDataConverter());
		CONVERTERS.put(Float.class.getName(), new FloatDataConverter());
		CONVERTERS.put(Double.class.getName(), new DoubleDataConverter());
		CONVERTERS.put(Date.class.getName(), new DataConverter<Date>() {
			@Override
			public Date translateData(String stringRepresentation) {
				try {
					return formatter.parse(stringRepresentation);
				} catch (Exception e) {
					logger.error("gagal konversi date untuk date string  : " +  stringRepresentation + ",error message :" + e.getMessage()  + e  );
					return null ; 
				}
				
				
			}
		}
		);
		
	}
	
	/**
	 * query hasil normalisasi parameter
	 **/
	private String queryStatement ; 
	
	
	
	
	
	/**
	 * map tipe data yang di kirim dalam array of filters, ini untuk memasukan kembali ke dalam JDBC
	 **/
	private Map<String, String> parameterDataTypeMap = new HashMap<String, String>(); 
	
	
	
	
	/**
	 * map dari parameter , data yang di pergunakan dalam proses filtering
	 **/
	private Map<String, Object>  parameterDataMap = new HashMap<String, Object>(); 
	
	
	public QueryNormalizeResult(SimpleQueryFilter [] filters ){
		if ( filters!= null&& filters.length>0){
			for ( SimpleQueryFilter scn : filters){
				String key = scn.getField().toUpperCase() ; 
				
				parameterDataTypeMap.put( key.toUpperCase()	,  scn.getFilterTypeClass()  ) ;
				parameterDataMap.put(key.toLowerCase(), scn.getFilter());
				 
				 
			}
		}
	}
	
	
	
	/**
	 * query hasil normalisasi parameter
	 **/
	public void setQueryStatement(String queryStatement) {
		this.queryStatement = queryStatement;
	}
	/**
	 * query hasil normalisasi parameter
	 **/
	public String getQueryStatement() {
		return queryStatement;
	}
	
	
	
	
	/**
	 * proses ini menormalkan query. feed nya querry dengan named parameter semacam ini: <br>
	 * <code>
	 * select a.* from<br/> 
	 * table_a a  <br/>
	 * where <br/>
	 * 	a.id=:PARAM 
	 * </code>
	 * di awali dengan tanda <i>:</i>  untuk parameter query
	 * @param queryStatement statement query yang perlu di normalkan
	 **/
	public QueryReadyData normalizeQuery (String queryStatement ){
		Pattern pattern = Pattern.compile("\\:[a-zA-Z0-9_]+");
		Matcher matcher = pattern.matcher(queryStatement);
		StringBuffer result = new StringBuffer(); 
		
		
		
		ArrayList<String> namedParams = new ArrayList<String>();  
		while (matcher.find()) {
			namedParams.add(matcher.group().substring(1).toUpperCase());  
			matcher.appendReplacement(result, "?"); 
		}
		 
		matcher.appendTail(result);
		QueryReadyData retval = new QueryReadyData(); 
		retval.setNormalizeQuery(matcher.toString());
		
		if ( namedParams.size()>0){
			int ttl = namedParams.size() ; 
			Object[] paramVal = new Object[ttl];
			int[] types = new int[ttl];
			retval.setQueryParameters(paramVal); 
			retval.setQueryParameterSqlTypes(types);  
			for ( int i = 0 ; i < ttl; i++){
				String key =  namedParams.get(i);
				paramVal[i] = parameterDataMap.get(key); 
				if ( !SQL_TYPE_MAP.containsKey(key)){
					logger.error("tipe data : " + key +",class anda perlu di update");
				}else{
					types[i] = SQL_TYPE_MAP.get( parameterDataTypeMap.get(  key));
				}
			}
		}
		return retval ; 
		  
	}
}

