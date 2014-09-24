package id.co.sigma.common.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import id.co.sigma.common.server.service.IObjectCleanUpManager;
import id.co.sigma.common.server.util.IObjectCleanUp;

/**
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ObjectCleanUpManagerImpl implements IObjectCleanUpManager{
	
	
	private Map<String, IObjectCleanUp<?> > cleanerMap = new HashMap<String, IObjectCleanUp<?>>() ; 

	@Override
	public void registerObjectCleaner(IObjectCleanUp<?> objectCleaner) {
		cleanerMap.put(objectCleaner.getHandledClass().getName(), objectCleaner); 
		
	}

	@Override
	public IObjectCleanUp<?> getObjectCleaner(String fqcn) {
		return cleanerMap.get(fqcn);
	}
	

}
