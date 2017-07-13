package com.RESTSpider.Jersey.Api;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.RESTSpider.vo.Anime;

@Path("/animes")
public interface RESTSpiderAnimeApi {
	
	@GET
	public List<Anime> searchAnime(@QueryParam("name")String name);
	
}
