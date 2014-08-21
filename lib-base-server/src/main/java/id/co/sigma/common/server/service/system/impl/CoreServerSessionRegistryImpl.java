package id.co.sigma.common.server.service.system.impl;

import id.co.sigma.common.server.service.system.ICoreServerSessionRegistry;

import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionRegistryImpl;




/**
 * session registry. to be define
 **/
public class CoreServerSessionRegistryImpl extends SessionRegistryImpl implements ICoreServerSessionRegistry{

	
	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		// TODO Auto-generated method stub
		super.onApplicationEvent(event);
	}
}
