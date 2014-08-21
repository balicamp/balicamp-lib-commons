package id.co.sigma.common.server;

/**
 * service yang bisa di reload. ini yang mengcahe data
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IReloadableService {
	
	
	
	/**
	 * reload cache yang kesimpan
	 */
	public void reloadCachedResource () ; 

}
