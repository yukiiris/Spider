import java.util.ArrayList;

public class ZhiHu {

	public String question;
	public String ZhiHuUrl;
	public ArrayList<String> answers;
	
	public ZhiHu()
	{
		question = "";
		ZhiHuUrl = "";
		answers = new ArrayList<String>();
	}
	
	public String toString()
	{
		return "���⣺" + question + "\n����" + ZhiHuUrl + "\n�ش�" + answers + "\n";
	}
}
