package id.co.sigma.commonlib.importengine.data;



/**
 * interface json friendly object
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 * @param <T> data sumber untuk di konversikan ke json
 **/
public interface IJSONFriendlyObject<T> {
	
	
	
	/**
	 * generate json string dari object
	 * @param srcObject data source object untuk di konversikan ke 
	 **/
	public String generateJSON(T srcObject) ; 
	
	
	/**
	 * generate data dari json string
	 **/
	public T generateFromJSONString(String json);

}
