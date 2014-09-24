package id.co.sigma.commonlib.housekeeper.restore.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import id.co.sigma.commonlib.housekeeper.restore.IJSONFieldFetcher;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseJSONFieldFetcher implements IJSONFieldFetcher{
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(BaseJSONFieldFetcher.class);
	
	
	@Override
	public final void fetchAndPutToMap(JsonObject object, String jsonKey,
			Map<String, Object> placeForPutResult, String keyOnMap) {
		
		placeForPutResult.put(keyOnMap + IJSONFieldFetcher.DATA_TYPE_SUFFIX,  getAcceptedType());
		
		if ( object== null||object.has(jsonKey)){
			return ; 
		}
		JsonElement e = object.get(jsonKey);
		if ( e== null)
	 		return ;
		placeForPutResult.put(keyOnMap,  getActualRepresentation( e));
		
	}
	
	
	/**
	 * membaca actual representation dari JSON data
	 **/
	public abstract Object getActualRepresentation(JsonElement element) ; 
	
	
	
	
	
	/**
	 * get actual object dan menaruh ke dalam map
	 **/
	public Object getCurrentObjectAndPutToMap(JsonReader reader ) {
		String key = null ; 
		try{
			
			reader.nextNull();
			return null ;  
		}catch ( IllegalStateException exc){
			try {
				   return   getActualRepresentation(reader);
			} catch (Exception eInner) {
				logger.error("gagal membaca dari reader untuk key : " + key + "  , error :" + eInner.getMessage());
				return null ;  
			}
			 
		}
		catch ( Exception exc){
			logger.error("gagal membaca dari reader untuk key : " + key + "  , error :" + exc.getMessage());
			return null ;  
		}
		
	}
	
	
	/**
	 * read simple value dari current object
	 **/
	protected abstract Object getActualRepresentation(JsonReader reader) throws Exception ;
	

}
