package com.AnimeSpider.factory;

import com.AnimeSpider.dao.IAnimeDAO;
import com.AnimeSpider.dao.IUserDAO;
import com.AnimeSpider.dao.proxy.AnimeDAOProxy;
import com.AnimeSpider.dao.proxy.UserDAOProxy;

public class DAOFactory {
	
	public static IUserDAO getIUserDAOInstance() throws Exception
	{
			System.out.println("¹¤³§´´½¨³É¹¦");
			return new UserDAOProxy();
	}
	
	public static IAnimeDAO getIGoodsDAOInstance() throws Exception
	{
		return new AnimeDAOProxy();
	}
}
