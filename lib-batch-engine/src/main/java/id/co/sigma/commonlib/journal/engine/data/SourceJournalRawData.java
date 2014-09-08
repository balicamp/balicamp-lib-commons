package id.co.sigma.commonlib.journal.engine.data;

import java.io.Serializable;
import java.util.HashMap;



/**
 * wrapper data source. hasil pembacaan sumber, ini di pass ke processor
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class SourceJournalRawData implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 425126710407852803L;
	/**
	 *  Hash map, menyimpan data dengan key nama column
	 **/
	private HashMap<String, Object> rawDataIndexer = new HashMap<String, Object>(); 
	
	
	
	
	/**
	 * worker unuk menaruh data dalam indexer
	 **/
	public void put(String columnName , Object data ){
		rawDataIndexer.put(columnName, data); 
	
	}
	
	
	
	
	/**
	 * membaca data berdasarkan nama column. ini sudah di guard dari masalah perbedaan case
	 **/
	public Object get(String columnName ){
		if ( rawDataIndexer.containsKey(columnName))
			return rawDataIndexer.get(columnName);
		for ( String key : rawDataIndexer.keySet()){
			if ( key==null)
				continue ;
			if ( key.equalsIgnoreCase(columnName))
				return rawDataIndexer.get(key); 
		}
		return null ; 
	}
	

}
