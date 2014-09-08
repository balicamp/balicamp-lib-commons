package id.co.sigma.commonlib.util;

import id.co.sigma.commonlib.base.ReadSelectStatementMetaData;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class CommonBatchUtil {

	
	
	private static CommonBatchUtil instance ; 
	
	
	private CommonBatchUtil (){}
	
	
	
	
	/**
	 * singleton instance
	 **/
	public static CommonBatchUtil getInstance() {
		if ( instance == null){
			instance = new CommonBatchUtil(); 
		}
		return instance;
	}
	
	/**
	 * regex work, di sini select statement di bongkar , di ganti named parameter dengan ?<br/>Contoh :
	 * <code> 
	 * select * from Sample where A=:XXX and B=:YYY<br/>
	 * menjadi : <br/>
	 * select * from Sample where A=? and B=?<br/>
	 * </code>
	 * @param sqlStatement sql statment yang perlu di clean up
	 **/
	public ReadSelectStatementMetaData normalizeSqlStatement(String sqlStatement) {
		Pattern pattern = Pattern.compile("\\:[a-zA-Z0-9_]+");
		Matcher matcher = pattern.matcher(sqlStatement);
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

}
