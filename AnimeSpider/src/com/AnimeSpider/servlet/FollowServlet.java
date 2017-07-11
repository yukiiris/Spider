package com.AnimeSpider.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AnimeSpider.factory.DAOFactory;

public class FollowServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		int UID = Integer.parseInt(request.getParameter("uid"));
		String na = URLDecoder.decode(request.getParameter("AName"), "UTF-8");
		String name = new String(na.getBytes("ISO-8859-1"),"UTF-8");
		
		try
		{
			if (DAOFactory.getIAnimeDAOInstance().findAnime(name, UID))
			{
				//TODO
			}
			else
			{
				DAOFactory.getIAnimeDAOInstance().doCreate(name, UID, 1);
				request.getRequestDispatcher("following.jsp");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		request.getRequestDispatcher("search.jsp");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException
	{
		doGet(request, response);
	}
}
