package task;

import java.util.Timer;

import java.util.TimerTask;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JTextArea;


public class Task	//任务类，各种任务类的祖宗类
{
	public String taskName=null;
	public SubTask ifTask=null, thenTask=null;
	
	public final static String READY="ready", RUNNING="running", SUSPEND="suspend", END="end", CONTINUE="continue";
}




abstract class IfTypeTask extends SubTask	 	//ifThis任务类接口
{
	public abstract boolean ifThis();
}

abstract class ThenTypeTask extends SubTask		//thenThat任务类接口
{
	public abstract boolean thenThat();
}

class myDate	//日期结构
{
	String year, month, day;
	
	public myDate()
	{
		year = "2013";
		month = "11";
		day = "18";
	}
	public myDate(String y, String m ,String d)
	{
		year = y;
		month = m;
		day = d;
	}	
	public myDate(myDate d)
	{
		year = d.year;
		month = d.month;
		day = d.day;
	}
	
	public int compareTo(myDate a){
		if(!this.year.equals(a.year))
			return this.year.compareTo(a.year);
		if(!this.month.equals(a.month))
			return this.month.compareTo(a.month);
		if(!this.day.equals(a.day))
			return this.day.compareTo(a.day);
		return 0;	
	}
	
	public void print(){
		System.out.println(year+"-"+month+"-"+day);
	}
}

class Time		//时间结构
{
	String hour, minute, seconde;
	
	public Time()
	{
		hour = "00";
		minute = "00";
		seconde = "00";
	}
	public Time(String h, String m, String s)
	{
		hour = h;
		minute = m;
		seconde = s;
	}
	public Time(Time t)
	{
		hour = t.hour;
		minute = t.minute;
		seconde = t.seconde;	
	}
	public int compareTo(Time a){
		if(!this.hour.equals(a.hour))
			return this.hour.compareTo(a.hour);
		if(!this.minute.equals(a.minute))
			return this.minute.compareTo(a.minute);
		if(!this.seconde.equals(a.seconde))
			return this.seconde.compareTo(a.seconde);
		return 0;
	}
	
	public void print(){
		System.out.println(hour+":"+minute+":"+seconde);
	}
}

class Acount	//账号结构
{
	String id=null, passkey=null;
	
	public Acount(String i, String pk)
	{
		id = i;
		passkey = pk;		
	}
	public Acount(Acount a)
	{
		id = a.id;
		passkey = a.passkey;
	}
}


class OrderTime extends IfTypeTask		//定时任务类
{
	private myDate date;	//日期
	private Time time;		//时间
	
	public OrderTime(myDate d, Time t)
	{
		description += "初始化定时任务...\n";
		date = new myDate(d);
		time = new Time(t);
		super.type = SubTask.ORDERTIME;
		description += "初始化完成.\n";
	}

	public boolean  ifThis()	//将设定的闹钟时间与当前系统时间比较，如果时间到则返回true，否则返回false
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String current = df.format(new Date());
		myDate currentDate = new myDate(current.substring(0, 4), current.substring(5, 7), current.substring(8, 10));
		Time currentTime = new Time(current.substring(11, 13), current.substring(14, 16), current.substring(17, 19));
		if((date.compareTo(currentDate)==0 && time.compareTo(currentTime)<=0) || (date.compareTo(currentDate)<0))
			return true;
		else
			return false;
	}
	
	public void taskStart(){
		description += "定时开始...\n";
	}
	public void taskSuccess(){
		description += "时间到.\n";
	}
	public void taskFailed(){
		description += "定时失败.\n";
	}
	public void taskSuspend(){
		description += "定时任务中止.\n";
	}
	public void taskEnd(){
		description += "定时任务结束.\n";
	}
}

class RecvMail extends IfTypeTask		//收取邮件类
{
	private Acount mailAcount;		//账号

	public RecvMail(Acount a)
	{
		description += "初始化查收邮件任务...\n";
		mailAcount = new Acount(a);
		super.type = SubTask.RECVMAIL;
		description += "初始化完成.\n";
	}
	public boolean ifThis()	//如果收到邮件则返回true，否则返回false
	{
		try
		{
			return GmailRecv.recvMail(mailAcount.id, mailAcount.passkey);
		}catch(Exception e){e.printStackTrace();return false;}		
		
	}
	public void taskStart(){
		description += "查收邮件开始...\n";
	}
	public void taskSuccess(){
		description += "收到新邮件.\n";
	}
	public void taskFailed(){
		description += "未收到新邮件.\n";
	}
	public void taskSuspend(){
		description += "查收邮件任务中止.\n";
	}
	public void taskEnd(){
		description += "查收邮件任务结束.\n";
	}
}

class SendMail extends ThenTypeTask		//发送邮件类
{
	private Acount mailAcount;
	private String toAddr;
	private String title;
	private String cst;
	
	public SendMail(Acount a, String toAddr, String title,String cst)
	{
		description += "初始化发邮件任务...\n";
		mailAcount = new Acount(a);	
		this.toAddr = toAddr;
		this.title = title;
		this.cst = cst;
		super.type = SubTask.SENDMAIL;
		description += "初始化完成.\n";
	}

	public boolean thenThat() 		//如果发送成功则返回true,否则返回false
	{
		try{	
			return GmailSend.sendMail(mailAcount.id, mailAcount.passkey, title, cst, toAddr);
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public void taskStart(){
		description += "开始发送邮件...\n";
	}
	public void taskSuccess(){
		description += "发送成功.\n";
	}
	public void taskFailed(){
		description += "发送失败.\n";
	}
	public void taskSuspend(){
		description += "发送任务中止.\n";
	}
	public void taskEnd(){
		description += "发送任务结束.\n";
	}
	
}


