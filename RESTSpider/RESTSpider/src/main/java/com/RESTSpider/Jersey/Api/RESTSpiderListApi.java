package com.RESTSpider.Jersey.Api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.RESTSpider.vo.Anime;

public interface RESTSpiderListApi {

	@Path("/PendingList")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Anime> getPendingList();
	
	@Path("/PendingList")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Anime> getFollowingList();
}
