package com.RESTSpider.Jersey.Impl;

import com.RESTSpider.Jersey.Api.RESTSpiderUserApi;
import com.RESTSpider.Token.Token;
import com.RESTSpider.factory.DAOFactory;
import com.RESTSpider.vo.User;

import net.sf.json.JSONObject;

public class RESTSpiderUserImpl implements RESTSpiderUserApi{

	public boolean createUser(String json)
	{
		boolean isCreate = false;
		JSONObject object = JSONObject.fromObject(json);
		User user = (User)JSONObject.toBean(object, User.class);
		System.out.println(user.getName());
		
		try
		{
			isCreate = DAOFactory.getIUserDAOInstance().doCreate(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isCreate;
	}
	

	
	public String Login(User user)
	{
		return Token.authenticateUser(user);
	}

}
