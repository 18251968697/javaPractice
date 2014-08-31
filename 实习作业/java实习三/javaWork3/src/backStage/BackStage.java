package backStage;

import user.Email;
import user.EmailBox;
import user.User;
import user.Transaction;
public class BackStage {
		public static String getUserInfo(User user[], int i){
			String info = "";
			info += "ID:	"+user[i].getId()+
					"\n密码	"+Integer.toString(user[i].getVip())+
					"\n余额：	"+Long.toString((long)user[i].getBalance())+"\n"
					+"交易清单："+"\n"+"序号	"+ "类型	"+"金额	"+"余额	"+"日期		"+"描述"
					+"\n-----------------------------------------------------------\n";
			int len = user[i].getTransactionsNum();
			Transaction tmp;
			for(int j=0; j<len; j++){
				tmp = user[i].getTransaction(j);
				info += j+":	"+tmp.getType()+"	"+tmp.getAmount()+"	"
						+tmp.getBalance()+"	"+tmp.getDate().substring(0,11)
						+"	"+tmp.getDescription()+'\n';
			}
			info += "\n\n";
			return info;
		}
		public static String getAllUserInfo(User user[]){
			String info = "";
			for(int i=0; i<user.length&&user[i]!=null; i++){
				info += getUserInfo(user, i);
			}
			return info;						
		}
		public static void addTask(){
			
		}
		public static void deleteTask(){
			
		}
		public static void alterTask(){
			
		}
		public static Email[] getUserEmail(User user[],int i){
			Email email[] = new Email[user[i].getLetterNumber()];
			for(int j=0; j<user[i].getLetterNumber(); j++)
				email[j] = user[i].getEmail(j);
			return email;
		}
		public static Email[] getUserMessage(User user[], int i){
			Email message[] = new Email[user[i].getMessageNumber()];
			for(int j=0; j<user[i].getMessageNumber(); j++)
				message[j] = user[i].getMessage(j);
			return message;
		}
		public static void main(String args[]){
			User user[] = new User[10];
			user[0] = new User("yangzh", "yyyy", User.ADMINISTER);
			user[1] = new User("yangyuan", "zzzzz", User.USER);
			user[0].recharge(100);
			user[0].consume(50);
			user[1].recharge(50);
			System.out.println(getAllUserInfo(user));
		}
}
