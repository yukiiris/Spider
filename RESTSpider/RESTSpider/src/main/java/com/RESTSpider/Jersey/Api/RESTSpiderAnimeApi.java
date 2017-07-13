package com.RESTSpider.Jersey.Api;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.RESTSpider.vo.Anime;

@Path("/animes")
public interface RESTSpiderAnimeApi {
	
	@GET
	public List<Anime> searchAnime(@QueryParam("name")String name);
	
	@Path("/detail")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public List<String> Chapter(@FormParam("url")String url);
}
