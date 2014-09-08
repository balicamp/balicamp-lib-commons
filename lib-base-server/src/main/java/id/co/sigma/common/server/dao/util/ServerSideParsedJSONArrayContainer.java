package id.co.sigma.common.server.dao.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import id.co.sigma.common.util.json.ParsedJSONArrayContainer;
import id.co.sigma.common.util.json.ParsedJSONContainer;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class ServerSideParsedJSONArrayContainer implements ParsedJSONArrayContainer{

	private JsonArray jsonArray ; 
	
	
	
	/**
	 * tipe data dalam array
	 **/
	private String dataTypeFQCN ; 
	
	public static void main (String[] args ) {
		JsonArray arr = new JsonArray(); 
		arr.add(new JsonPrimitive("nama 1"));
		arr.add(new JsonPrimitive("nama 2"));
		System.out.println(arr.toString());
	}
	
	public ServerSideParsedJSONArrayContainer(){
		this.jsonArray = new JsonArray(); 
		
		
	}
	public ServerSideParsedJSONArrayContainer(JsonArray rawData){
		this.jsonArray = rawData ; 
	}
	
	
	
	@Override
	public ParsedJSONContainer get(int index) {
		if ( index>= length())
			return null ;
		return new ServerSideParsedJSONContainer(jsonArray.get(index).getAsJsonObject());
	}

	@Override
	public int length() {
		return jsonArray.size();
	}
	
	/**
	 * tipe data dalam array
	 **/
	public void setDataTypeFQCN(String dataTypeFQCN) {
		this.dataTypeFQCN = dataTypeFQCN;
	}
	/**
	 * tipe data dalam array
	 **/
	public String getDataTypeFQCN() {
		return dataTypeFQCN;
	}
	@Override
	public String getAsString(int index) {
		try {
			return this.jsonArray.get(index).getAsString(); 
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
		
	}
	@Override
	public Double getAsNumber(int index) {
		JsonElement e =  jsonArray.get(index);
		if ( e== null ||!e.isJsonPrimitive())
			return null ; 
		return e.getAsDouble(); 
	}
	@Override
	public Boolean getAsBoolean(int index) {
		JsonElement e =  jsonArray.get(index);
		if ( e== null ||!e.isJsonPrimitive())
			return null ; 
		return e.getAsBoolean(); 
	}

	@Override
	public void appendToArray(ParsedJSONContainer jsonData) {
		ServerSideParsedJSONContainer c = (ServerSideParsedJSONContainer) jsonData ; 
		jsonArray.add(  c.getJsonObject()); 
		
	}

	@Override
	public String getJSONString() {
		return jsonArray.toString();
	}

}

