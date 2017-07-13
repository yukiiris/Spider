package com.RESTSpider.Jersey.Impl;

import java.util.List;

import com.RESTSpider.Jersey.Api.RESTSpiderAnimeApi;
import com.RESTSpider.factory.DAOFactory;
import com.RESTSpider.vo.Anime;

public class RESTSpiderAnimeImpl implements RESTSpiderAnimeApi{

	public List<Anime> searchAnime(String name)
	{
		List<Anime> animes = null;
		try
		{
			animes = DAOFactory.getIAnimeDAOInstance().searchAnime(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return animes;
	}
	
}
