package com.AnimeSpider.dao.proxy;

import java.sql.SQLException;

import com.AnimeSpider.dao.IUserDAO;
import com.AnimeSpider.dao.lmpl.UserDaoImpl;
import com.AnimeSpider.dbc.DatabaseConnection;
import com.AnimeSpider.vo.User;

public class UserDAOProxy implements IUserDAO{

	private DatabaseConnection dbc = null;
	private IUserDAO dao = null;
	
	public UserDAOProxy() throws SQLException, ClassNotFoundException
	{
		try
		{
			dbc = new DatabaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		dao = new UserDaoImpl(dbc.getConnection());
	}
	
	public boolean doCreate(User user) throws Exception
	{
		boolean isCreate = false;
		try
		{
			isCreate = dao.doCreate(user);
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
