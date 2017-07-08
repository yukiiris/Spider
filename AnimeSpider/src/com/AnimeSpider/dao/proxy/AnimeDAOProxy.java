package com.AnimeSpider.dao.proxy;

import java.sql.SQLException;

import com.AnimeSpider.dao.IAnimeDAO;
import com.AnimeSpider.dao.lmpl.AnimeDaoImpl;
import com.AnimeSpider.dbc.DatabaseConnection;

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
	
	public boolean doCreate(String name, int ID) throws Exception
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.doCreate(name, ID);
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
}