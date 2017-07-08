package com.AnimeSpider.dao.lmpl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.AnimeSpider.dao.IAnimeDAO;

public class AnimeDaoImpl implements IAnimeDAO{
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public AnimeDaoImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean doCreate(String name, int ID) throws Exception
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO user(id,name,password) VALUES(?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, ID);
			
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
