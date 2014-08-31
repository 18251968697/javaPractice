package sendwb;

import weibo4j.Timeline;  
import weibo4j.Weibo;  
import weibo4j.examples.oauth2.Log;  
import weibo4j.model.Status;  
import weibo4j.model.WeiboException;

public class SendWb {
   
	    public static void sendWb(String cst) {  
	        String access_token = "2.00sd2jTChYv6CB333ac17dea0EErRe";  
	        String statuses = cst;  
	        Weibo weibo = new Weibo();  
	        weibo.setToken(access_token);  
	        Timeline tm = new Timeline(); 
	        tm.client.setToken(access_token);
	        try {  
	            Status status = tm.UpdateStatus(statuses);  
	            Log.logInfo(status.toString());  
	        } catch (WeiboException e) { e.printStackTrace();}   
	   }  
	    public static void main(String args[]){
	    	SendWb.sendWb("Hello world!");
	    	
	    }
}
