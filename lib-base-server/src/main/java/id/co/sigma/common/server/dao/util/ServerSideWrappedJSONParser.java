package id.co.sigma.common.server.dao.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;
import id.co.sigma.common.util.json.WrappedJSONParser;



/**
 * Server side JSON parser container
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public final class ServerSideWrappedJSONParser implements WrappedJSONParser{
	
	
	
	private static ServerSideWrappedJSONParser instance ; 
	
	private ServerSideWrappedJSONParser (){
		
	}
	
	public static ServerSideWrappedJSONParser getInstance() {
		if (instance==null)
			instance = new ServerSideWrappedJSONParser();
		return instance;
	}

	@Override
	public ParsedJSONContainer parseJSON(String jsonString) throws Exception {
		return new ServerSideParsedJSONContainer(jsonString);
	}
	
	@Override
	public ParsedJSONArrayContainer parseJSONArray(String jsonStringArray)
			throws Exception {
		JsonParser p = new JsonParser(); 
		JsonElement elem =  p.parse(jsonStringArray);
		if ( elem== null || !elem.isJsonArray())
			return null ; 
		return new ServerSideParsedJSONArrayContainer(elem.getAsJsonArray());
	}

	@Override
	public ParsedJSONContainer createBlankObject() {
		 
		return new ServerSideParsedJSONContainer();
	}

}
