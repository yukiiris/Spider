import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Spider {

	static String SendGet(String url)
	{
		String result = "";
		BufferedReader in = null;
		
		try
		{
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null)
			{
				result += line;
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	static ArrayList<ZhiHu> GetRecommendation(String content)
	{
		ArrayList<ZhiHu> results = new ArrayList<ZhiHu>();
	
		Pattern pattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher matcher = pattern.matcher(content);
		
		boolean isFind = matcher.find();
		
		while (isFind)
		{
			ZhiHu zhiHu = new ZhiHu(matcher.group(1));
			results.add(zhiHu);
			isFind = matcher.find();
		}
		return results;
	}
}
