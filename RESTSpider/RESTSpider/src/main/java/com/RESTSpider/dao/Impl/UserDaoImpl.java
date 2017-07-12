package com.RESTSpider.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.RESTSpider.dao.IUserDAO;
import com.RESTSpider.vo.User;



public class UserDaoImpl implements IUserDAO{
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public UserDaoImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean doCreate(User user) throws SQLException
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO user(id,name,email,password) VALUES(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getEmail());
			pstm.setString(3, user.getPassword());
			
			if (pstm.executeUpdate() > 0)
			{
				isCreate = true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return isCreate;
	}


	public boolean findUser(User user) throws Exception
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT id FROM user WHERE (name=? and password=?) or (email=? and password=?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setString(3, user.getName());
			pstm.setString(4, user.getPassword());
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				isFind = true;
				user.setID(rs.getInt(1));
			}
			rs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (pstm != null)
				{
					pstm.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}
		return isFind;
	}

}
