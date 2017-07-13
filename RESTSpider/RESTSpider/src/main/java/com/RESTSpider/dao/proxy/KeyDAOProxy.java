package com.RESTSpider.dao.proxy;

import java.sql.SQLException;

import com.RESTSpider.dao.IKeyDAO;
import com.RESTSpider.dao.Impl.KeyDaoImpl;
import com.RESTSpider.dbc.DatabaseConnection;

public class KeyDAOProxy implements IKeyDAO{
	private DatabaseConnection dbc = null;
	private IKeyDAO dao = null;
	
	public KeyDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new KeyDaoImpl(dbc.getConnection());
	}
	
	public boolean setKey(String name, String key)
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.setKey(name, key);
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
	
	public String getKey(String name)
	{
		String key = "";
		try
		{
			key = dao.getKey(name);
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
		return key;
	}

}
