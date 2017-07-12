package com.RESTSpider.dao;

import com.RESTSpider.vo.User;

public interface IUserDAO {

	public boolean doCreate(User user) throws Exception;
	public boolean findUser(User user) throws Exception;
}
