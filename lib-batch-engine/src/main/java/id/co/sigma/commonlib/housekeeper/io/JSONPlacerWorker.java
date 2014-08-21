package id.co.sigma.commonlib.housekeeper.io;

import com.google.gson.JsonObject;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface JSONPlacerWorker<DATA> {
	
	
	/**
	 * 
	 **/
	public void put( JsonObject targetData,  String key , DATA object);
	
	
	
	/**
	 * get raw data from json
	 **/
	public DATA getFromJSON ( JsonObject targetData,  String key ) ; 
	
	
	/**
	 * serialization type code
	 **/
	public String getSerializationTypeCode () ;

}
