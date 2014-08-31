package user;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
	public static final int ADMINISTER = 1993, USER = 1221;
	private String id, password;
	private int type;
	private double balance;
	private int vip, score;
	private EmailBox inbox;
	private ArrayList transactions;
	private Date createDate;
	private String sex;
	private int age;
	private String phoneNumber, email;

	
	public User(String id, String password, int type){
		if(type != ADMINISTER && type != USER){
			System.out.println("error! in create new user");
			return;
		}
		this.id = id;
		this.password = password;
		this.type = type;
		balance = 0;
		vip = 0;
		score = 0;
		inbox = new EmailBox();
		transactions = new ArrayList();
		createDate = new Date();
	}
	public boolean setSex(String sex){
		if(!sex.matches("^f|m$"))
			return false;
		this.sex = sex;
		return true;
	}
	public boolean setAge(int age){
		if(age<0)
			return false;
		this.age = age;
		return true;
	}
	public boolean setPhoneNumber(String pn){
		phoneNumber = pn;
		return true;
	}
	public boolean setEmail(String email){
		this.email = email;
		return true;
	}
	protected void setVip(){
		if(score <= 100)
			vip = 0;
		else if(score <= 500)
			vip = 1;
		else if(score <= 1000)
			vip = 2;
		else if(score <= 2000)
			vip = 3;
		else if(score <= 5000)
			vip = 4;
		else if(score <= 10000)
			vip = 5;
		else if(score <= 20000)
			vip = 6;
		else if(score <= 50000)
			vip = 7;
		else if(score <= 100000)
			vip = 8;
		else if(score <= 200000)
			vip = 9;
		else
			vip = 10;
	}
	
	public String getId(){
		return id;
	}
	public String getPassword(){
		return password;
	}
	public double getBalance(){
		return balance;
	}
	public int getVip(){
		return vip;
	}
	public int getScore(){
		return score;
	}
	public int getLetterNumber(){
		return inbox.getLetterNum();
	}
	public Email getEmail(int i){
		return inbox.getEmail(i);
	}
	public Email[] getAllEmail(){
		return inbox.getAllEmail();
	}
	public int getMessageNumber(){
		return inbox.getMessageNum();
	}
	public Email getMessage(int i){
		return inbox.getMessage(i);
	}
	public Email[] getAllMessage(){
		return inbox.getAllMessage();
	}
	public String getCreateDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		return sdf.format(createDate);
	}
	public String getSex(){
		return sex;
	}
	public int getAge(){
		return age;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	public String getEmail(){
		return email;
	}
	public int getTransactionsNum(){
		return transactions.size();
	}
	public Transaction getTransaction(int index){
		return (Transaction)transactions.get(index);
	}
	public int getType(){
		return type;
	}
	
	public void recharge(double money){
		balance += money;
		transactions.add(new Transaction('D', money, balance, "³äÖµ"));
		score += money * 10;
		setVip();
	}
	public void consume(double money){
		balance -= money;
		transactions.add(new Transaction('W', money, balance, "Ïû·Ñ"));
	}
	public boolean receiveMail(Email email){
		return inbox.addEmail(email);
	}
	public boolean sendMail(User dst, Email email){
		return dst.receiveMail(email);
	}
	
}
