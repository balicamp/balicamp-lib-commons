package id.co.sigma.common.server.service.system;

import java.util.Collection;

import id.co.sigma.common.server.data.security.CoreComponentAuthority;
import id.co.sigma.common.server.data.security.UserLoginNotificationData;



/**
 * translator dari array of user group ke SigmaAuthority. ini agar applikasi comply dengan spring security. karena spring security memblok user dengan authority dari user,
 * jadinya untuk user musti di provide authority
 **/
public interface AuthorityTranslator {
	
	public Collection<CoreComponentAuthority> generateAuthorities (UserLoginNotificationData notificationResult) ; 

}
