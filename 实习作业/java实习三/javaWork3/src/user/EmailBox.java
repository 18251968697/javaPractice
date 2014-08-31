package user;

public class EmailBox { 
	private Email box[];
	private Email board[];
	private int letterNum, messageNum;
	public static final int BOX_MAX_SIZE = 50, BOARD_MAX_SIZE = 5;
	
	public EmailBox(){
		box = new Email[BOX_MAX_SIZE];
		board = new Email[BOARD_MAX_SIZE];
		letterNum = 0;
		messageNum = 0;
	}
	
	public int getLetterNum(){
		return letterNum;
	}
	public int getMessageNum(){
		return messageNum;
	}
	
	public boolean addMessage(User user, Email email){
		if(user.getType() != User.ADMINISTER)
			return false;
		if(messageNum >= BOARD_MAX_SIZE){
			int i=0;
			for(i=0; i<messageNum; i++)
				if(board[i].getReadFlag())
					break;
			for(i+=1;i<messageNum; i++){
				board[i-1] = null;
				board[i] = board[i-1];
			}
			board[i-1] = null;
			board[i-1] = new Email(email);
			return true;
		}
		board[messageNum] = new Email(email);
		messageNum++;
		return true;
	}
	public boolean addEmail(Email email){
		if(letterNum >= BOX_MAX_SIZE)
			return false;
		box[letterNum] = new Email(email);
		letterNum++;
		return true;
	}
	
	public Email getEmail(int i){
		if(i >= letterNum)
			return null;
		return new Email(box[i]);
	}
	public Email[] getAllEmail(){
		if(letterNum == 0)
			return null;
		Email email[] = new Email[letterNum];
		for(int i=0; i<letterNum; i++)
			email[i] = new Email(box[i]);
		return email;
	}
	public Email getMessage(int i){
		if(i >= messageNum)
			return null;
		return new Email(board[i]);
	}
	public Email[] getAllMessage(){
		if(messageNum == 0)
			return null;
		Email message[] = new Email[messageNum];
		for(int i=0; i<messageNum; i++)
			message[i] = new Email(board[i]);
		return message;
	}
	
	public boolean deleteEmail(int i){
		if(i >= letterNum)
			return false;
		for(i+=1; i<letterNum; i++){
			box[i-1] = null;
			box[i-1] = box[i];
		}
		box[i-1] = null;
		letterNum--;
		return true;
	}
	public boolean deleteMessage(int i){
		if(i >= messageNum)
			return false;
		for(i+=1; i<messageNum; i++){
			board[i-1] = null;
			board[i-1] = board[i];
		}
		board[i-1] = null;
		messageNum--;
		return true;
	}
	public void deleteAllEmail(){
		for(int i=0; i<letterNum; i++)
			box[i] = null;
		letterNum = 0;
	}
	public void deleteAllMessage(){
		for(int i=0; i<messageNum; i++)
			board[i] = null;
		messageNum = 0;
	}
}
