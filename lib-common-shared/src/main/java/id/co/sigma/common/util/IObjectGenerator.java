package id.co.sigma.common.util;

/**
 * interface object generator. di sisi server cukup sederhana dengan berbekal reflection. di sisi client, ini bisa membuat sakit kepala. karena tidaka da reflection. solusi nya sebagai jembatan, maka object generator di bridge ke dalam interface
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IObjectGenerator {
	
	
	/**
	 * @param objectFQCN full qualified class name dari object yang perlu di instantiate
	 **/
	public <T> T instantiateSampleObject (String objectFQCN) ; 
	
	
	
	/**
	 * instantiate array of object
	 **/
	public <T> T[] instantiateArray (String objectFQCN, int size) ;
	
	
	
	

}

