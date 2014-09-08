package id.co.sigma.common.server.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class CustomJoinHqlProviderManagerImpl implements ICustomJoinHqlProviderManager{
	
	
	private Map<String, ICustomJoinHqlProvider<?>> indexedHandler = new HashMap<String, ICustomJoinHqlProvider<?>>() ; 

	@Override
	public void register(ICustomJoinHqlProvider<?> customProvider) {
		indexedHandler.put(customProvider.getHandledClass().getName()	, customProvider);
		
	}

	@Override
	public ICustomJoinHqlProvider<?> getCustomProvider(String fqcn) {
		return indexedHandler.get(fqcn);
	}
	@Override
	public boolean contains(String fqcn) {
		
		return indexedHandler.containsKey(fqcn);
	}

}
