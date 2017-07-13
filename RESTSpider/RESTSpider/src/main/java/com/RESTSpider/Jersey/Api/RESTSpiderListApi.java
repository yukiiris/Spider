package com.RESTSpider.Jersey.Api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.RESTSpider.vo.Anime;

@Path("/List")
public interface RESTSpiderListApi {

	@Path("/PendingList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Anime> getPendingList(String name);
	
	@Path("/Following")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Anime> getFollowingList(@QueryParam("name")String name);
	
	@Path("/Following")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createFollowing(Anime anime, @QueryParam("name")String name);
	
	@Path("/Pending")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean createPending(Anime anime, @QueryParam("name")String name);

}
