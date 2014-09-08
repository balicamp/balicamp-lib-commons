package id.co.sigma.common.server.service;

/**
 * bind bulk data additional props
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomBulkDataAdditionalPropertyBinderManager {

	
	/**
	 * bind prop additional ke dalam data
	 */
	public void registerBulkDataBinder ( ICustomBulkDataAdditionalPropertyBinder<?> binder) ; 
}
