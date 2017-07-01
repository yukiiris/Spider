import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhiHu {

	public String question;
	public String description;
	public String ZhiHuUrl;
	public ArrayList<String> answers;
	
	public ZhiHu()
	{
		question = "";
		ZhiHuUrl = "";
		description = "";
		answers = new ArrayList<String>();
	}
	
	public ZhiHu(String url)
	{
		question = "";
		ZhiHuUrl = "";
		description = "";
		answers = new ArrayList<String>();
		
		if (getRealUrl(url))
		{
			String content = Spider.SendGet(ZhiHuUrl);

			Pattern pattern = Pattern.compile("QuestionHeader-title\">(.+?)</h1>");
			Matcher matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				question = matcher.group(1);
			}
			
			pattern = Pattern.compile("QuestionHeader-detail.+?<span.*?>(.*?)</span>");
			matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				description = matcher.group(1);
			}
			
			pattern = Pattern.compile("class=\"ContentItem AnswerItem.*?<p>(.+?)</span>");
			matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				answers.add(matcher.group(1));
			}
		}
	}
	
	boolean getRealUrl(String url)
	{
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find())
		{
			ZhiHuUrl = "https://www.zhihu.com/question/" + matcher.group(1);
		}
		else
		{
			return false;
		}
		return true;
	}
	
	public String toString()
	{
		return "问题：" + question + "\n" + "描述:" + description + "\n" +
				"链接:" + ZhiHuUrl + "\n回答:" + answers + "\n";
	}

	public String writeString()
	{
		String result = "";
		result += "问题:" + question +"\r\n";
		result += "描述:" + description + "\r\n";
		result += "链接:" + ZhiHuUrl + "\r\n";
		for (int i = 0; i < answers.size(); i++)
		{
			result += "回答:" + i + ":" + answers.get(i) + "\r\n";
		}
		result += "\r\n\r\n";
		result = result.replaceAll("<br>", "\r\n");
		result = result.replaceAll("<.*?>", "");
		return result;
	}

}
