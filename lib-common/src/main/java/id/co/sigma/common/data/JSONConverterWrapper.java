package id.co.sigma.common.data;



/**
 * wrapper untuk konversi ke 
 **/
public interface JSONConverterWrapper<T> {
	
	public void putObject (String key , String value) ; 
	public void putObject (String key , Number value) ;
	public void putObject (String key , Boolean value) ;
	public T instantiateFromJSON(T sampleObject , String jsonString);

}
