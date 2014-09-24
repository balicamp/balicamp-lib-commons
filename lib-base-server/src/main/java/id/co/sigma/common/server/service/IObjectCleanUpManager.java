package id.co.sigma.common.server.service;

import id.co.sigma.common.server.util.IObjectCleanUp;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IObjectCleanUpManager {
	
	
	
	
	/**
	 * register object cleaner
	 */
	public void registerObjectCleaner ( IObjectCleanUp<?> objectCleaner ); 
	
	
	
	/**
	 * mencari object cleaner 
	 */
	public IObjectCleanUp<?> getObjectCleaner ( String fqcn );  

}
