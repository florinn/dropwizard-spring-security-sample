package com.github.florinn.samples;

import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.florinn.samples.config.MyServiceConfiguration;
import com.github.florinn.samples.security.DropwizardAuthenticator;
import com.github.florinn.samples.security.Principal;
import com.github.florinn.samples.security.SpringSecurityCredentials;
import com.github.florinn.samples.security.SpringSecurityProvider;
import com.github.nhuray.dropwizard.spring.SpringBundle;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.util.concurrent.UncaughtExceptionHandlers;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.auth.CachingAuthenticator;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class MyService extends Service<MyServiceConfiguration> {

	private static AnnotationConfigApplicationContext context;

	static {
		context = new AnnotationConfigApplicationContext();
		context.registerShutdownHook();
		context.scan("com.github.florinn.samples.config");
	}

	private static SingletonBeanRegistry beanRegistry = context.getBeanFactory();
	
	public static void main(String[] args) throws Exception {
		Thread.currentThread().setUncaughtExceptionHandler(UncaughtExceptionHandlers.systemExit());
		new MyService().run(args);
	}

	@Override
	public void initialize(Bootstrap<MyServiceConfiguration> bootstrap) {
		bootstrap.setName("MyService");
        bootstrap.addBundle(new SpringBundle<MyServiceConfiguration>(context, true, true));
	}

	@Override
	public void run(MyServiceConfiguration configuration, Environment environment)
			throws Exception {

		DropwizardAuthenticator authenticator = 
    			context.getBean(DropwizardAuthenticator.class);
    	CachingAuthenticator<SpringSecurityCredentials, Principal> cachingAuthenticator = 
    			CachingAuthenticator.wrap(authenticator, 
    					CacheBuilderSpec.parse(configuration.getAuthenticationCachePolicy()));
    	beanRegistry.registerSingleton(
    			DropwizardAuthenticator.class.getName(), cachingAuthenticator);
		 
		new SpringSecurityProvider(context).registerProvider(environment);
	}

}
