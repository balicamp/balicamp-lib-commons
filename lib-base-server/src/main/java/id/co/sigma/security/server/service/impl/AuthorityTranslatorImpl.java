package id.co.sigma.security.server.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import id.co.sigma.common.server.data.security.CoreComponentAuthority;
import id.co.sigma.common.server.data.security.UserLoginNotificationData;
import id.co.sigma.common.server.service.system.AuthorityTranslator;

@Service
public class AuthorityTranslatorImpl implements AuthorityTranslator{

	@Override
	public Collection<CoreComponentAuthority> generateAuthorities(UserLoginNotificationData notificationResult) {
		return null;
	}
}