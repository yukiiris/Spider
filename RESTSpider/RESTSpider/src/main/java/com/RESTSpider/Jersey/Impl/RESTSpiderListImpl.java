package com.RESTSpider.Jersey.Impl;

import java.util.ArrayList;
import java.util.List;

import com.RESTSpider.Jersey.Api.RESTSpiderListApi;
import com.RESTSpider.factory.DAOFactory;
import com.RESTSpider.spider.Spider;
import com.RESTSpider.vo.Anime;

public class RESTSpiderListImpl implements RESTSpiderListApi{

	public List<Anime> getPendingList(String name)
	{
		List<Anime> animes = new ArrayList<>();
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);
			animes = DAOFactory.getIAnimeDAOInstance().getAll(ID, 0);
		}
		catch  (Exception e)
		{
			e.printStackTrace();
		}
		return animes;
	}
	
	public List<Anime> getFollowingList(String name)
	{
		List<Anime> animes = new ArrayList<>();
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);
			animes = DAOFactory.getIAnimeDAOInstance().getAll(ID, 1);
		}
		catch  (Exception e)
		{
			e.printStackTrace();
		}
		return animes;
	}

	public boolean deleteFollowing(Anime anime,String name)
	{
		boolean isDelete = false;
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);
			isDelete = DAOFactory.getIAnimeDAOInstance().deleteAnime(ID, anime.getName(), 1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isDelete;
	}
	
	public boolean deletePending(Anime anime,String name)
	{
		boolean isDelete = false;
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);
			isDelete = DAOFactory.getIAnimeDAOInstance().deleteAnime(ID, anime.getName(), 0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isDelete;
	}
	
	public boolean createFollowing(Anime anime, String name)
	{
		boolean isCreate = false;
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);

			if (DAOFactory.getIAnimeDAOInstance().findAnime(anime.getName(), ID))
			{
				//TODO
			}
			else if (!Spider.getAnimeName(Spider.getContent(anime.getLink())).equals(anime.getName()))
			{
				
			}
			else
			{
				isCreate = DAOFactory.getIAnimeDAOInstance().doCreate(anime.getName(), ID, anime.getLink(),1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;
	}
	
	public boolean createPending(Anime anime, String name)
	{
		boolean isCreate = false;
		try
		{
			int ID = DAOFactory.getIUserDAOInstance().findID(name);
			if (DAOFactory.getIAnimeDAOInstance().findAnime(anime.getName(), ID))
			{
				//TODO
			}
			else if (!Spider.getAnimeName(Spider.getContent(anime.getLink())).equals(anime.getName()))
			{
				
			}
			else
			{
				isCreate = DAOFactory.getIAnimeDAOInstance().doCreate(anime.getName(), ID, anime.getLink(), 0);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;
	}
}
