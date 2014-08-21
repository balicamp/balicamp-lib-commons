package id.co.sigma.common.server.service;

/**
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public interface ICustomTargetDataLoaderContainer {
	
	
	/**
	 * register custom loader ke dalam container
	 **/
	public void register (ICustomTargetDataLoader<?> register ) ; 

}
