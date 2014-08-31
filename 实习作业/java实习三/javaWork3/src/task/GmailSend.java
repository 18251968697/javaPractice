package task;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class GmailSend
{
	public static boolean sendMail(String id, String passkey, String title, String cst, String toId) {
		String host = "smtp.gmail.com";  
	    Properties props = System.getProperties();  
	    props.put("mail.smtp.starttls.enable", "true"); // 在本行添加  
	    props.put("mail.smtp.host", host);  
	    props.put("mail.smtp.user", id);  
	    props.put("mail.smtp.password", passkey);  
	    props.put("mail.smtp.port", "587");  
	    props.put("mail.smtp.auth", "true");  

		 
	    String[] to = {toId}; // 在本行添加  
	    try{
		    Session session = Session.getDefaultInstance(props, null);  
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(id));  
		 
		    InternetAddress[] toAddress = new InternetAddress[to.length];  
		 
		    // 获取地址的array  
		    for( int i=0; i < to.length; i++ ) { // 从while循环更改而成  
		        toAddress[i] = new InternetAddress(to[i]);  
		    }  
		 
		    for( int i=0; i < toAddress.length; i++) { // 从while循环更改而成  
		        message.addRecipient(Message.RecipientType.TO, toAddress[i]);  
		    }  
		    message.setSubject(title);  
		    message.setText(cst);  
		    Transport transport = session.getTransport("smtp");  
		    transport.connect(host, id, passkey);  
		    transport.sendMessage(message, message.getAllRecipients());  
		    transport.close();  
	    }catch(Exception e){e.printStackTrace(); return false;}
	    
		return true;
	}
	 public static void main(String[] args) throws Exception 
	 {
					
	 }
}
