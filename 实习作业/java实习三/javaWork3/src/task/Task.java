package task;

import java.util.Timer;

import java.util.TimerTask;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JTextArea;


public class Task	//�����࣬�����������������
{
	public String taskName=null;
	public SubTask ifTask=null, thenTask=null;
	
	public final static String READY="ready", RUNNING="running", SUSPEND="suspend", END="end", CONTINUE="continue";
}




abstract class IfTypeTask extends SubTask	 	//ifThis������ӿ�
{
	public abstract boolean ifThis();
}

abstract class ThenTypeTask extends SubTask		//thenThat������ӿ�
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

class Acount	//�˺Žṹ
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


class OrderTime extends IfTypeTask		//��ʱ������
{
	private myDate date;	//����
	private Time time;		//ʱ��
	
	public OrderTime(myDate d, Time t)
	{
		description += "��ʼ����ʱ����...\n";
		date = new myDate(d);
		time = new Time(t);
		super.type = SubTask.ORDERTIME;
		description += "��ʼ�����.\n";
	}

	public boolean  ifThis()	//���趨������ʱ���뵱ǰϵͳʱ��Ƚϣ����ʱ�䵽�򷵻�true�����򷵻�false
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
		description += "��ʱ��ʼ...\n";
	}
	public void taskSuccess(){
		description += "ʱ�䵽.\n";
	}
	public void taskFailed(){
		description += "��ʱʧ��.\n";
	}
	public void taskSuspend(){
		description += "��ʱ������ֹ.\n";
	}
	public void taskEnd(){
		description += "��ʱ�������.\n";
	}
}

class RecvMail extends IfTypeTask		//��ȡ�ʼ���
{
	private Acount mailAcount;		//�˺�

	public RecvMail(Acount a)
	{
		description += "��ʼ�������ʼ�����...\n";
		mailAcount = new Acount(a);
		super.type = SubTask.RECVMAIL;
		description += "��ʼ�����.\n";
	}
	public boolean ifThis()	//����յ��ʼ��򷵻�true�����򷵻�false
	{
		try
		{
			return GmailRecv.recvMail(mailAcount.id, mailAcount.passkey);
		}catch(Exception e){e.printStackTrace();return false;}		
		
	}
	public void taskStart(){
		description += "�����ʼ���ʼ...\n";
	}
	public void taskSuccess(){
		description += "�յ����ʼ�.\n";
	}
	public void taskFailed(){
		description += "δ�յ����ʼ�.\n";
	}
	public void taskSuspend(){
		description += "�����ʼ�������ֹ.\n";
	}
	public void taskEnd(){
		description += "�����ʼ��������.\n";
	}
}

class SendMail extends ThenTypeTask		//�����ʼ���
{
	private Acount mailAcount;
	private String toAddr;
	private String title;
	private String cst;
	
	public SendMail(Acount a, String toAddr, String title,String cst)
	{
		description += "��ʼ�����ʼ�����...\n";
		mailAcount = new Acount(a);	
		this.toAddr = toAddr;
		this.title = title;
		this.cst = cst;
		super.type = SubTask.SENDMAIL;
		description += "��ʼ�����.\n";
	}

	public boolean thenThat() 		//������ͳɹ��򷵻�true,���򷵻�false
	{
		try{	
			return GmailSend.sendMail(mailAcount.id, mailAcount.passkey, title, cst, toAddr);
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public void taskStart(){
		description += "��ʼ�����ʼ�...\n";
	}
	public void taskSuccess(){
		description += "���ͳɹ�.\n";
	}
	public void taskFailed(){
		description += "����ʧ��.\n";
	}
	public void taskSuspend(){
		description += "����������ֹ.\n";
	}
	public void taskEnd(){
		description += "�����������.\n";
	}
	
}


