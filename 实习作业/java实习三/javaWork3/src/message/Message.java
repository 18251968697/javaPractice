package message;

import user.Email;
import user.User;

public class Message {
	public static Email[] getUserEmail(User user){
		return user.getAllEmail();
	}
	public static Email[] getUserMessage(User user){
		return user.getAllMessage();
	}
}
