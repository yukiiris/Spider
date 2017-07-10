package com.AnimeSpider.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AnimeSpider.factory.DAOFactory;
import com.AnimeSpider.vo.Anime;

public class FindAnimeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		List<Anime> animes = null;
		String name = request.getParameter("name");
		String path = "search.jsp";
		System.out.println(name);
		try
		{
			animes = DAOFactory.getIAnimeDAOInstance().findAnime(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if (animes != null)
		{
			for (Anime anime : animes)
			{
				System.out.println(anime.getName());
				System.out.println(anime.getLink());
			}
		}
		request.setAttribute("animes", animes);
		request.getRequestDispatcher(path).forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException
	{
		doGet(request, response);
	}

}
