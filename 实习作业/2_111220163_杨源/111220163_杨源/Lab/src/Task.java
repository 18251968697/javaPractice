import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JTextArea;
import receive.GmailRecv;
import send.GmailSend;
import sendwb.SendWeibo;
public class Task	{//任务类
	private String taskName;	
	protected int type;		
	private JTextArea jta = null;	
	public Task(){//默认类
		taskName = "";
		type = -1;
	}
	public void rename(String n)	{//重命名任务名
		taskName = n;
	}
	public String getName()		{
		return taskName;
	}
	public int getTaskType()	{
		return type;
	}
	public void getJta(JTextArea jta)	{
		this.jta = jta;
	}
	public void printRes(String str)	{//打印任务
		String tmp = jta.getText();
		if(!tmp.equals(""))
			tmp += "\n"+str;
		else
			tmp = str;
		jta.setText(tmp);
	}	
}
class TaskQueue		{//创建任务队列
    Task task[];	
	int top;	
	public TaskQueue(int len){
		task = new Task[len];
		top = 0;
	}
	public void deleteItem(int index){//删除任务
		if(index>=top || index<0)
			return;
		task[index] = null;
		for(int i=index; i<top-1; i++){
			task[i] = task[i+1];
		}
		top--;
	}
	public void deleteItemWithoutMove(int index){
		task[index] = null;
	}
}
abstract class TaskRequest extends Task	 	{
	public abstract boolean ifThis() throws Exception;

}
abstract class TaskGoal extends Task		{
	public abstract boolean thenThat();
}
class myDate	{//日期
	String year, month, day;
	public myDate(){
		year = "2013";
		month = "11";
		day = "18";
	}
	public myDate(String y, String m ,String d){
		year = y;
		month = m;
		day = d;
	}	
	public myDate(myDate d){
		year = d.year;
		month = d.month;
		day = d.day;
	}
}
	class Time		{//时间类
	String hour, minute, seconde;
	public Time(){
		hour = "00";
		minute = "00";
		seconde = "00";
	}
	public Time(String h, String m, String s){
		hour = h;
		minute = m;
		seconde = s;
	}
	public Time(Time t){
		hour = t.hour;
		minute = t.minute;
		seconde = t.seconde;	
	}
}
class Acount	{//用户名和密码类
	String id = "", passkey = "";
	public Acount(){}
	public Acount(String i, String pk){
		id = i;
		passkey = pk;		
	}
	public Acount(Acount a){
		id = a.id;
		passkey = a.passkey;
	}
}
class OrderTime extends TaskRequest		{//定时
	private myDate date;
	private Time time;
	public OrderTime(){
		date = new myDate();
		time = new Time();
		super.type = 0;
	}
	public OrderTime(myDate d, Time t){
		date = new myDate(d);
		time = new Time(t);
		super.type = 0;
	}
	public void getDateAndTime(myDate d, Time t)	{
		d.year = date.year;
		d.month = date.month;
		d.day = date.day;
		t.hour = time.hour;
		t.minute = time.minute;
		t.seconde = time.seconde;
	}
	public boolean  ifThis(){//if this的实现
		Timer timer = new Timer();
		class myTimerTask extends TimerTask{
			boolean ready = false;
			public void run() {
				ready = true;
			}
			public boolean getReady(){
				return ready;
			}	
		}
		printRes("设置闹钟...");
		myTimerTask myTT = new myTimerTask(); //设置定时器进行定时
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tmp = null;
		try{
			tmp = df.parse(date.year+"-"+date.month+"-"+date.day+" "+time.hour+":"+time.minute+":"+time.seconde);
			timer.schedule(myTT, tmp);
		}
		catch(ParseException e){}
		printRes("等待...");
		try{
			while(true){
				Thread.sleep(1000);
			if(myTT.getReady() == true){
				printRes("时间到，执行任务...");
				break;
			}
		}}
		catch(Exception e){return false;}
		return true;
	}
}

class RecvMail extends TaskRequest	{//收邮件
	private Acount mailAcount;
	public RecvMail(){
		mailAcount = new Acount();
		super.type = 1;
	}
	public RecvMail(Acount a){
		mailAcount = new Acount(a);
		super.type = 1;
	}
	public void getRecvMailAcount(Acount a){
		a.id = mailAcount.id;
		a.passkey = mailAcount.passkey;
	}
	public boolean ifThis()	throws Exception	{
		try{
			printRes("收邮件...");
			if(GmailRecv.recvMail(mailAcount.id, mailAcount.passkey) == true)//只要邮箱里有邮件，即视为有新邮件
				printRes("发现新邮件...");
			else{
				System.out.println("接收邮件失败！");
				return false;
			}
		}
		catch(Exception e){}		
		return true;	
	}
}
class SendWb extends TaskGoal	{//调用发微博
	Acount wbAcount;
	String wbConstant;
	public SendWb(){
		wbAcount = new Acount();
		wbConstant = "";
		super.type = 2;
	}
	public SendWb(Acount a, String wbCst){
		wbAcount = new Acount(a);
		wbConstant = wbCst;
		super.type = 2;
	}
	public String getAcountAndCst(Acount a){
		a.id = wbAcount.id;
		a.passkey = wbAcount.passkey;
		return wbConstant;
	}
	public boolean thenThat()	{
		try{
			printRes("发送微博...");
			SendWeibo sendWb = new SendWeibo();
			sendWb.sendWeibo(wbConstant);
			printRes("发送完毕...");
		}
		catch(Exception e){
			printRes("发送微博失败！");
			return false;
		}
		return true;
	}
}
class SendMail extends TaskGoal		{//发邮件
	private Acount mailAcount;
	private String mailConstant;	
	public SendMail(){
		mailAcount = new Acount();
		mailConstant = "";
		super.type = 3;
	}
	public SendMail(Acount a, String mailCst){
		mailAcount = new Acount(a);	
		mailConstant = mailCst;
		super.type = 3;
	}
	public String getAcountAndCst( Acount a){
		a.id =mailAcount.id;
		a.passkey = mailAcount.passkey;
		return mailConstant;
	}
	public boolean thenThat() 		{//设置为gmail邮箱发送邮件
		try{
			printRes("发送邮件...");	
			if(GmailSend.sendMail("yy971914033@gmail.com", "971914033", mailConstant, mailAcount.id) == true)
				printRes("发送完毕...");
			else{
				printRes("发送失败！");
				return false;
			}
		}
		catch(AddressException e ){}
		catch(MessagingException a){}
		return true;
	}
}