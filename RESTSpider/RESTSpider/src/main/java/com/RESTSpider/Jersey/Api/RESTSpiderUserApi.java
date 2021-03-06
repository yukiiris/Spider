package com.RESTSpider.Jersey.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.RESTSpider.vo.User;

@Path("/Spider")
public interface RESTSpiderUserApi {

	@Path("/new_user")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)  
	public boolean createUser(String json);
	
	@Path("/user")
	@POST
    @Consumes(MediaType.APPLICATION_JSON)  
    @Produces(MediaType.APPLICATION_JSON)  
	public String Login(User user);
	
//	@Path("/user")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public User showUser();
   
}
