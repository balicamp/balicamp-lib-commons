package id.co.sigma.common.server.lov.impl;

import java.util.ArrayList;
import java.util.HashMap;


import id.co.sigma.common.server.lov.Base2ndLevelLOVProvider;
import id.co.sigma.common.server.lov.BaseLOVProvider;
import id.co.sigma.common.server.lov.CustomLOVProviderGroup;
import id.co.sigma.common.server.lov.ISelfRegisterLovManager;



public class SelfRegisterLovManagerImpl implements ISelfRegisterLovManager{
	
	
	
	
	private HashMap<String, BaseLOVProvider> customLOVProviders = new HashMap<String, BaseLOVProvider>();
	
	
	private HashMap<String, Base2ndLevelLOVProvider> customLOVProviders2ndLevel = new HashMap<String, Base2ndLevelLOVProvider>();
	
	@Override
	public void register(CustomLOVProviderGroup lovProviderGroup) {
		if ( lovProviderGroup==null)
			return ; 
		ArrayList<BaseLOVProvider>providers =  lovProviderGroup.getAllAvailableProvider(); 
		if (providers==null||providers.isEmpty())
			return ; 
		for ( BaseLOVProvider scn : providers){
			this.customLOVProviders.put(scn.getId(), scn); 
		}
	}



	@Override
	public void register(BaseLOVProvider lovProvider) {
		this.customLOVProviders.put( lovProvider.getId(), lovProvider);
	}



	@Override
	public BaseLOVProvider get(String id) {
		return this.customLOVProviders.get(id);
	}



	@Override
	public void register(Base2ndLevelLOVProvider lovProvider) {
		customLOVProviders2ndLevel.put(lovProvider.getId(), lovProvider);
		
	}



	@Override
	public Base2ndLevelLOVProvider get2ndLevelProvider(String id) {
		return customLOVProviders2ndLevel.get(id);
	}
	
	

	
	 
}
