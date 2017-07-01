import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args)
	{
		String url = "https://www.zhihu.com/explore/recommendations";
		String content = Spider.SendGet(url);
		ArrayList<ZhiHu> zhiHus = Spider.GetZhiHu(content);

		ArrayList<ZhiHu> zhiHus = Spider.GetRecommendation(content);
		for (ZhiHu zhiHu : zhiHus)
		{
			FileReaderWriter.writeIntoFile(zhiHu.writeString(), "C:/Users/asus/Desktop/Recommendation.txt", true);
		}
	}

}
