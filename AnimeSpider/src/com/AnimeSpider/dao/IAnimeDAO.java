package com.AnimeSpider.dao;

import java.util.List;

import com.AnimeSpider.vo.Anime;

public interface IAnimeDAO {

	public boolean doCreate(String name, int ID) throws Exception;
	public List<Anime> findAnime(String name);
}
