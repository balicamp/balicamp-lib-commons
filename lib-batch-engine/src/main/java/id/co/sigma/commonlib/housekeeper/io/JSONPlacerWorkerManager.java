package id.co.sigma.commonlib.housekeeper.io;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final  class JSONPlacerWorkerManager {
	
	
	private JSONPlacerWorkerManager(){
		
	}
	
	private static final Map<String, JSONPlacerWorker<?>> indexedData = new HashMap<String, JSONPlacerWorker<?>>() ; 
	
	static {
		indexedData.put(String.class.getName(), new StringJSONPlacerWorker());
		indexedData.put(Integer.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(int.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(Long.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(long.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(Double.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(double.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(Short.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(short.class.getName(), new NumberJSONPlacerWorker());
		
		indexedData.put(BigInteger.class.getName(), new NumberJSONPlacerWorker());
		indexedData.put(BigDecimal.class.getName(), new NumberJSONPlacerWorker());
		
		indexedData.put(Date.class.getName(), new DateJSONPlacerWorker());
		indexedData.put(java.sql.Date.class.getName(), new DateJSONPlacerWorker());
		indexedData.put(java.sql.Time.class.getName(), new DateJSONPlacerWorker());
		indexedData.put(java.sql.Timestamp.class.getName(), new DateJSONPlacerWorker());
	}
	
	
	
	private static JSONPlacerWorkerManager instance ; 
	
	
	public static JSONPlacerWorkerManager getInstance() {
		if(instance== null)
			instance = new JSONPlacerWorkerManager(); 
		return instance;
	}
	
	
	
	public JSONPlacerWorker<?> getJSONPlacer (String fqcn ) {
		return indexedData.get(fqcn) ; 
		
	}
	
	
	

}
