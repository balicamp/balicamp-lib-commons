package id.co.sigma.common.data.serializer;

/**
 * marker object untuk ngeflag, kalau object ini bukan object actual
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface IJSONWrapperObject<T> {
	
	
	
	/**
	 * actual data
	 **/
	public T getActualData () ; 

}
