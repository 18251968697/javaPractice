package user;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction{
	private Date date;
	private char type;
	private double amount, balance;
	private String description;

	public Transaction(char type, double amount, double balance, String description){
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.description = description;
		date = new Date();
	}
	public char getType(){
		return type;
	}
	public double getAmount(){
		return amount;
	}
	public double getBalance(){
		return balance;
	}
	public String getDescription(){
		return description;
	}

	public String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return sdf.format(new Date());
	}
}

