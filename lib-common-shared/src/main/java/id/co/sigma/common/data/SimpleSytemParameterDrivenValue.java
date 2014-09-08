package id.co.sigma.common.data;

/**
 * generic untuk enum yang mendescribe key+ value yang berasal dari  {@link id.co.sigma.common.data.app.SystemSimpleParameter}
 * key , desc dan flag encrypted
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface SimpleSytemParameterDrivenValue<D extends SimpleSytemParameterDrivenValue<D>> {
	
	
	/**
	 * key dari paremater
	 */
	public String getParameterKey () ;
	
	/**
	 * deskripsi dari parameter
	 */
	public String getParameterDesc () ;
	
	
	/**
	 * flag data boleh di edit atau tidak
	 */
	public boolean isEditable() ; 
	
	
	/**
	 * flag item ini di encrypt atau tidak
	 */
	public boolean isEncrypted () ; 
	
	
	/**
	 * instantiate from string
	 */
	public    D  instantiateFromString ( String key ); 

}
