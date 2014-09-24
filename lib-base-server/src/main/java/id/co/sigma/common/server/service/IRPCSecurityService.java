package id.co.sigma.common.server.service;

import id.co.sigma.common.exception.InvalidTokenException;

/**
 * service untuk handle RPC security. lebih ke arah token
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface IRPCSecurityService extends IBaseService{
	
	
	
	/**
	 * check token. Dan expired di expired 
	 * @param token token yang hendak di cek
	 * @exception InvalidTokenException ini kalau token tidak di temukan 
	 */
	public void checkAndExpiredToken (String token ) throws InvalidTokenException;
	
	
	/**
	 * generate token. Token unik per session
	 */
	public String generateToken () ; 

}
