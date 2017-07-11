package com.AnimeSpider.dao.proxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AnimeSpider.dao.IAnimeDAO;
import com.AnimeSpider.dao.lmpl.AnimeDaoImpl;
import com.AnimeSpider.dbc.DatabaseConnection;
import com.AnimeSpider.vo.Anime;

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
	
	public boolean doCreate(String name, int ID, int isFollow) throws Exception
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.doCreate(name, ID, 1);
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

	public  List<Anime> getAll(int UID)
	{
		List<Anime> animes = new ArrayList<>();
		try 
		{
			animes = dao.getAll(UID);
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
}
