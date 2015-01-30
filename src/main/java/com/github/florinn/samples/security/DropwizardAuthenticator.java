package com.github.florinn.samples.security;

import static com.google.common.base.Preconditions.*;
import com.google.common.base.Optional;

import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

public class DropwizardAuthenticator implements Authenticator<SpringSecurityCredentials, Principal> {

	@Override
	public Optional<Principal> authenticate(SpringSecurityCredentials credentials) 
			throws AuthenticationException {

		checkNotNull(credentials);

		Principal principal = getPrincipal(credentials);

		return Optional.of(principal);
	}

	private Principal getPrincipal(SpringSecurityCredentials credentials)
			throws AuthenticationException {
		Principal principal = null;

		// TODO: you should replace the hardwired values below by your own authentication logic (maybe a db query or a service call)

		if(credentials.getUsername().equals("user@domain.com") &&
				credentials.getPassword().equals("supersecret"))
			principal = new Principal(credentials.getUsername());
		else
			throw new AuthenticationException(
					new AuthException(Constants.BAD_CREDENTIALS));

		return principal;
	}

}

