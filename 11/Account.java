import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class Account {
	private int id;
	private ArrayList transactions=new ArrayList();
	private String name;
	private double balance;
	private double annualInterestRate;
	private Date dateCreated;
	public Account(){
		id=0;
		balance=0;
		annualInterestRate=0;
		name="0";
		dateCreated=new Date();
		transactions.clear();
	}
	public Account(int id,double balance){
		this.id=id;
		this.dateCreated=new Date();
		this.balance=balance;
	}
	public Account(String name,int id,double balance){
		this.name=name;
		this.id=id;
		this.balance=balance;
		this.dateCreated=new Date();
	}
	public String getName(){
		return name;
	}
	public void changeName(String name){
		this.name=name;
	}
	public int getId(){
		return id;
	}
	public void changeId(int id){
		this.id=id;
	}
	public double getBalance(){
		return balance;
	}
	public void changeBalance(int balance){
		this.balance=balance;
	}
	public double getAnnualInterestRate(){
		return annualInterestRate;
	}
	public void changeAnnualInterestRate(double annualInterestRate){
		this.annualInterestRate=annualInterestRate;
	}
	public Date getDateCreated(){
		return dateCreated;
	}
	public double getMonthlyInterestRate(){
		return annualInterestRate/12;
	}
	public void withDraw(double balance){
		this.balance-=balance;
		transactions.add(this.dateCreated);
		transactions.add('W');
		transactions.add(balance);
		transactions.add(this.balance);
		transactions.add("取款完毕");
	}
	public void deposit(double balance){
		this.balance+=balance;
		transactions.add(this.dateCreated);
		transactions.add('D');
		transactions.add(balance);
		transactions.add(this.balance);
		transactions.add("存款完毕");
	}
	public void print(){
		System.out.println("Name:"+this.name);
		System.out.println("MonthlyInterestRate:"+this.getMonthlyInterestRate());
		System.out.println("Balance:"+this.balance);
		System.out.println("All trade:");
		int k=1;
		for(int i=0;i<=this.transactions.size()-1;i++){
			System.out.println(k+":");
			System.out.println("time:"+transactions.get(i));
			i++;
			System.out.println("type:"+transactions.get(i));
			i++;
			System.out.println("balance:"+transactions.get(i));
			i++;
			System.out.println("current balance:"+transactions.get(i));
			i++;
			k++;
		}
		
	}
	public static void main(String[] args){
		Account a=new Account("George",1122,1000);
		a.changeAnnualInterestRate(0.015);
		a.deposit(30);
		a.deposit(40);
		a.deposit(50);
		a.withDraw(5);
		a.withDraw(4);
		a.withDraw(2);
		a.print();
	}
}
