package sendwb;
import weibo4j.model.WeiboException;

public class Test {

	public static void main(String args[]) throws WeiboException
	{
		SendWeibo swb = new SendWeibo();
		swb.sendWeibo("heiio, world!");
	}
}
