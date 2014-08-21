package id.co.sigma.commonlib.importengine.definition.src;

import id.co.sigma.commonlib.importengine.data.StringKeyTranslationDataMap;
import id.co.sigma.commonlib.importengine.exception.DataTypeViolationException;

import java.util.HashMap;
import java.util.Map;



/**
 * base class untuk data dengan model data di translate dengan table lookup
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public  class TranslatedWithMapperUploadColumnDefinition<RESULT> extends BaseFileColumnDefinition<RESULT>{
	
	
	/**
	 * translator object
	 **/
	private Map<String, StringKeyTranslationDataMap<RESULT>> indexedTranslator = new HashMap<String, StringKeyTranslationDataMap<RESULT>>();

	
	
	/**
	 * true : kalau data string di luar map maka akan menjadi exception untuk field ini
	 **/
	private boolean doNotAllowOutOfMapData =true ; 
	
	
	
	/**
	 * key internalizationkalau data melanggar map
	 **/
	private String dataOutOfMapMessageI18NKey ; 
	/**
	 * translator object
	 **/
	public Map<String, StringKeyTranslationDataMap<RESULT>> getIndexedTranslator() {
		return indexedTranslator;
	}

	
	
	
	/**
	 * assign translator data ke dalam object
	 **/
	public void assignDataMap(StringKeyTranslationDataMap<RESULT>[] dataMappers){
		indexedTranslator.clear(); 
		if ( dataMappers==null||dataMappers.length==0)
			return ; 
		for ( StringKeyTranslationDataMap<RESULT> scn : dataMappers){
			indexedTranslator.put(scn.getSource(), scn);
		}
	}
	
	
	
	@Override
	protected RESULT translateStringToData(String uploadSrcData)
			throws DataTypeViolationException {
		if ( !indexedTranslator.containsKey(uploadSrcData.trim())){
			return null; 
		};
		return indexedTranslator.get(uploadSrcData).getResult();
	}



	/**
	 * true : kalau data string di luar map maka akan menjadi exception untuk field ini
	 **/
	public boolean isDoNotAllowOutOfMapData() {
		return doNotAllowOutOfMapData;
	}


	/**
	 * true : kalau data string di luar map maka akan menjadi exception untuk field ini
	 **/
	public void setDoNotAllowOutOfMapData(boolean doNotAllowOutOfMapData) {
		this.doNotAllowOutOfMapData = doNotAllowOutOfMapData;
	}



	/**
	 * key internalizationkalau data melanggar map
	 **/
	public String getDataOutOfMapMessageI18NKey() {
		return dataOutOfMapMessageI18NKey;
	}



	/**
	 * key internalizationkalau data melanggar map
	 **/
	public void setDataOutOfMapMessageI18NKey(String dataOutOfMapMessageI18NKey) {
		this.dataOutOfMapMessageI18NKey = dataOutOfMapMessageI18NKey;
	}
	

}
