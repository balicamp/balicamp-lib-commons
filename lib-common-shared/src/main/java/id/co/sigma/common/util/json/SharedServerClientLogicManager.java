package id.co.sigma.common.util.json;

import id.co.sigma.common.util.SimpleDebugerWriter;
import id.co.sigma.common.util.SimpleDebugerWriterManager;



/**
 * container proses non compatible client server. misalnya : 
 * <ol>
 * <li>json</li>
 *  <li>date parsing</li>
 *  </ol>
 * ini bridge agar proses di POJO bisa cross antara client vs server dengan fungsi yang konsisten. 
 * perlu di perhatikan , parser perlu di set. use case nya spt ini : 
 * <ol>
 * <li>di code client, parser musti di set pada saat onModulLoad. anda bisa mempergunakan : {@link id.co.sigma.common.client.util.ClientSideWrappedJSONParser}</li>
 * <li>di sisi server ini perlu di set pada saat akan di pergunakan(singleton object, recomendasi pada request)</li>
 * </ol>
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public final class SharedServerClientLogicManager {
	
	
	private WrappedJSONParser jsonParser ; 
	
	
	/**
	 * parser date time. ini di desain aman utnuk kasus server vs client
	 **/
	private CrossDateTimeParser dateTimeParser; 
	private static SharedServerClientLogicManager instance ; 
	
	
	
	
	private SharedServerClientLogicManager(){}
	
	
	public static SharedServerClientLogicManager getInstance() {
		if ( instance ==null)
			instance = new SharedServerClientLogicManager(); 
		return instance;
	}
	
	
	
	/**
	 * menaruh parser json ke dlaam singleton 
	 **/
	public void setJSONParser(WrappedJSONParser parser) {
		this.jsonParser = parser;
	}
	
	
	
	/**
	 * get json parser
	 **/
	public WrappedJSONParser getJSONParser() {
		return jsonParser;
	}
	/**
	 * parser date time. ini di desain aman utnuk kasus server vs client
	 **/
	
	public CrossDateTimeParser getDateTimeParser() {
		return dateTimeParser;
	}
	/**
	 * parser date time. ini di desain aman utnuk kasus server vs client
	 **/
	public void setDateTimeParser(CrossDateTimeParser dateTimeParser) {
		this.dateTimeParser = dateTimeParser;
	}
	
	
	
	private SimpleDebugerWriterManager debugerWriterManager ; 
	
	public SimpleDebugerWriterManager getDebugerWriterManager() {
		return debugerWriterManager;
	}
	
	public void setDebugerWriterManager(
			SimpleDebugerWriterManager debugerWriterManager) {
		this.debugerWriterManager = debugerWriterManager;
	}
	
	
	
	public SimpleDebugerWriter getDebugWritter ( Class<?> owner ){
		return this.debugerWriterManager.getDebugWriter(owner.getName());
	}
	 
	
	
	
        
        
        
	
	
	 
}
