package com.RESTSpider.Jersey.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.RESTSpider.Jersey.Impl.RESTSpiderUserImpl;

public class JerseyResourceConfig extends ResourceConfig{

	public JerseyResourceConfig()
	{
		register(RESTSpiderUserImpl.class);
		register(LoggingFilter.class);
		packages("com.RESTSpider.Jersey.Impl");
		register(JacksonJsonProvider.class);
	}
}
