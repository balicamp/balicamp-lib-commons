package id.co.sigma.commonlib.importengine;



/**
 * object yang bisa di reset. ini dalam kasus object yang memiliki cache object. kalau ada perubahan, data perlu di reset
 * @author <a href="gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public interface ResetableObject {
	
	
	/**
	 * reset data
	 **/
	public void reset () ; 

}
