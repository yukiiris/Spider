package com.AnimeSpider.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.AnimeSpider.factory.DAOFactory;
import com.AnimeSpider.vo.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		String path = "register.jsp";
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		List<String> info = new ArrayList<String>();
		
		if (name == null || "".equals(name))
		{
			info.add("用户名不能为空");
		}
		else if (name.length() > 9)
		{
			info.add("用户名过长");
		}
		if (password == null || "".equals(password))
		{
			info.add("密码不能为空");
		}
		else if (!verify.equals(verify))
		{
			info.add("两次密码输入不一致");
		}
		else if (password.length() > 9)
		{
			info.add("密码过长");
		}
		if (!Pattern.matches( ".+?@.+?",email))
		{
			info.add("pattern error");
		}
		
		if (info.size() == 0)
		{
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			try
			{
				if (DAOFactory.getIUserDAOInstance().doCreate(user))
				{
					info.add("创建成功");
				}
				else
				{
					info.add("创建失败");
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		request.setAttribute("info", info);
		request.getRequestDispatcher(path).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doGet(request, response);
	}
}
