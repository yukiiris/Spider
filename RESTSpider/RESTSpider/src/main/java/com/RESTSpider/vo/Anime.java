package com.RESTSpider.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Anime {

	private String name;
	private int ID;
	private String link;

	public String getName() 
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}