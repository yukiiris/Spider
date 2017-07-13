package com.RESTSpider.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;

import com.RESTSpider.factory.DAOFactory;

import java.io.IOException;
import java.security.Key;

@Provider
@Priority(Priorities.AUTHENTICATION)//优先级最高
//实现该拦截器借口
//@Provider可以自动注册
public class JWTSecurityFilter implements ContainerRequestFilter{
	
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException 
	{
		Key key = null;
		String method = containerRequestContext.getMethod().toLowerCase();
		String path = ((ContainerRequest) containerRequestContext).getPath(true).toLowerCase();
		
		if (path.indexOf("/user") > -1 && method.equals("post"))
		{
			return;
		}
//		try
//		{
//			key = DAOFactory.getIKeyDAOInstance().getKey();
//		}
//		catch (Exception e)
//		{
//			e.printStackTrace();
//		}
	}
}
