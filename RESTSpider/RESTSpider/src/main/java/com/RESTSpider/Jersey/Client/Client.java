package com.RESTSpider.Jersey.Client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.RESTSpider.vo.User;

public class Client {

	static String url = "http://localhost:8080/RESTSpider/Spider";
	
	public static void main(String[] args)
	{
		addUser();
	}
	
	private static void addUser()
	{
		User user = new User("lily", "123@qq.com", "123456");
		javax.ws.rs.client.Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);  
		WebTarget target = client.target(url + "/new_user");  
		 target.request().buildPost(Entity.entity(user, MediaType.APPLICATION_JSON)).invoke();  
		//((javax.ws.rs.client.Client) response).close();  
	}
}
