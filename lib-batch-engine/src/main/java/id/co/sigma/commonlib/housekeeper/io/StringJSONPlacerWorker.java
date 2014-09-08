package id.co.sigma.commonlib.housekeeper.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class StringJSONPlacerWorker implements id.co.sigma.commonlib.housekeeper.io.JSONPlacerWorker<String>{

	@Override
	public void put(JsonObject targetData, String key, String object) {
		if ( object== null){
			targetData.add(key, JsonNull.INSTANCE);
			return ; 
		}
		String n = (String)object ; 
		targetData.addProperty(key	, n);
		
	}

	@Override
	public String getFromJSON(JsonObject targetData, String key) {
		if (! targetData.has(key)){
			return null ; 
		}
		JsonElement e =  targetData.get(key); 
		return e.getAsString();
	}

	@Override
	public String getSerializationTypeCode() {
		return String.class.getSimpleName().toUpperCase();
	}
}
