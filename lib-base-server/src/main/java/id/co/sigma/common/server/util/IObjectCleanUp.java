package id.co.sigma.common.server.util;

/**
 * method ini di pergunakan untuk clean up data. misal dalam kasus hibernate. object yang tidak di perlukan di nullkan, atau use case lain nya
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IObjectCleanUp<POJO > {
	
	
	/**
	 * cleaner data
	 **/
	public void cleanUp ( POJO pojo); 
	
	
	/**
	 * class yang di handle
	 */
	public Class<POJO> getHandledClass () ; 

}
