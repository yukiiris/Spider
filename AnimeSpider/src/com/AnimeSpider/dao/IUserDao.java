package com.AnimeSpider.dao;

import com.AnimeSpider.vo.User;

public interface IUserDao {

	public boolean doCreate(User user) throws Exception;
}
