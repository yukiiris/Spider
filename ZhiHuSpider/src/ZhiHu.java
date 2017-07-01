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
<<<<<<< HEAD
			Pattern pattern = Pattern.compile("QuestionHeader-title\">.+?>(.+?)<!--");
=======
			Pattern pattern = Pattern.compile("QuestionHeader-title\">(.+?)</h1>");
>>>>>>> 907d722286761bbd9a62e79d338d90021ebcda35
			Matcher matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				question = matcher.group(1);
			}
<<<<<<< HEAD
			pattern = Pattern.compile("");
=======
			
			pattern = Pattern.compile("QuestionHeader-detail.+?<span.+?>(.+?)</span>");
			matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				description = matcher.group(1);
			}
			
			pattern = Pattern.compile("class=\"QuestionAnswers-answers.*?<p>(.+?)</span>");
			matcher = pattern.matcher(content);
			
			if (matcher.find())
			{
				answers.add(matcher.group(1));
			}
>>>>>>> 907d722286761bbd9a62e79d338d90021ebcda35
		}
	}
	
	boolean getRealUrl(String url)
	{
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		
		if (matcher.find())
		{
<<<<<<< HEAD
			ZhiHuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
=======
			ZhiHuUrl = "https://www.zhihu.com/question/" + matcher.group(1);
>>>>>>> 907d722286761bbd9a62e79d338d90021ebcda35
		}
		else
		{
			return false;
		}
		return true;
	}
	
	public String toString()
	{
<<<<<<< HEAD
		return "问题：" + question + "\n" + "描述" + description + 
				"链接" + ZhiHuUrl + "\n回答" + answers + "\n";
=======
		return "问题：" + question + "\n" + "描述:" + description + "\n" +
				"链接:" + ZhiHuUrl + "\n回答:" + answers + "\n";
>>>>>>> 907d722286761bbd9a62e79d338d90021ebcda35
	}
}
