package id.co.sigma.common.util.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




/**
 * wrapper untuk akses json yang sudah di parse dari string. ini bridge untuk handle dual, akses dari client vs dari server
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ParsedJSONContainer {
	
	
	/**
	 * ekor key FQCN . ini untuk menyimpan data type dari data dalam format array/list
	 **/
	public static final String JSON_ARRAY_DATA_TYPE_SUFFIX = "_detail_fqcn" ;
	
	
	
	/**
	 * inject json string ke dalam json container
	 */
	public void injectJSONValue (String json ) ; 
	
	
	
	/**
	 * mengecek apakah ada object dengan key yang di kirim
	 * @param key key dari object yang di cek keberadaan nya dari dalam json container
	 **/
	public boolean contain( String key ); 
	
	
	
	/**
	 * mengambil data as json array
	 **/
	public ParsedJSONArrayContainer getAsArray (String key) ; 
	
	/**
	 * membaca data as string 
	 **/
	public String[] getAsStringArray (String key); 
	
	
	/**
	 * membaca representasi data dalam bentuk array. jadiny object yang di parsing berupa array
	 */
	public ParsedJSONArrayContainer getAsArray () ; 
	
	
	/**
	 * mengambil object as array of date
	 **/
	public Date[] getAsArrayOfDates (String key) ;
	
	
	public BigDecimal[] getAsArrayOfBigDecimals (String key) ;
	
	
	
	
	/**
	 * membaca data as array of big integer
	 **/
	public BigInteger[] getAsArrayOfBigIntegers (String key) ;
	/**
	 * membaca data as array of Float
	 **/
	public Float[] getAsArrayOfFloats (String key) ;
	/**
	 * membaca data as array of Integer
	 **/
	public Integer[] getAsArrayOfIntegers (String key) ;
	/**
	 * membaca data as array of Short
	 **/
	public Short[] getAsArrayOfShorts (String key) ;
	
	
	
	
	/**
	 * membaca data as array of Long
	 **/
	public Long[] getAsArrayOfLongs (String key) ;
	
	
	/**
	 * menaruh raw data ke dalam sub elemen dari json. data berupa string
	 * kalau blm ada array, maka array akan di buat
	 **/
	public void appendToArray (String key , String value) ; 
	
	/**
	 * menaruh raw data ke dalam sub elemen dari json. data berupa boolean
	 * kalau blm ada array, maka array akan di buat
	 **/
	public void appendToArray (String key , Boolean value) ;
	
	
	/**
	 * menaruh raw data ke dalam sub elemen dari json. data berupa boolean. 
	 * kalau blm ada array, maka array akan di buat
	 **/
	public void appendToArray (String key , Double value) ;
	
	
	
	
	/**
	 * menaruh sub object ke dalam json
	 **/
	public void appendToArray (String key , IJSONFriendlyObject<?>[] value) ;
	
	
	
	
	
	/**
	 * menaruh array of date ke dalam array
	 **/
	public void appendToArray (String key , Date[] values) ;
	
	
	/**
	 * menaruh array of BigInteger ke dalam array
	 **/
	public void appendToArray (String key , BigInteger[] values) ;
	
	/**
	 * menaruh array of {@link BigDecimal} ke dalam array
	 **/
	public void appendToArray (String key , BigDecimal[] values) ;
	
	
	
	/**
	 * menaruh array of {@link Integer} ke dalam array
	 **/
	public void appendToArray (String key , Integer[] values) ;
	
	
	
	
	/**
	 * memasukan long ke dalam (array)
	 */
	public void appendToArray (String key , Long[] values) ;
	
	
	
	
	/**
	 * menambahkan ke dlaam array. 1 object, kalau blm ada sub object(array) akan di buatkan. kalau sudah ada, data di tambahkan pada posisi lates dalam array
	 * @param key key dari variable yang bertipe array
	 * @param value value yang hendak di append ke dalam array
	 **/
	public void appendToArray(String key , IJSONFriendlyObject<?> value) ; 
	
	
	
	/**
	 * membaca FQCN dari sub class. dalam proses penyimpanan data as array. FQCN akan di simpan dengan -> key +JSON_ARRAY_DATA_TYPE_SUFFIX 
	 **/
	public String getArrayTypeFQCN (String arrayKey ) ;
	
	
	
	
	
	
	/**
	 * menaruh array of string ke dalam object.kebaolkan dari ini, silakan cek {@link #getAsStringArray(String)}
	 * @param key key json 
	 * @param value array of stringyang di taruh ke dalam array
	 **/
	public void appendToArray (String key , String[] value) ;
	
        
        
        /**
         * get sub object. proses pembacaan di sini dengan dengan mengirimkan sample data. jadinya property di baca as json object dan di baca dalam dengan sample object
        */
        public <T extends  IJSONFriendlyObject<T>> T getAsSubJSONObject (String key , T sampleObjectForConverter ) ; 
	
        
        
        /**
         * baca dengan ambil meta data langsung
         **/
        public <T extends IJSONFriendlyObject<T>> T getAsSubJSONObject(String key ) ;
	/**
	 * membaca string dari parsed json object
	 **/
	public String getAsString(String key ); 
	/**
	 * ambil boolena value dari json yang sudah di parsing
	 **/
	public Boolean getAsBoolean(String key );
	/**
	 * membaca number dari raw json
	 **/
	public Double getAsNumber(String key );
        
        
        /**
         * membaca data as big decimal
        */
        public BigDecimal getAsBigDecimal ( String key);
        
        
        public BigInteger getAsBigInteger ( String key);
	
        /**
         * membaca data , langsung di konversi ke dalam Integer. null pointer safe item
        */
	public Integer getAsInteger (String key ) ;
	
	
	
	/**
	 * get tipe data dengan argument key + Full qualified class name
	 **/
	public <T> T get(String key , String expectedResultFQCN ) ; 
	
	
	
	
	
	/**
	 * membaca data dengan expected return array of object
	 **/
	public <T extends IJSONFriendlyObject<T>> T[] getAsArray (String key , Class<T> expectedObjectClass) ;
	
	
	/**
	 * membaca data dengan expected return array of object. versi ini di kirimi FQCN 
	 **/
	public  Object[] getAsArrayByFQCN (String key , String expectedObjectFQCN) ;
	
	/**
	 * get data as Date. ini no error kalau data sesuai dengan format
	 **/
	public Date getAsDate (String key ) ;
	
	/**
	 * menaruh String ke dalam json. nested path di taruh dengan notasi .[dot]
	 **/
	public void put(String key , String value );
	
	
	
	/**
	 * menaruh short variable
	 **/
	public void put(String key , Short value );
	
	/**
	 * menaruh Double ke dalam json. nested path di taruh dengan notasi .[dot]
	 **/
	public void put(String key , Double value );
	
	
	
	
	/**
	 * menaruh integer
	 **/
	public void put(String key , Integer value );
	
	
	
	
	/**
	 * menaruh Big integer
	 **/
	public void put(String key , BigInteger value );
	
	
	
	/**
	 * menaruh big decimal
	 **/
	public void put(String key , BigDecimal value );
	
	
	
	/**
	 * menaruh date
	 **/
	public void put(String key , Float value );
	
	
	/**
	 * menaruh Boolean ke dalam json. nested path di taruh dengan notasi .[dot]
	 **/
	public void put(String key , Boolean value );
	
	
	
	/**
	 * menaruh long ke dalam container
	 * @param key key dari long data. 
	 * @param longData data long
	 * 
	 **/
	public void put(String key, Long longData);
	
	/**
	 * menaruh date ke dalam container
	 **/
	public void put( String key , Date date);
	
	/**
	 * membaca JSON String dari current data
	 **/
	public String getJSONString(); 
	
	
	/**
	 * put array of data
	 * @param key key json data
	 * @param arrayOfData array of data yang di taruh dalam json container
	 **/
	public void put (String key , List<? extends IJSONFriendlyObject<?>> arrayOfData); 
	
        
	/**
	 * <ol>
	 * <li>cek tipe data. JSON enable atau tidak</li>
	 * <li>masukan semua ke dlaam array of json friendly object</li>
	 * <li>push ke dalam array</li>
	 * </ol>
	 **/
	public void putIfJsonEnableObjects (String key , List<?> arrayOfData);
        /**
         * menaruh sub object ke dalam object heirarcy
         */
        public <T extends  IJSONFriendlyObject<T>>void put (String key, T subObject ) ; 
        
        
	
	/**
	 * get as array of object. ini sementara hanya bisa bekerja untuk flat object
	 **/
	public <T extends IJSONFriendlyObject<T>> ArrayList<T> getAsArraylist(    String key , String objectTypeFQCN );  
	
	
	/**
	 * mencari panjang dari object array. array di cari dengan key dari object(sesuai dengan variable <i>keyOfSuscpectedArrayObject</i>)
	 * @param keyOfSuscpectedArrayObject key dari sub object yang tipe nya array
	 **/
	public abstract int getArrayLength (String keyOfSuscpectedArrayObject) ; 
	
	/**
	 * set JSON value null untuk key tertentu
	 **/
	public void setNull (String key ); 
 
}
