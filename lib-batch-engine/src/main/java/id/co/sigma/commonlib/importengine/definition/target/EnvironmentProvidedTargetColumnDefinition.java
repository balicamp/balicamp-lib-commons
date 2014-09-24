package id.co.sigma.commonlib.importengine.definition.target;

public class EnvironmentProvidedTargetColumnDefinition extends BaseTargetColumnDefinition{
	
	
	
	/**
	 * ini prefix yang akan di tambahkan ke dalam parameter
	 **/
	public static final String PREFIX_FOR_NAMED_PARAM ="SYSTEM_PROVIDED_"; 
	
	
	private String environmentLocatorKey ; 
	

	public EnvironmentProvidedTargetColumnDefinition(String targetColumnName, String environmentLocatorKey) {
		super(targetColumnName);
		this.environmentLocatorKey  = environmentLocatorKey ; 
	}

	@Override
	public String generateValueStatment() {
		return ":" + PREFIX_FOR_NAMED_PARAM +environmentLocatorKey ;
	}


}
