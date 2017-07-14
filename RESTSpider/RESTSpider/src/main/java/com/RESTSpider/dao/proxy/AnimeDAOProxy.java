package com.RESTSpider.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.RESTSpider.dao.IAnimeDAO;
import com.RESTSpider.dao.Impl.AnimeDaoImpl;
import com.RESTSpider.dbc.DatabaseConnection;
import com.RESTSpider.vo.Anime;



public class AnimeDAOProxy implements IAnimeDAO{

	private DatabaseConnection dbc = null;
	private IAnimeDAO dao = null;
	
	public AnimeDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new AnimeDaoImpl(dbc.getConnection());
	}
	
	public boolean doCreate(String name, int ID, String link, int isFollow) throws Exception
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.doCreate(name, ID, link, isFollow);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isCreate; 
	}

	public boolean deleteAnime(int user, String anime, int isFollow)
	{
		boolean isDelete = false;
		try
		{
			isDelete = dao.deleteAnime(user, anime, isFollow);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isDelete; 
	}
	
	public List<Anime> searchAnime(String name)
	{
		List<Anime> animes = null;
		try
		{
			animes = dao.searchAnime(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return animes;
	}

	public boolean findAnime(String name, int UID)
	{
		boolean isFind = false;
		try
		{
			isFind = dao.findAnime(name, UID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isFind;
	}

	public  List<Anime> getAll(int UID, int isFollow)
	{
		List<Anime> animes = new ArrayList<>();
		try 
		{
			animes = dao.getAll(UID, isFollow);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return animes;
	}
	
	public String getLink(String name)
	{
		String link = "";
		try
		{
			link = dao.getLink(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbc.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return link;
	}
}
