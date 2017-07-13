package com.RESTSpider.dao;

import java.util.List;

import com.RESTSpider.vo.Anime;


public interface IAnimeDAO {

	public boolean doCreate(String name, int ID, String link, int isFollow) throws Exception;
	public List<Anime> searchAnime(String name);
	public boolean findAnime(String name, int UID);
	public List<Anime> getAll(int UID, int isFollow);
	public String getLink(String name);
}
