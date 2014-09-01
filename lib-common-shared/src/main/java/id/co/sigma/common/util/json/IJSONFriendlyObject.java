package id.co.sigma.common.util.json;

import java.io.Serializable;



/**
 * Object yang bisa di konversi ke dalam json dan sebaliknya
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/

public interface IJSONFriendlyObject<T> extends Serializable {
	
	
	
	
	/**
	 * string marker dalam JSON untuk menaruh pengenal dari data. di isikan dalam model array. karena array susunan lebih kompleks
	 **/
	public static final String IS_ARRAY_MARKER_PROPERTY = "arrayObject"; 
	
	
	/**
	 * marker java.util.List
	 **/
	public static final String IS_ARRAYLIST_MARKER_PROPERTY = "arrayListObject"; 
	
	/**
	 * FQCN dari object
	 **/
	public static final String FQCN_MARKER_KEY = "fqcn";
	
	
	
	/**
	 * key json untuk data actual
	 **/
	public static final String ACTUAL_ARRAY_DATA_KEY = "actualData";
	
	/**
	 * konversi Object  menjadi JSON String. property dari object perlu di taruh ke dalam json container. ini menyesuaikan. versi client dengan JSON engine JS , versi server dengan library server
	 * @param jsonContainer json di mana data perlu di taruh
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 **/
	public void translateToJSON(ParsedJSONContainer jsonContainer) ;
	
	
	/**
	 * instantiate instance object dengan mempergunakan json reference.
	 * what to do : <br/>
	 * <ol>
	 * <li>create new object </li>
	 * <li>set semua variable dengan data dari json</li>
	 * </ol>
	 **/
	public T instantiateFromJSON (ParsedJSONContainer jsonContainer) ; 

}
