package id.co.sigma.commonlib.housekeeper.restore;

import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.stream.JsonReader;

/**
 * parameter untuk meluncurkan job restore
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RestoreParameterDetail implements Serializable {
	
	
	
	
	
	
	
	 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8705951926531012270L;
	/**
	 * insert into statemnet yang di pakai untuk insert into ke dalam database
	 **/
	private String insertIntoStatement ; 
	/**
	 * nama file yang perlu di load untuk proses restore
	 **/
	private String filePathToLoad;
	
	
	 
	
	
	
	/**
	 * tipe data 
	 **/
	private Map<String, Integer> dataTypeFields; 
	
	
	/**
	 * map key json vs key nama field db 
	 **/
	private Map<String, String> shortVersusActualFields = new HashMap<String, String>() ; 
	
	
	
	
	/**
	 * json field fetcher. sesuai dengan urutan column yang ada dalam json data
	 **/
	private ArrayList<IJSONFieldFetcher> fieldFetchers = new ArrayList<IJSONFieldFetcher>(); 
	
	
	
	
	public RestoreParameterDetail( String pathToLoad ) throws Exception{
		JsonReader r = new JsonReader(new FileReader(pathToLoad)); 
		this.filePathToLoad = pathToLoad  ; 
		try{
			r.beginObject(); 
			r.nextName() ; 
			this.insertIntoStatement = r.nextString(); 
			r.nextName();  
			r.beginArray();
			ArrayList<Integer> dataTypeSwaps = new ArrayList<Integer>() ;
			while (r.hasNext()){
				int dtType = r.nextInt();
				this.fieldFetchers.add(JSONFieldFetcherManager.getInstance().getFetcher(dtType));
				dataTypeSwaps.add(dtType); 
			}
			r.endArray(); 
			r.nextName(); 
			//fieldMapper
			r.beginArray();
			dataTypeFields = new HashMap<String, Integer>(); 
			int i  = 0 ; 
			while (r.hasNext()){
				r.beginObject();
				String key = r.nextName() ; 
				String actualField = r.nextString(); 
				shortVersusActualFields.put(key, actualField); 
				dataTypeFields.put(actualField, dataTypeSwaps.get(i)); 
				i++; 
				r.endObject();
			}
			r.endArray();
			r.nextName(); 
			
		}finally {
			r.close(); 
		}
	}
	
	/**
	 * nama file yang perlu di load untuk proses restore
	 **/
	public String getFilePathToLoad() {
		return filePathToLoad;
	}
	/**
	 * nama file yang perlu di load untuk proses restore
	 **/
	public void setFilePathToLoad(String filePathToLoad) {
		this.filePathToLoad = filePathToLoad;
	}
	 
	/**
	 * insert into statemnet yang di pakai untuk insert into ke dalam database
	 **/
	public void setInsertIntoStatement(String insertIntoStatement) {
		this.insertIntoStatement = insertIntoStatement;
	}
	/**
	 * insert into statemnet yang di pakai untuk insert into ke dalam database
	 **/
	public String getInsertIntoStatement() {
		return insertIntoStatement;
	}
	
	 
	 
	
	
	
	 
	
	
	
	/**
	 * tipe data 
	 **/
	public Map<String, Integer> getDataTypeFields() {
		return dataTypeFields;
	}
	
	
	/**
	 * tipe data 
	 **/
	public void setDataTypeFields(Map<String, Integer> dataTypeFields) {
		this.dataTypeFields = dataTypeFields;
	}

	@Override
	public String toString() {
		return "RestoreParameterDetail [insertIntoStatement="
				+ insertIntoStatement + ", filePathToLoad=" + filePathToLoad
				+ ", dataTypeFields=" + dataTypeFields
				+ ", shortVersusActualFields=" + shortVersusActualFields + "]";
	}
	
	
	
	/**
	 * berapa ukuran column yang perlu di baca
	 **/
	public int getColumnSize () {
		return this.dataTypeFields.size(); 
	}
	
	/**
	 * json field fetcher. sesuai dengan urutan column yang ada dalam json data
	 **/
	public ArrayList<IJSONFieldFetcher> getFieldFetchers() {
		return fieldFetchers;
	}

	/**
	 * map key json vs key nama field db 
	 **/
	public Map<String, String> getShortVersusActualFields() {
		return shortVersusActualFields;
	}
}
