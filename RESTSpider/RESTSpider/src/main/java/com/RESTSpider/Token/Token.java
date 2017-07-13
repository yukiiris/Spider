package com.RESTSpider.Token;


import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;


import javax.annotation.security.PermitAll;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.RESTSpider.factory.DAOFactory;
import com.RESTSpider.vo.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;  

@PermitAll()
@Path("/login")
public class Token {

	public static byte[] result; 
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public static String authenticateUser(User user)
	{
		Key key = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");  
			keyGenerator.init(new SecureRandom());  
			SecretKey secretKey= keyGenerator.generateKey();  
			//转换key秘钥  
			DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(secretKey.getEncoded());  
			SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
			key = secretKeyFactory.generateSecret(deSedeKeySpec);
		} catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		String keyencode= HexBin.encode(key.getEncoded());
		//System.out.println(keyencode);
		String jwt = "";
		Date expiry = getExpiryDate(30 * 24 * 60);
		if (authenticate(user))
		{
			//key = MacProvider.generateKey(SignatureAlgorithm.HS256); 
			try {
				DAOFactory.getIKeyDAOInstance().setKey(user.getName(), keyencode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(key);
			jwt = getJWTString(user.getName(), expiry, key);
		}
		return jwt;
	}
	
	private static Date getExpiryDate(int minutes)
	{
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minutes);
		
		return calendar.getTime();
	}
	
	private static boolean authenticate(User user)
	{
		boolean isFind = false;
		try
		{
			isFind = DAOFactory.getIUserDAOInstance().findUser(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return isFind;
	}
	
	public static String getJWTString(String name, Date expires, Key key)
	{	
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String jwt = Jwts.builder()
				.setIssuer("Jersey-Security-Basic")
				.setSubject(name)
				.setAudience("user")
				.setExpiration(expires)
				.setIssuedAt(new Date())
				.setId("1")
				.signWith(signatureAlgorithm, key)
				.compact();
		return jwt;
	}
	
    public static Key receiveSecret(String keystr){  
        Key key = null;
    	try {           
         //2.通过读取到的key  获取到key秘钥对象  
            byte[] keybyte= HexBin.decode(keystr); 
            DESedeKeySpec deSedeKeySpec=new DESedeKeySpec(keybyte);  
            SecretKeyFactory secretKeyFactory=SecretKeyFactory.getInstance("DESede");  
            key = secretKeyFactory.generateSecret(deSedeKeySpec); //获取到key秘钥  
             //System.out.println(key);
        } catch (Exception e) {  
            // TODO: handle exception  
            e.printStackTrace();  
        }  
    	return key;
    }
	
//    public static void main(String[] args)
//	{
//		String jsw = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJKZXJzZXktU2VjdXJpdHktQmFzaWMiLCJzdWIiOiJtb2x0cmVzIiwiYXVkIjoidXNlciIsImV4cCI6MTUwMjU1MDE2MSwiaWF0IjoxNDk5OTU4MTYxLCJqdGkiOiIxIn0.lyn8YnasAZYOFZHK1REmt4lR56AscDdivEG6oZIX38U";
//			User user = new User("moltres", "25852469@qq.com", "123456");
//
//			//System.out.println(authenticateUser(user));
//			try {
//			System.out.println(receiveSecret(DAOFactory.getIKeyDAOInstance().getKey("moltres")));
//				System.out.println(Jwts.parser().setSigningKey(receiveSecret(DAOFactory.getIKeyDAOInstance().getKey("moltres"))).parseClaimsJws(jsw).getBody().getSubject());
//			} catch (Exception e) {
////				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
}
