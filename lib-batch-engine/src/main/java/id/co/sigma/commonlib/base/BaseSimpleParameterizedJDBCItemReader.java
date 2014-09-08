package id.co.sigma.commonlib.base;

import id.co.sigma.commonlib.base.serializer.BigIntegerToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.DateToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.DoubleToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.LongToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.ObjectToJobParamSerializer;
import id.co.sigma.commonlib.base.serializer.StringToJobParamSerializer;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.database.JdbcCursorItemReader;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseSimpleParameterizedJDBCItemReader<DATA> extends JdbcCursorItemReader<DATA> {

	
	
	protected static Map<String, ObjectToJobParamSerializer> PREDEFINED_SERIALIZER = new HashMap<String, ObjectToJobParamSerializer>();
	
	
	
	
	/**
	 * setter parameter. ini hanya berguna kalau misalnya anda menyertakan parameter
	 */
	protected SourceDataPreparedStatementSetter sourceDataPreparedStatementSetter = new SourceDataPreparedStatementSetter();
	
	
	/**
	 * meta data. 
	 **/
	protected ReadSelectStatementMetaData readSelectStatementMetaData;
	
	static {
		PREDEFINED_SERIALIZER.put(String.class.getName(), new StringToJobParamSerializer()); 
		
		PREDEFINED_SERIALIZER.put(long.class.getName(), new LongToJobParamSerializer());
		PREDEFINED_SERIALIZER.put(Long.class.getName(), new LongToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(Double.class.getName(), new DoubleToJobParamSerializer());
		PREDEFINED_SERIALIZER.put(double.class.getName(), new DoubleToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(BigInteger.class.getName(), new BigIntegerToJobParamSerializer());
		
		PREDEFINED_SERIALIZER.put(Date.class.getName(), new DateToJobParamSerializer());
		
	}
	
	/**
	 * regex work, di sini select statement di bongkar , di ganti named parameter dengan ?
	 **/
	protected ReadSelectStatementMetaData normalizeSqlStatement(String selectStatment) {
		Pattern pattern = Pattern.compile("\\:[a-zA-Z0-9_]+");
		Matcher matcher = pattern.matcher(selectStatment);
		StringBuffer result = new StringBuffer();
		ArrayList<String>   namedParamArray = new ArrayList<String>(); 
		while (matcher.find()) {
			namedParamArray.add(matcher.group().substring(1));  
			matcher.appendReplacement(result, "?"); 
		}
		matcher.appendTail(result); 
		ReadSelectStatementMetaData retval = new ReadSelectStatementMetaData();
		retval.setNormalizeStatement(result.toString());
		String[] arr = new String[namedParamArray.size()];
		namedParamArray.toArray(arr); 
		retval.setNamedParameterArray(arr); 
		 
		
		return retval ; 
	}
	
	
	
	/**
	 * Set SQL. ini otomatis juga akan me run normalisasi data
	 **/
	@Override
	public void setSql(String sql) {
		this.readSelectStatementMetaData =  normalizeSqlStatement(sql); 
		this.sourceDataPreparedStatementSetter.setNamedParameters(readSelectStatementMetaData.getNamedParameterArray());
		String sqlSmt =readSelectStatementMetaData.getNormalizeStatement() ;
		System.out.println("sql : " + sqlSmt);
		super.setSql(sqlSmt);
		
	}
	

}
