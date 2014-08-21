package id.co.sigma.commonlib.util.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.co.sigma.commonlib.journal.engine.io.reader.ParameterKey;
import id.co.sigma.commonlib.util.IQueryParameterHolder;


/**
 * query parameter holder. ini wrapper parameter Query reader, incase datanya banyak
 **/
public class QueryParameterHolderImpl implements IQueryParameterHolder{
	
	
	
	
	private Map<String, Map<String, Object>> indexedCachedParameter = new HashMap<String, Map<String,Object>>(); 
	private Map<String, Map<String, Class<?>>> valueDataTypes = new HashMap<String, Map<String, Class<?>>>();
	
	
	/**
	 * flattened data. ini untuk membuang data yang overflow
	 **/
	private ArrayList<String> flattendedKey = new ArrayList<String>() ; 
	
	private static int LATEST_MARKER =1; 
	
	
	/**
	 * berapa max group akan di keep
	 **/
	private static int MAX_HOLDED_ELEMENT = 1000; 
	

	@Override
	public ParameterKey generateKey() {
		int swap = LATEST_MARKER ; 
		LATEST_MARKER++;
		String key =swap + "-" + (new Date()).getTime() + "-" + UUID.randomUUID() ;
		
		
		flattendedKey.add(0,  key   ); 
		if (flattendedKey.size()> MAX_HOLDED_ELEMENT ){
			String keyRemove =  flattendedKey.get(flattendedKey.size()-1);
			removeParameter(new ParameterKey(keyRemove));
			System.out.println("remove item dengan key:" + keyRemove +", di register key dengan key :" + key +", flattendedKey :" + flattendedKey.size() );
			
		}
		indexedCachedParameter.put(key, new HashMap<String, Object>());
		return new ParameterKey(key);
	}
	
	private void putDataType(ParameterKey groupKey, String paramKey, Class<?> clazz) {
		if(!valueDataTypes.containsKey(groupKey.getInternalKey())) {
			Map<String, Class<?>> dataTypes = new HashMap<String, Class<?>>();
			dataTypes.put(paramKey, clazz);
			valueDataTypes.put(groupKey.getInternalKey(), dataTypes);
		} else {
			Map<String, Class<?>> dataTypes = valueDataTypes.get(groupKey.getInternalKey());
			dataTypes.put(paramKey, clazz);
		}
	}
	
	@Override
	public void put(ParameterKey groupKey, String paramKey, Date data) {
		putWorker(groupKey, paramKey, data);
		putDataType(groupKey, paramKey, data.getClass());
	}
	
	
	
	@Override
	public void put(ParameterKey groupKey, String paramKey, Number data) {
		putWorker(groupKey, paramKey, data);
		putDataType(groupKey, paramKey, data.getClass());
	}
	
	@Override
	public void put(ParameterKey groupKey, String paramKey, String data) {
		putWorker(groupKey, paramKey, data);
		putDataType(groupKey, paramKey, data.getClass());
	}

	
	private void putWorker(ParameterKey key,String paramKey,  Object data) {
		String keyAsString = key.getInternalKey() ; 
		putWorker(keyAsString, paramKey, data);
	}
	
	private void putWorker(String keyAsString,String paramKey,  Object data) {
		if(!indexedCachedParameter.containsKey(keyAsString)){
			indexedCachedParameter.put(keyAsString, new HashMap<String, Object>());
		}
		indexedCachedParameter.get(keyAsString).put(paramKey, data);
	}

	@Override
	public void removeParameter(ParameterKey key) {
		indexedCachedParameter.remove(key.getInternalKey());
		flattendedKey.remove(key); 
	}

	@Override
	public Map<String, Object> get(String groupKey) {
		return indexedCachedParameter.get(groupKey);
	}

	@Override
	public Map<String, Class<?>> getParamTypeMap(String groupKey) {
		return valueDataTypes.get(groupKey);
	}

	@Override
	public void putRawObject(ParameterKey groupKey, String paramKey, Object data) {
		putWorker(groupKey, paramKey, data);
		if ( data!=null)
			putDataType(groupKey, paramKey, data.getClass());
	}

	@Override
	public void putWithRawStringKey(String groupKey, String paramKey,
			String data) {
		putWorker(groupKey, paramKey, data); 
		
	}

	

}
