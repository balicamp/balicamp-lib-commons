package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * wrapper class. ini untuk passing data ke client + token double submit avoidence. ini untuk mengindari double submit data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DataWithToken<DATA extends IJSONFriendlyObject<DATA>> implements IJSONFriendlyObject<DataWithToken<DATA>>  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7339387525910995510L;



	/**
	 * token untuk proses edit data. token ini untuk di kirimkan pada saat merequest kirim data
	 */
	private String token;
	
	
	
	/**
	 * actual data yang hendak di kirim ke client
	 */
	private DATA data; 
	
	
	
	public DataWithToken() {
		
	}
	
	
	public DataWithToken(String token, DATA data) {
		super();
		this.token = token;
		this.data = data;
	}


	/**
	 * token untuk proses edit data. token ini untuk di kirimkan pada saat merequest kirim data
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * token untuk proses edit data. token ini untuk di kirimkan pada saat merequest kirim data
	 */
	public String getToken() {
		return token;
	}
	/**
	 * actual data yang hendak di kirim ke client
	 */
	public void setData(DATA data) {
		this.data = data;
	}
	/**
	 * actual data yang hendak di kirim ke client
	 */
	public DATA getData() {
		return data;
	}
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("token", token);
		if ( data!= null){
			jsonContainer.put("actData", data);
		}
		
	}
	@Override
	public DataWithToken<DATA> instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		DataWithToken<DATA> retval = new DataWithToken<DATA>();
		retval.token = jsonContainer.getAsString("token"); 
		if ( jsonContainer.contain("actData")){
			String fqcn = jsonContainer.getAsString( "actData"+  ParsedJSONContainer.JSON_ARRAY_DATA_TYPE_SUFFIX); 
			retval.data  =  jsonContainer.get("actData", fqcn); 
		}
		return retval;
	}

}
