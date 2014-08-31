package task;

import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GmailRecv
{
	public static boolean recvMail(String id, String passkey) throws Exception
	{
		try {
			Properties props = new Properties();  
			props.setProperty("mail.store.protocol", "imaps");  
			props.setProperty("mail.imap.host", "imap.gmail.com"); 
			Session session = Session.getInstance(props);
			
			Store store = session.getStore("imaps"); 
			session.setDebug(false);
			store.connect("imap.gmail.com", id, passkey);
			
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			if(folder.getUnreadMessageCount()<=0)
				return false;
			return true;
		}
		catch (Exception e) {e.printStackTrace(); return false;}
	}
			
	
	public static void main(String arg[]) throws Exception
	{
		sendwb.SendWb sw = new sendwb.SendWb();
		sw.sendWb("What a fuck!");
	/*	if(GmailRecv.recvMail("yangzh.nju@gmail.com", "yz344047780")==true)
			System.out.println("success!!");
		else
		System.out.println("error!");*/
	}

}
