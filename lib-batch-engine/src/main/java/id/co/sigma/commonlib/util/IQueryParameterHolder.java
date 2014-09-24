package id.co.sigma.commonlib.util;

import id.co.sigma.commonlib.journal.engine.io.reader.ParameterKey;

import java.util.Date;
import java.util.Map;



/**
 * holder Query Parameter. karena proses adalah async, makan parameter akan di taruh dalam bean
 **/
public interface IQueryParameterHolder {
	
	
	
	
	
	/**
	 * key untuk menaruh output path dari batch proses
	 **/
	public static final String OUTPUT_FILE_KEY ="batch_output_file_path_key";
	
	
	
	/**
	 * generate key baru untuk menaruh data
	 **/
	public ParameterKey generateKey () ; 
	
	
	/**
	 * menaruh string data ke dalam parameter holder
	 **/
	public void put(ParameterKey groupKey,String paramKey ,  String data); 
	
	
	/**
	 * ini key group dengan String
	 **/
	public void putWithRawStringKey(String groupKey,String paramKey ,  String data);
	
	
	
	
	/**
	 * versi ini menaruh number ke dalam parameter holder
	 **/
	public void put(ParameterKey groupKey,String paramKey ,  Number data);
	
	
	/**
	 * menaruh Date ke dalam parameter Holder
	 **/
	public void put(ParameterKey groupKey, String paramKey , Date data);
	
	
	
	/**
	 * menaruh raw object ke dalam container
	 **/
	public void putRawObject(ParameterKey groupKey, String paramKey , Object data);
	
	
	/**
	 * hapus data dari parameter  holder
	 **/
	public void removeParameter(ParameterKey groupKey) ; 
	
	
	/**
	 * get data parameter dari dalam container
	 **/
	public Map<String, Object> get(String groupKey); 
	
	
	public Map<String, Class<?>> getParamTypeMap(String groupKey);

}
