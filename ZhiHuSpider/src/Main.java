import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args)
	{
		String url = "https://www.zhihu.com/explore/recommendations";
		String content = Spider.SendGet(url);
		ArrayList<ZhiHu> zhiHus = Spider.GetZhiHu(content);
		System.out.println(zhiHus);
	}

}
