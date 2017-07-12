package com.RESTSpider.dao.Impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.RESTSpider.dao.IAnimeDAO;
import com.RESTSpider.spider.Spider;
import com.RESTSpider.vo.Anime;



public class AnimeDaoImpl implements IAnimeDAO{
	private Connection conn = null;
	private PreparedStatement pstm = null;
	
	public AnimeDaoImpl(Connection conn)
	{
		this.conn = conn;
	}
	
	public boolean doCreate(String name, int ID, int isFollow) throws Exception
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO list(name,uid,isfollow) VALUES(?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, ID);
			pstm.setInt(3, isFollow);
	
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

	public List<Anime> searchAnime(String name) 
	{
		String url = null;
		List<Anime> animes = null;
		try
		{
			if (name != null)
			{
				url = URLEncoder.encode(name, "UTF-8");
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		animes = Spider.getAnime(Spider.getContent("http://m.dmzj.com/search/" + url + ".html"));
		return animes;
	}

	public boolean findAnime(String name, int UID)
	{
		boolean isFind = false;
		try
		{
			String sql = "SELECT * FROM list WHERE name=? and uid=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, UID);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				isFind = true;
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

	public List<Anime> getAll(int UID)
	{
		List<Anime> animes = new ArrayList<>();
		try
		{
			String sql = "SELECT * FROM list WHERE uid=" + UID;
			pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				Anime anime = new Anime();
				anime.setName(rs.getString("name"));
				animes.add(anime);
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
		return animes;
	}
}
