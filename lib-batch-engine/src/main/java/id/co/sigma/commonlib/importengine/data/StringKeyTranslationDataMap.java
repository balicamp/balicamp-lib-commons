package id.co.sigma.commonlib.importengine.data;




/**
 * ini data untuk transalasi kan data dari string ke result. ini untuk di masukan ke dalam map
 * pergunakan method ini kalau populasi map kecil
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class StringKeyTranslationDataMap<RESULT> {

	
	/**
	 * hasil translasi
	 **/
	private RESULT result ;
	
	/**
	 * data source. ini apa yang akan di translasikan
	 **/
	private String source ;
	
	
	public StringKeyTranslationDataMap(){}
	

	public StringKeyTranslationDataMap(RESULT result, String source) {
		this.result = result;
		this.source = source;
	}


	/**
	 * hasil translasi
	 **/
	public RESULT getResult() {
		return result;
	}

	/**
	 * hasil translasi
	 **/
	public void setResult(RESULT result) {
		this.result = result;
	}
	/**
	 * data source. ini apa yang akan di translasikan
	 **/
	public String getSource() {
		return source;
	}
	/**
	 * data source. ini apa yang akan di translasikan
	 **/
	public void setSource(String source) {
		this.source = source;
	}


	@Override
	public String toString() {
		return "StringKeyTranslationDataMap [result=" + result + ", source="
				+ source + "]";
	} 
	
	
	
}
