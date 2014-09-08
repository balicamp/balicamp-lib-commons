package id.co.sigma.commonlib.housekeeper.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class BooleanJSONPlacerWorker implements id.co.sigma.commonlib.housekeeper.io.JSONPlacerWorker<Boolean>{

	@Override
	public void put(JsonObject targetData, String key, Boolean object) {
		if ( object== null){
			targetData.add(key, JsonNull.INSTANCE);
			return ; 
		}
		Boolean n = (Boolean)object ; 
		targetData.addProperty(key	, n);
		
	}

	@Override
	public Boolean getFromJSON(JsonObject targetData, String key) {
		if (! targetData.has(key)){
			return null ; 
		}
		JsonElement e =  targetData.get(key); 
		return e.getAsBoolean();
	}

	@Override
	public String getSerializationTypeCode() {
		return Boolean.class.getSimpleName().toUpperCase();
	}

}
