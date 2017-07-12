package com.RESTSpider.vo;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
	
	private int ID;
	private String name;
	private String email;
	private String password;
	private String pendingList;
	private String followingList;
	
	public User()
	{
		
	}
	
	public User(String name, String email, String password)
	{
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public int getID() 
	{
		return ID;
	}
	public void setID(int iD) 
	{
		ID = iD;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getPendingList()
	{
		return pendingList;
	}
	public String getFollowingList()
	{
		return followingList;
	}


}