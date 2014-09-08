package id.co.sigma.common.server.dao;

/**
 * 
 * interface spooler custom Hql provider
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomJoinHqlProviderManager {
	
	
	/**
	 * register custom handler ke dalam manager
	 **/
	public void register ( ICustomJoinHqlProvider<?> customProvider) ;
	
	
	/**
	 * custom provider. di cari dengan FQCN  
	 */
	public ICustomJoinHqlProvider<?> getCustomProvider ( String fqcn ) ;
	
	/**
	 * punya apa ndak
	 **/
	public boolean contains ( String fqcn ) ;

}
