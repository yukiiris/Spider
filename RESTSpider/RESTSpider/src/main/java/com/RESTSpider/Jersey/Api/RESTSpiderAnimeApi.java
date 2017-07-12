package com.RESTSpider.Jersey.Api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.RESTSpider.vo.Anime;

public interface RESTSpiderAnimeApi {

	@Path("/animes")
	@GET
	@Produces
	public List<Anime> searchAnime();
	
}
