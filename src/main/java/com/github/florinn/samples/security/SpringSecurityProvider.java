package com.github.florinn.samples.security;

import javax.servlet.Filter;

import org.springframework.context.ApplicationContext;
import org.springframework.security.web.SecurityFilterChain;

import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.config.Environment;

public class SpringSecurityProvider implements InjectableProvider<Auth, Parameter> {

	ApplicationContext context;

	public SpringSecurityProvider(ApplicationContext context) {
		this.context = context;
	}

	public void registerProvider(Environment environment) {

		registerSpringSecurityFilters(environment);

		environment.addProvider(this);
	}

	protected void registerSpringSecurityFilters(Environment environment) {

		SecurityFilterChain filterChain = context.getBean(SecurityFilterChain.class);

		for (Filter filter : filterChain.getFilters()) {

			environment.addFilter(filter, "/*");
		}
	}

	@Override
	public Injectable<Principal> getInjectable(ComponentContext componentContext, Auth auth, Parameter parameter) {

		return new SpringSecurityInjectable(auth.required(), context);
	}

	@Override
	public ComponentScope getScope() {

		return ComponentScope.PerRequest;
	}

}