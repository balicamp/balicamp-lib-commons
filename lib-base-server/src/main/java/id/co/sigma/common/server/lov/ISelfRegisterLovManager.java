package id.co.sigma.common.server.lov;


/**
 * manager yang bisa self register LOV providers
 **/
public interface ISelfRegisterLovManager {

	/**
	 * register group of lov provider
	 **/
	public void register(CustomLOVProviderGroup lovProviderGroup);
	
	/**
	 * register lov provider tunggal
	 **/
	public void register(BaseLOVProvider lovProvider);
	
	
	/**
	 * get by id
	 **/
	public BaseLOVProvider get(String id );
	
	
	/**
	 * register lov provider tunggal
	 **/
	public void register(Base2ndLevelLOVProvider lovProvider);
	
	/**
	 * provider LOV 2nd Level
	 **/
	public Base2ndLevelLOVProvider get2ndLevelProvider(String id); 
}
