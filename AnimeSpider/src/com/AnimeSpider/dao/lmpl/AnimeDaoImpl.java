package com.AnimeSpider.dao.lmpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.AnimeSpider.Spider.Spider;
import com.AnimeSpider.dao.IAnimeDAO;
import com.AnimeSpider.vo.Anime;

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

	public List<Anime> findAnime(String name) 
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
}
