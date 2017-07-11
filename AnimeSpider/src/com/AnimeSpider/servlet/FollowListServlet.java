package com.AnimeSpider.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AnimeSpider.factory.DAOFactory;
import com.AnimeSpider.vo.Anime;

public class FollowListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		int UID = (int) request.getAttribute("UID");
		List<Anime> animes = new ArrayList<>();
		
		try
		{
			animes = DAOFactory.getIAnimeDAOInstance().getAll(UID);
		}
		catch  (Exception e)
		{
			e.printStackTrace();
		}
		request.setAttribute("animes", animes);
		request.getRequestDispatcher("following.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException
	{
		doGet(request, response);
	}
}
