package com.RESTSpider.Jersey.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.RESTSpider.Jersey.Impl.RESTSpiderAnimeImpl;
import com.RESTSpider.Jersey.Impl.RESTSpiderUserImpl;
import com.RESTSpider.filter.JWTSecurityFilter;

public class JerseyResourceConfig extends ResourceConfig{

	public JerseyResourceConfig()
	{
		register(RESTSpiderUserImpl.class);
		register(LoggingFilter.class);
		packages("com.RESTSpider.Jersey.Impl");
		register(RESTSpiderAnimeImpl.class);
		register(JacksonJsonProvider.class);
		//register(JWTSecurityFilter.class, 1);
	}
}
