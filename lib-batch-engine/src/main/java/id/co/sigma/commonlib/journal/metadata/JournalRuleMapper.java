package id.co.sigma.commonlib.journal.metadata;

import id.co.sigma.commonlib.journal.engine.data.SourceJournalRawData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;



/**
 * class untuk me-map dari rule ke GL, ini kalau ada proses pemetaan. kasus ini di pakai kalau GL yang di pergunakan tergantung pada hasil select lain nnya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class JournalRuleMapper {
	
	
	/**
	 * nama field dari mana proses mapper di ambil
	 **/
	private String mappedSourceField ; 
	
	
	
	/**
	 * proses mapper mempergunakan String. jadinya kalau data sumber bukan string( misal biginteger-> kode item, long etc) maka perlu di konversikan menjadi string
	 * object ini merupakan konverter dari object ini menjadi string
	 **/
	private ObjectToStringTranslator objectToStringTranslator ; 
	
	
	
	private HashMap<String, String> indexedMapperGL = new HashMap<String, String>(); 
	
	public JournalRuleMapper(String fieldOfMappingSource) {
		this.mappedSourceField = fieldOfMappingSource ; 
	}
	
	public String getGLCode(SourceJournalRawData rawData)  {
		Object obj =  rawData.get(mappedSourceField);
		if ( obj==null)
			return null ;
		String key =  objectToStringTranslator==null? obj.toString() : objectToStringTranslator.translator(obj) ;
			
		return indexedMapperGL.get(key); 
	} 
	
	
	/**
	 * proses mapper mempergunakan String. jadinya kalau data sumber bukan string( misal biginteger-> kode item, long etc) maka perlu di konversikan menjadi string
	 * object ini merupakan konverter dari object ini menjadi string
	 **/
	public ObjectToStringTranslator getObjectToStringTranslator() {
		return objectToStringTranslator;
	}
	/**
	 * proses mapper mempergunakan String. jadinya kalau data sumber bukan string( misal biginteger-> kode item, long etc) maka perlu di konversikan menjadi string
	 * object ini merupakan konverter dari object ini menjadi string
	 **/
	public void setObjectToStringTranslator(
			ObjectToStringTranslator objectToStringTranslator) {
		this.objectToStringTranslator = objectToStringTranslator;
	}
	
	/**
	 * memasukan data GL mapper ke dalam rule mapper
	 * @param mapValue nilai untuk di map
	 * @param GLCode kode gl hasil pemetaan
	 **/
	public void appendGLMapper(String mapValue, String GLCode ) {
		indexedMapperGL.put(mapValue, GLCode);
	}

	@Override
	public String toString() {
		return "JournalRuleMapper [mappedSourceField=" + mappedSourceField
				+ ", objectToStringTranslator=" + objectToStringTranslator
				+ ", indexedMapperGL=" + indexedMapperGL + "]";
	}
	
	
	
	

}
