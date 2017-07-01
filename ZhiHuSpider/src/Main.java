import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args)
	{
		String url = "https://www.zhihu.com/explore/recommendations";
		String content = Spider.SendGet(url);
		ArrayList<ZhiHu> zhiHus = Spider.GetZhiHu(content);
=======
		ArrayList<ZhiHu> zhiHus = Spider.GetRecommendation(content);
>>>>>>> 907d722286761bbd9a62e79d338d90021ebcda35
		System.out.println(zhiHus);
	}

}
