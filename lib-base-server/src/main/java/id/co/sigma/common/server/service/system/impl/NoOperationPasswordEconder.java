package id.co.sigma.common.server.service.system.impl;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;



/**
 * no operation password encoder
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@Service(value="sigma-no-operation-pwd-encoder")
public class NoOperationPasswordEconder implements PasswordEncoder{

	@Override
	public String encodePassword(String rawPass, Object salt) {
		return rawPass;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		return true;
	}

}
