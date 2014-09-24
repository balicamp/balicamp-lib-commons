package id.co.sigma.common.server.dao.util;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import id.co.sigma.common.util.ObjectGeneratorManager;
import id.co.sigma.common.util.json.BaseParsedJSONContainer;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONArrayContainer;

import java.math.BigDecimal;



/**
 * JSON container di sisi server
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class ServerSideParsedJSONContainer extends BaseParsedJSONContainer{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ServerSideParsedJSONContainer.class); 
	
	private JsonObject  jsonObject ; 
	
	
	public ServerSideParsedJSONContainer(JsonObject  rawData){
		jsonObject = rawData ; 
	}
	
	public ServerSideParsedJSONContainer(String jsonString){
		JsonParser parser = new JsonParser();  
		JsonElement jsonElement = parser.parse(jsonString);
		jsonObject =  jsonElement.getAsJsonObject(); 
		
	}

        @Override
        public BigDecimal getAsBigDecimal(String key) {
            Number swap = getAsNumber(key); 
            if ( swap == null)
                return null; 
            return new BigDecimal(swap.toString()); 
        }
        
        

   
	
        
        
	public ServerSideParsedJSONContainer(){
		this.jsonObject = new JsonObject(); 
	}

	@Override
	public String getAsString(String key) {
		JsonElement e =  null ; 
		if ( !key.contains(".")){
			e = jsonObject.get(key);
		}
		else
			e = getByPath(jsonObject, key);
			 
		if ( e==null||e.isJsonNull())
			return null ; 
		return e.getAsString();
	}

	@Override
	public Boolean getAsBoolean(String key) {
		JsonElement e =  null ; 
		if ( !key.contains(".")){
			e = jsonObject.get(key);
		}
		else
			e = getByPath(jsonObject, key);
			 
		if ( e==null||e.isJsonNull())
			return null ; 
		return e.getAsBoolean();
	}

	@Override
	public Double getAsNumber(String key) {
		JsonElement e =  null ; 
		if ( !key.contains(".")){
			e = jsonObject.get(key);
		}
		else
			e = getByPath(jsonObject, key);
			 
		if ( e==null||e.isJsonNull())
			return null ; 
		return e.getAsDouble();
	}
	
	
	public JsonElement getByPath (JsonObject objectRef , String path ) {
		String [] pathArr = path.split("\\."); 
		JsonObject curr = objectRef ; 
		for ( int i = 0 ; i < pathArr.length-2 ; i++){
			
			try {
				JsonElement swap = curr.get(pathArr[i]);
				curr = swap.getAsJsonObject();
			} catch (Exception e) {
				return null ; 
			}
		}
		if ( curr ==null )
			return null ; 
		return   curr.get(pathArr[pathArr.length-1]); 
		
		
	}

	
	public static void main (String [] args){
		
		String pathDot = "a.b.c"; 
		System.out.println(pathDot.contains("."));
		String jsonPath ="{\"a\":{\"name\":\"gede sutarsa\", \"nested\":1}}"; 
		JsonParser parser = new JsonParser();
		JsonElement elem =  parser.parse(jsonPath);
		JsonObject obj =  elem.getAsJsonObject() ;
		JsonElement swapE =   obj.get("a");
		
		System.out.println(swapE.getAsJsonObject().get("nested").toString());
		
				
				
	}

	@Override
	public void put(String key, String value) {
		
		if (! key.contains(".")){
			jsonObject.addProperty( key ,  value);
		}
		else{
			placeDeepPath(key, value);
		}
	}

	@Override
	public void put(String key, Double value) {
		if (! key.contains(".")){
			jsonObject.addProperty( key ,  value);
		}
		else{
			placeDeepPath(key, value);
		}
	}

	@Override
	public void put(String key, Boolean value) {
		if (! key.contains(".")){
			jsonObject.addProperty( key ,  value);
		}
		else{
			placeDeepPath(key, value);
		}
	}
	
	private void placeDeepPath (String key , Object valueToPush){
		String [] path = key.split("\\.");
		JsonObject current = jsonObject ; 
		for ( int i=0 ; i < path.length-2 ; i++){
			JsonElement val =  current.get( path[i]);
			if ( val== null||val.isJsonNull() || !val.isJsonObject()){
				JsonObject baru = new JsonObject(); 
			 
				current.add(    path[i],baru );
				current = baru ; 
			}else{
				current = val.getAsJsonObject(); 
			}
		}
		
		if ( current != null){
			String latesKey = path[path.length-1];
			if ( valueToPush==null)
				current.add(latesKey, JsonNull.INSTANCE); 
			else if ( valueToPush instanceof String)
				current.addProperty(latesKey, (String)valueToPush);
			else if ( valueToPush instanceof Number)
				current.addProperty(latesKey, (Number)valueToPush);
			else if ( valueToPush instanceof Boolean)
				current.addProperty(latesKey, (Boolean)valueToPush);
		}
	}

	@Override
	public String getJSONString() {
		return (this.jsonObject==null)? null : jsonObject.toString();
	}
	
	@Override
	public String toString() {
		return getJSONString();
	}

	@Override
	public void put(String key,
			List<? extends IJSONFriendlyObject<?>> arrayOfData) {
		if ( arrayOfData==null|| arrayOfData.isEmpty()){
			jsonObject.add(key, JsonNull.INSTANCE);
		}else {
			String keyType =key + JSON_ARRAY_DATA_TYPE_SUFFIX;
			String fqcn = arrayOfData.get(0).getClass().getName();
			jsonObject.addProperty(keyType, fqcn);
			JsonArray arr = new JsonArray(); 
			jsonObject.add(key, arr); 
			for ( IJSONFriendlyObject<?> scn : arrayOfData){
				ServerSideParsedJSONContainer chld = new ServerSideParsedJSONContainer(); 
				scn.translateToJSON(chld); 
				arr.add(chld.jsonObject);
			}
		}
	}

	@Override
	public ParsedJSONArrayContainer getAsArray(String key) {
		String keyType =key + JSON_ARRAY_DATA_TYPE_SUFFIX;
		
		JsonElement arrRaw = this.jsonObject.get(key); 
		if ( arrRaw==null)
			return null ; 
		JsonArray arrActual = arrRaw.getAsJsonArray(); 
		ServerSideParsedJSONArrayContainer arr = new ServerSideParsedJSONArrayContainer(arrActual);
		JsonElement e =  this.jsonObject.get(keyType);
		if( e!=null){
			String fqcn =e.getAsString();
			if ( fqcn!=null&&fqcn.length()>0) 
				arr.setDataTypeFQCN(fqcn);
		}
			 
		
		return arr;
	}
	
	@Override
	public ParsedJSONArrayContainer getAsArray() {
		if (!jsonObject.isJsonArray())
			return null; 
		JsonArray arr =  jsonObject.getAsJsonArray();
		return new ServerSideParsedJSONArrayContainer( arr);
	}

	@Override
	public <T extends IJSONFriendlyObject<T>> ArrayList<T> getAsArraylist(
			String key, String objectTypeFQCN) {
		
		if (! this.jsonObject.has(key))
			return null ; 
		 
		 
		try {
			@SuppressWarnings("unchecked")
			T sample = (T) BeanUtils.instantiate(Class.forName( objectTypeFQCN));
			JsonElement swapE = jsonObject.get(key)  ; 
			JsonArray arr =  swapE.getAsJsonArray() ;
			if  ( arr==null || arr.size()==0)
				return null ; 
			ArrayList<T> retval = new ArrayList<T>(); 
			for ( int i = 0 ; i< arr.size(); i++){
				
				JsonObject swap = arr.get(i).getAsJsonObject() ; 
				if ( swap==null)
					retval.add(null) ; 
				else
					retval.add(sample.instantiateFromJSON(new ServerSideParsedJSONContainer(swap)));
			}
			
			return retval ; 
		} catch (Exception e) {
			logger.error("gagal translate object untuk key : " + key + " , error message : " + e.getMessage());
			return null;
		}
		
		
	}

    @Override
    public <T extends IJSONFriendlyObject<T>> void put(String key, T subObject) {
        if ( subObject==null){
            jsonObject.add(key, JsonNull.INSTANCE);
        }else{
            ServerSideParsedJSONContainer sub = new ServerSideParsedJSONContainer(); 
            subObject.translateToJSON(sub);
            jsonObject.add(key, sub.jsonObject);
            jsonObject.addProperty( key +JSON_ARRAY_DATA_TYPE_SUFFIX, subObject.getClass().getName());
        }
    }

    @Override
    public <T extends IJSONFriendlyObject<T>> T getAsSubJSONObject(String key, T sampleObjectForConverter) {
        if ( sampleObjectForConverter== null || key== null || key.length()==0)
            return null ; 
        if (! jsonObject.has(key))
            return null ; 
        
        JsonElement e =  jsonObject.get(key) ; 
        if ( e== null|| JsonNull.INSTANCE.equals(e))
        	return null; 
        JsonObject swapObj = e.getAsJsonObject(); 
        if (jsonObject==null)
            return null ; 
        
        ServerSideParsedJSONContainer prs = new ServerSideParsedJSONContainer(swapObj); 
        return  sampleObjectForConverter.instantiateFromJSON(prs); 
    }
    @Override
    public <T extends IJSONFriendlyObject<T>> T getAsSubJSONObject(String key) {
    	 if (! jsonObject.has(key + JSON_ARRAY_DATA_TYPE_SUFFIX))
             return null ; 
    	 T sample =   ObjectGeneratorManager.getInstance().instantiateSampleObject( jsonObject.get(key+ JSON_ARRAY_DATA_TYPE_SUFFIX).getAsString());
    	 
    	return getAsSubJSONObject(key, sample);
    }
    

	@Override
	public void appendToArray(String key, String value) {
		if (value!= null ) 
			getOrCreateArray(key,String.class.getName()).add(new JsonPrimitive(value));
		else
			getOrCreateArray(key, String.class.getName()).add(JsonNull.INSTANCE);
		
	}

	@Override
	public void appendToArray(String key, Boolean value) {
		if (value!= null ) 
			getOrCreateArray(key, Boolean.class.getName()).add(new JsonPrimitive(value));
		else
			getOrCreateArray(key, Boolean.class.getName()).add(JsonNull.INSTANCE);
		
	}

	@Override
	public void appendToArray(String key, Double value) {
		if (value!= null ) 
			getOrCreateArray(key, Double.class.getName()).add(new JsonPrimitive(value));
		else
			getOrCreateArray(key, Double.class.getName()).add(JsonNull.INSTANCE);
		
	}

	@Override
	public void appendToArray(String key, IJSONFriendlyObject<?>[] value) {
		if ( value== null|| value.length==0)
			return  ; 
		String fqcn = null ; 
		for ( IJSONFriendlyObject<?> scn : value){
			if ( scn== null)
				continue ; 
			fqcn = scn.getClass().getName() ; 
			break ;	
		}
		if ( fqcn== null){
			jsonObject.add(key, JsonNull.INSTANCE); 
			return ; 
		}
		JsonArray arr = getOrCreateArray(key, fqcn);
		for ( IJSONFriendlyObject<?> scn : value){
			ServerSideParsedJSONContainer p = new ServerSideParsedJSONContainer(); 
			scn.translateToJSON( p); 
			arr.add(p.jsonObject); 
		}
		
	}
	
	
	@Override
	public void appendToArray(String key, IJSONFriendlyObject<?> value) {
		if ( value== null ){
			if ( this.jsonObject.has(key)){
				JsonElement e = jsonObject.get(key);
				if ( e== null|| JsonNull.INSTANCE.equals(e))
					return  ;
				
				
				jsonObject.get(key).getAsJsonArray().add(JsonNull.INSTANCE);
			}else{
				JsonArray arr = new JsonArray(); 
				arr.add(JsonNull.INSTANCE);
				jsonObject.add(key, arr);
			}
		}else{
			JsonArray arr = getOrCreateArray(key, value.getClass().getName());
			ServerSideParsedJSONContainer p = new ServerSideParsedJSONContainer(); 
			value.translateToJSON( p); 
			arr.add(p.jsonObject);
		}
		
		
	}
    
	
	
	protected JsonArray getOrCreateArray (String key, String fqcn ){
		if ( jsonObject.has(key)){
			JsonElement e =  jsonObject.get(key);
			if (!( e== null || JsonNull.INSTANCE.equals(e))){
				return e.getAsJsonArray();
			}
				
			
		}
		JsonArray arr = new JsonArray(); 
		jsonObject.add(key, arr);
		jsonObject.addProperty(key + JSON_ARRAY_DATA_TYPE_SUFFIX	, fqcn);
		return arr; 
	}

	@Override
	public String[] getAsStringArray(String key) {
		if (!this.jsonObject.has(key)){
			return null ;
		}
		JsonElement e =  jsonObject.get(key);
		if (( e== null || JsonNull.INSTANCE.equals(e))){
			return null;
		}
		JsonArray arr = jsonObject.getAsJsonArray(key) ; 
		if ( arr== null || arr.size()==0)
			return null ; 
		String[] retval = new String[arr.size()];
		for ( int i=0; i< arr.size();i++){
			JsonElement elem =  arr.get(i);
			if ( elem!= null)
				retval[i] = elem.getAsString() ; 
		}
		
		
		
		
		return retval;
	}

	@Override
	public void appendToArray(String key, String[] value) {
		if ( value==null || value.length==0){
			jsonObject.add(key, JsonNull.INSTANCE);
			return ; 
		}
		JsonArray arr = new JsonArray(); 
		for ( String scn  : value ) {
			
			arr.add(scn== null ? JsonNull.INSTANCE :   new JsonPrimitive(scn));
		}
		
		jsonObject.add(key, arr);
	}

	

	@Override
	public void appendToArray(String key, Long[] values) {
		if ( values==null || values.length==0){
			jsonObject.add(key, JsonNull.INSTANCE);
			return ; 
		}
		JsonArray arr = new JsonArray(); 
		for ( Long scn  : values ) {
			arr.add(scn== null ? JsonNull.INSTANCE :   new JsonPrimitive(scn));
		}
		
		jsonObject.add(key, arr);
	}
	
	
	

	

	@Override
	protected void putNull(String key) {
		this.jsonObject.add(key, JsonNull.INSTANCE);
	}

	@Override
	public int getArrayLength(String keyOfSuscpectedArrayObject) {
		if (! this.jsonObject.has(keyOfSuscpectedArrayObject))
			return 0 ;
		JsonElement elem =this.jsonObject.get(keyOfSuscpectedArrayObject);
		if (elem== null||!elem.isJsonArray())
			return 0 ; 
		return elem.getAsJsonArray().size();
	}
    
        
	@Override
	public boolean contain(String key) {
		return this.jsonObject.has(key);
	}
	
        
	
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	@Override
	public void injectJSONValue(String json) {
		JsonParser parser = new JsonParser();  
		JsonElement jsonElement = parser.parse(json);
		jsonObject =  jsonElement.getAsJsonObject(); 
		
	}
	
}
