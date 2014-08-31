package user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;

public class Task	//任务类，各种任务类的祖宗类
{
	private String taskName;	//任务名
	protected int type;		//任务类型
	private JTextArea jta = null;	//任务结果输出框
	public Task()
	{
		taskName = "";
		type = -1;
	}
	
	public void rename(String n)	//任务重命名
	{
		taskName = n;
	}
	public String getName()		//获取任务名
	{
		return taskName;
	}
	public int getTaskType()	//获取任务类型
	{
		return type;
	}
	public void getJta(JTextArea jta)	//获取任务输出框
	{
		this.jta = jta;
	}
	public void printRes(String str)	//打印任务结果
	{
		String tmp = jta.getText();
		if(!tmp.equals(""))
			tmp += "\n"+str;
		else
			tmp = str;
		jta.setText(tmp);
	}
	
}

class TaskQueue		//任务队列类
{
    Task task[];	//任务数组
	int top;	//数组头
	
	public TaskQueue(int len)
	{
		task = new Task[len];
		top = 0;
	}
	public void deleteItem(int index)
	{
		if(index>=top || index<0)
			return;
		task[index] = null;
		for(int i=index; i<top-1; i++)
		{
			task[i] = task[i+1];
		}
		top--;
	}
	public void deleteItemWithoutMove(int index)
	{
		task[index] = null;
	}
}

abstract class TaskRequest extends Task	 	//ifThis任务类接口
{
	public abstract boolean ifThis();

}

abstract class TaskGoal extends Task		//thenThat任务类接口
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
}

class Acount	//账号结构
{
	String id = "", passkey = "";
	
	public Acount()
	{}
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


class OrderTime extends TaskRequest		//定时任务类
{
	private myDate date;
	private Time time;
	
	public OrderTime()
	{
		date = new myDate();
		time = new Time();
		super.type = 0;
	}
	public OrderTime(myDate d, Time t)
	{
		date = new myDate(d);
		time = new Time(t);
		super.type = 0;
	}
	public void getDateAndTime(myDate d, Time t)
	{
		d.year = date.year;
		d.month = date.month;
		d.day = date.day;
		t.hour = time.hour;
		t.minute = time.minute;
		t.seconde = time.seconde;
	}
	public boolean  ifThis()	//设置闹钟，如果时间到则返回true
	{
		Timer timer = new Timer();
		class myTimerTask extends TimerTask
		{

			boolean ready = false;
			public void run() {
				ready = true;
			}
			public boolean getReady()
			{
				return ready;
			}
			
		}
		printRes("设置闹钟...");
		myTimerTask myTT = new myTimerTask(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tmp = null;
		try
		{
			tmp = df.parse(date.year+"-"+date.month+"-"+date.day+" "
					+time.hour+":"+time.minute+":"+time.seconde);
			timer.schedule(myTT, tmp);

		}catch(ParseException e){}
		printRes("等待...");
		try{
			while(true)
			{
				Thread.sleep(1000);
			if(myTT.getReady() == true)
			{
				printRes("时间到，执行任务...");
				break;
			}
		}}
		catch(Exception e){return false;}
	
		return true;
	}

}

class RecvMail extends TaskRequest		//收取邮件类
{
	private Acount mailAcount;

	public RecvMail()
	{
		mailAcount = new Acount();
		super.type = 1;
	}
	public RecvMail(Acount a)
	{
		mailAcount = new Acount(a);
		super.type = 1;
	}
	public void getRecvMailAcount(Acount a)
	{
		a.id = mailAcount.id;
		a.passkey = mailAcount.passkey;
	}
	public boolean ifThis()	//如果收到邮件则返回true，否则返回false
	{
		return true;
	}
}

class SendWb extends TaskGoal	//发送微博类
{
	Acount wbAcount;
	String wbConstant;
	
	
	public SendWb()
	{
		wbAcount = new Acount();
		wbConstant = "";
		super.type = 2;
	}
	public SendWb(Acount a, String wbCst)
	{
		wbAcount = new Acount(a);
		wbConstant = wbCst;
		super.type = 2;
	}
	public String getAcountAndCst(Acount a)
	{
		a.id = wbAcount.id;
		a.passkey = wbAcount.passkey;
		return wbConstant;
	}
	public boolean thenThat()	//如果发送成功则返回true,否则返回false
	{
		return true;
	}
}


class SendMail extends TaskGoal		//发送邮件类
{
	private Acount mailAcount;
	private Acount goalMailAcount;
	private String mailConstant;
	
	public SendMail()
	{
		mailAcount = new Acount();
		mailConstant = "";
		super.type = 3;
	}
	public SendMail(Acount a, Acount b, String mailCst)
	{
		mailAcount = new Acount(a);	
		goalMailAcount = new Acount(b);
		mailConstant = mailCst;
		super.type = 3;
	}
	public String getAcountAndCst(Acount a, Acount b)
	{
		a.id = mailAcount.id;
		a.passkey = mailAcount.passkey;
		b.id = goalMailAcount.id;
		b.passkey = goalMailAcount.passkey;
		return mailConstant;
	}
	public boolean thenThat() 		//如果发送成功则返回true,否则返回false
	{
		return true;
	}
}