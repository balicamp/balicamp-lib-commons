package id.co.sigma.common.data;




/**
 * extractor key(versi string dari 1 pojo)
 **/
public interface SingleKeyVersionDataExtractor<DATA> {
	
	
	
	/**
	 * generate key versi string. <br/>
	 * <ol>
	 * 	<li>kalau key bukan string di stringkan(i.e integer di konversi jadi string angka)</li>
	 * <li>kalau key lebih dari 1 , maka bisa di concat(dengan fixed length atau dengan delimiter tertentu)</li>
	 * </ol>
	 * @param data data yang akan di extract key nya
	 **/
	public String generateSingleStringKey(DATA data);
	
	
	/**
	 * mengkonversi string key ke dalam array of object. object ini di pergunakan sbg key(i.e di sisi server)
	 * @param dataKey string yang akan di extract menjadi komponen key original data
	 * @return array of object. di saran kan isi array berupa primitiv(string, int, float, doble,date etc)
	 **/
	public Object[] getDataKeyFromString(String dataKey);
	
	
	

}
