package id.co.sigma.commonlib.housekeeper.io;

import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class NumberJSONPlacerWorker implements id.co.sigma.commonlib.housekeeper.io.JSONPlacerWorker<Number>{

	@Override
	public void put(JsonObject targetData, String key, Number object) {
		if ( object== null){
			targetData.add(key, JsonNull.INSTANCE);
			return ; 
		}
		targetData.addProperty(key	, object);
		
	}

	@Override
	public Number getFromJSON(JsonObject targetData, String key) {
		if (! targetData.has(key)){
			return null ; 
		}
		JsonElement e =  targetData.get(key); 
		return e.getAsNumber();
	}
	
	@Override
	public String getSerializationTypeCode() {
		return Number.class.getSimpleName().toUpperCase();
	}

}
