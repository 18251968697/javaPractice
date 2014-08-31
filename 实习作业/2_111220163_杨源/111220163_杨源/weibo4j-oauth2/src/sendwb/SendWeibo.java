package sendwb;
import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

public class SendWeibo 
{
		public void sendWeibo(String str) throws WeiboException
		{

			Weibo weibo = new Weibo();  
			String access_token ="2.00DhZukC0nbQEb4e1ccefcfdJ2MgsC";
			weibo.setToken(access_token);            
			Timeline tm = new Timeline();
			tm.client.setToken(access_token);
			Status status = tm.UpdateStatus(str);

		}
}


