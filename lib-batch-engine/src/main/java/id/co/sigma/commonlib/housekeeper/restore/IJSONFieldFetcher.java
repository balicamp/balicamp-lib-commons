package id.co.sigma.commonlib.housekeeper.restore;

import java.io.Serializable;
import java.sql.Types;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

/**
 * fetcher data dari json. pekerjaan nya : <ol>
 * <li>membaca data dari json</li>
 * <li>menaruh data di baca ke dalam Map target</li>
 * </ol>
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IJSONFieldFetcher extends Serializable {
	
	
	
	public static final String DATA_TYPE_SUFFIX ="_SQL_TYPE"; 
	
	/**
	 * mengambil data dari json dan menaruh nya di map
	 * @param object json object dari mana data di panggil
	 * @param jsonKey key data
	 * @param placeForPutResult map di mana data akan di taruh
	 * @param keyOnMap key dalam map. ini sesuai sesuai dengan nama column sumber. ini di pergunakan sebagai named parameter
	 **/
	public void fetchAndPutToMap ( JsonObject object , String jsonKey , Map<String, Object> placeForPutResult , String keyOnMap);
	
	/**
	 * tipe sql yang di terima. apa saja. ini menyesuaikan dengan {@link Types}
	 **/
	public int getAcceptedType () ;
	
	
	/**
	 * get actual object dan menaruh ke dalam map
	 **/
	public Object getCurrentObjectAndPutToMap(JsonReader reader ) ;  

}
