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
	
	public boolean doCreate(String name, int ID, String link, int isFollow) throws Exception
	{
		boolean isCreate = false;
		try
		{
			String sql = "INSERT INTO list(name,uid,isfollow,link) VALUES(?,?,?.?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, ID);
			pstm.setInt(3, isFollow);
			pstm.setString(4, link);
			
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
		animes = Spider.getAnimes(Spider.getContent("http://m.dmzj.com/search/" + url + ".html"));
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

	public List<Anime> getAll(int UID, int isFollow)
	{
		List<Anime> animes = new ArrayList<>();
		try
		{
			String sql = "SELECT * FROM list WHERE uid=? and isfollow=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, UID);
			pstm.setInt(2, isFollow);
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
	
	public String getLink(String name)
	{
		String link = "";
		try
		{
			String sql = "SELECT link FROM list WHERE name=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next())
			{
				link = rs.getString("link");
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
		return link;
	}
}
