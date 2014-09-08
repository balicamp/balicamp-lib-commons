package id.co.sigma.common.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.context.request.RequestContextHolder;

import id.co.sigma.common.exception.InvalidTokenException;
import id.co.sigma.common.security.MD5Utils;
import id.co.sigma.common.server.service.AbstractService;
import id.co.sigma.common.server.service.IRPCSecurityService;

/**
 * service untuk handle RPC security token
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class RPCSecurityServiceImpl extends AbstractService implements IRPCSecurityService{

	
	//FIXME: replace dengan ehcache
	private Map<String, List<String>> indexedToken = new HashMap<String, List<String>>()  ;
	
	
	@Override
	public void checkAndExpiredToken(String token) throws InvalidTokenException {
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
		if (! indexedToken.containsKey(sessionId) || !indexedToken.get(sessionId).contains(token)){
			InvalidTokenException tcx =  new InvalidTokenException();
			throw tcx ; 
		}
		indexedToken.get(sessionId).remove(token) ;
		
	}

	@Override
	public String generateToken() {
		String tkn = (new Date()).getTime() + UUID.randomUUID().toString(); 
		tkn =  MD5Utils.getInstance().hashMD5(tkn);
		String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId(); 
		if (! indexedToken.containsKey(sessionId))
			indexedToken.put(sessionId, new ArrayList<String>()); 
		indexedToken.get(sessionId).add(tkn); 
		return tkn;
	}

	
	
	
	

}
