package com.RESTSpider.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.RESTSpider.dao.IKeyDAO;

public class KeyDaoImpl implements IKeyDAO{

	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public KeyDaoImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public String getKey(String name)
	{
		String sql = "SELECT * FROM user WHERE name=?";
		String key = "";
		try
		{
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				key = rs.getString("secret_key");
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
		return key;
	}
	
	public boolean setKey(String name, String key)
	{
		boolean isCreate = false;
		try
		{
			String sql = "UPDATE user SET secret_key=? WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, key);
			pstm.setString(2, name);
			
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
}
