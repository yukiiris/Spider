package com.AnimeSpider.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.AnimeSpider.factory.DAOFactory;
import com.AnimeSpider.vo.User;

public class LoginServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException
	{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		List<String> info = new ArrayList<String>();
		
		int i;
		System.out.println(password);
		if (name == null || "".equals(name))
		{
			info.add("名字不能为空");
		}
		if (password == null || "".equals(password))
		{
			info.add("密码不能为空");
		}
		if (info.size() == 0)
		{
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			try
			{
				if (DAOFactory.getIUserDAOInstance().findUser(user))
				{
					i = user.getID();
					request.setAttribute("i", i);
				}
				else
				{
					info.add("登录失败！");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		request.setAttribute("info", info);
		if (info.size() == 0)
		{
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else
		{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException
	{
		doGet(request, response);
	}
}