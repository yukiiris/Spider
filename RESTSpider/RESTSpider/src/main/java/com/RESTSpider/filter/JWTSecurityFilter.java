package com.RESTSpider.filter;

import java.io.IOException;
import java.io.InputStream;

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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;

import com.RESTSpider.Token.Token;
import com.RESTSpider.factory.DAOFactory;
import com.RESTSpider.spider.Spider;

import io.jsonwebtoken.Jwts;

import java.io.IOException;
import java.security.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Provider
@Priority(Priorities.AUTHENTICATION)//优先级最高
//实现该拦截器借口
//@Provider可以自动注册
public class JWTSecurityFilter implements ContainerRequestFilter{
 
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException 
	{
		String path = ((ContainerRequest) containerRequestContext).getPath(true);
		
		if (path.indexOf("/List") > -1)
		{
			MultivaluedMap<String, String> namecp = containerRequestContext.getUriInfo().getQueryParameters();
			String jsw = ((ContainerRequest) containerRequestContext).getHeaderString("x-auth-token");
			String name = namecp.getFirst("name");
			try
			{
				if (Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey(name))).parseClaimsJws(jsw).getBody().getSubject().equals(name))
				{
					return;
				}
				else
				{
					containerRequestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build());
				}
			}
			catch (Exception e)
			{
				containerRequestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build());
				e.printStackTrace();
			}
		}
	}
}
