package com.AnimeSpider.dao.lmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.AnimeSpider.dao.IUserDao;
import com.AnimeSpider.vo.User;

public class UserDaoImpl implements IUserDao{
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
			String sql = "INSERT INTO user(id,name,password) VALUES(?,?,?)";
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

}
