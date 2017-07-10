package com.AnimeSpider.Spider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.AnimeSpider.vo.Anime;

public class Spider {

	
//	public String get()
//	{
//		String content = null;
//		HttpGet httpGet = new HttpGet(url);
//		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");          
//		try 
//		{
//			CloseableHttpResponse response = httpClient.execute(httpGet);
//			HttpEntity entity = response.getEntity();
//			content = EntityUtils.toString(entity, "utf-8");
//		} 
//		catch (ClientProtocolException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return content;
//	}
//	
//	public String post(List<NameValuePair> nvps)
//	{
//		String content = null;
//		HttpPost httpPost = new HttpPost(url);
//		try {
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			content = EntityUtils.toString(entity, "UTF-8");
//		} 
//		catch (UnsupportedEncodingException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		catch (ClientProtocolException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (ParseException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return content;
//	}
	
	public static String getContent(String url)
	{
		Runtime rt = Runtime.getRuntime();
		Process process = null;
		try {
            process = 
            		rt.exec("/home/moltres/Downloads/phantomjs-2.1.1-linux-x86_64/bin/phantomjs /home/moltres/github/Spider/AnimeSpider/WebContent/parser.js " +url);
            InputStream in = process.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            StringBuffer sbf = new StringBuffer();
            String tmp = "";
            while ((tmp = br.readLine()) != null) {
                sbf.append(tmp);
            }
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public static List<Anime> getAnime(String html)
	{
		List<Anime> animes = new ArrayList<>();
		Document doc = Jsoup.parse(html);
		Elements elements1 = doc.getElementsByClass("itemBox");
		
		for (Element element1 : elements1)
		{
			Anime anime = new Anime();
			Pattern pattern = Pattern.compile("/view/(.+?)/");
			Matcher matcher = pattern.matcher(element1.select("a").last().attr("href"));
			matcher.find();
			anime.setID(Integer.parseInt(matcher.group(1)));
			anime.setLink("http://m.dmzj.com" + element1.select("a").attr("href"));
			anime.setName(element1.select(".title").text());
			animes.add(anime);
		}
		
//		for (Anime anime : animes)
//		{
//			System.out.println(anime.getName());
//			System.out.println(anime.getLink());
//		}
		return animes;
	}
//	public static void main(String[] args)
//	{
//		String url = null;
//		try {
//			url = URLEncoder.encode("一拳超人", "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//GetContent getContent = new GetContent("http://m.dmzj.com/");
//		//System.out.println("http://m.dmzj.com/search/" + url + ".html?");
//		getAnime(getContent("http://m.dmzj.com/search/" + url + ".html"));
//		//System.out.println(getContent("http://m.dmzj.com/search/" + url + ".html"));
//	}
}

