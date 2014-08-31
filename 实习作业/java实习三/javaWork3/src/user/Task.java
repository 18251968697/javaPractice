package user;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JTextArea;

public class Task	//�����࣬�����������������
{
	private String taskName;	//������
	protected int type;		//��������
	private JTextArea jta = null;	//�����������
	public Task()
	{
		taskName = "";
		type = -1;
	}
	
	public void rename(String n)	//����������
	{
		taskName = n;
	}
	public String getName()		//��ȡ������
	{
		return taskName;
	}
	public int getTaskType()	//��ȡ��������
	{
		return type;
	}
	public void getJta(JTextArea jta)	//��ȡ���������
	{
		this.jta = jta;
	}
	public void printRes(String str)	//��ӡ������
	{
		String tmp = jta.getText();
		if(!tmp.equals(""))
			tmp += "\n"+str;
		else
			tmp = str;
		jta.setText(tmp);
	}
	
}

class TaskQueue		//���������
{
    Task task[];	//��������
	int top;	//����ͷ
	
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

abstract class TaskRequest extends Task	 	//ifThis������ӿ�
{
	public abstract boolean ifThis();

}

abstract class TaskGoal extends Task		//thenThat������ӿ�
{
	public abstract boolean thenThat();
}

class myDate	//���ڽṹ
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

class Time		//ʱ��ṹ
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

class Acount	//�˺Žṹ
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


class OrderTime extends TaskRequest		//��ʱ������
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
	public boolean  ifThis()	//�������ӣ����ʱ�䵽�򷵻�true
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
		printRes("��������...");
		myTimerTask myTT = new myTimerTask(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date tmp = null;
		try
		{
			tmp = df.parse(date.year+"-"+date.month+"-"+date.day+" "
					+time.hour+":"+time.minute+":"+time.seconde);
			timer.schedule(myTT, tmp);

		}catch(ParseException e){}
		printRes("�ȴ�...");
		try{
			while(true)
			{
				Thread.sleep(1000);
			if(myTT.getReady() == true)
			{
				printRes("ʱ�䵽��ִ������...");
				break;
			}
		}}
		catch(Exception e){return false;}
	
		return true;
	}

}

class RecvMail extends TaskRequest		//��ȡ�ʼ���
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
	public boolean ifThis()	//����յ��ʼ��򷵻�true�����򷵻�false
	{
		return true;
	}
}

class SendWb extends TaskGoal	//����΢����
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
	public boolean thenThat()	//������ͳɹ��򷵻�true,���򷵻�false
	{
		return true;
	}
}


class SendMail extends TaskGoal		//�����ʼ���
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
	public boolean thenThat() 		//������ͳɹ��򷵻�true,���򷵻�false
	{
		return true;
	}
}