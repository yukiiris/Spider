package com.AnimeSpider.dao;

import java.util.List;

import com.AnimeSpider.vo.Anime;

public interface IAnimeDAO {

	public boolean doCreate(String name, int ID, int isFollow) throws Exception;
	public List<Anime> searchAnime(String name);
	public boolean findAnime(String name, int UID);
	public List<Anime> getAll(int UID);
}
