package id.co.sigma.common.util.json;



/**
 * interface wrapper json. ini interface untuk membridge proses di server vs di client
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface WrappedJSONParser {
	
	
	/**
	 * parse JSON String 
	 **/
	public ParsedJSONContainer parseJSON(String jsonString )throws Exception ;
	
	
	
	/**
	 * parse array dari string json
	 */
	public ParsedJSONArrayContainer parseJSONArray ( String jsonStringArray) throws Exception;  
	
	/**
	 * membuat blank object
	 **/
	public ParsedJSONContainer createBlankObject () ; 
	
	

}
