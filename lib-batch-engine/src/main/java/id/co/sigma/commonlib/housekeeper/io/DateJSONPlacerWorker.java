package id.co.sigma.commonlib.housekeeper.io;

import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class DateJSONPlacerWorker implements id.co.sigma.commonlib.housekeeper.io.JSONPlacerWorker<Date>{

	@Override
	public void put(JsonObject targetData, String key, Date object) {
		if ( object== null){
			targetData.add(key, JsonNull.INSTANCE);
			return ; 
		}
		Date n = (Date)object ; 
		targetData.addProperty(key	, n.getTime());
		
	}

	@Override
	public Date getFromJSON(JsonObject targetData, String key) {
		if (! targetData.has(key)){
			return null ; 
		}
		JsonElement e =  targetData.get(key);
		Number n =  e.getAsNumber();
		if ( n== null)
			return null ;
		Date d = new Date(n.longValue());
		return d;
	}

	@Override
	public String getSerializationTypeCode() {
		return Date.class.getSimpleName().toUpperCase();
	}
}
