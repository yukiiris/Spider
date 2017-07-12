package com.RESTSpider.factory;

import com.RESTSpider.dao.IAnimeDAO;
import com.RESTSpider.dao.IUserDAO;
import com.RESTSpider.dao.proxy.AnimeDAOProxy;
import com.RESTSpider.dao.proxy.UserDAOProxy;

public class DAOFactory {
	
	public static IUserDAO getIUserDAOInstance() throws Exception
	{
			return new UserDAOProxy();
	}
	
	public static IAnimeDAO getIAnimeDAOInstance() throws Exception
	{
		return new AnimeDAOProxy();
	}
}