import java.util.Scanner;
public class sum_of_days {
public static void main(String []args){
	Scanner input=new Scanner(System.in);
	System.out.print("Enter the month:");
	byte month=input.nextByte();
	System.out.print("Enter the year:");
	int year=input.nextInt();
	boolean isLeapYear=(((year%4==0)&&(year%100!=0))||(year%400==0));
	if(month==1)
		System.out.print("January "+year+" has 31 days");
	else if(month==2)
		if(isLeapYear)
		System.out.print("February "+year+" has 29 days");
		else
		System.out.print("February "+year+" has 28 days");
	else if(month==3)
		System.out.print("March "+year+" has 31 days");
	else if(month==4)
		System.out.print("April "+year+" has 30 days");
	else if(month==5)
		System.out.print("May "+year+" has 31 days");
	else if(month==6)
		System.out.print("June "+year+" has 30 days");
	else if(month==7)
		System.out.print("July "+year+" has 31 days");
	else if(month==8)
		System.out.print("August "+year+" has 31 days");
	else if(month==9)
		System.out.print("September "+year+" has 30 days");
	else if(month==10)
		System.out.print("October "+year+" has 31 days");
	else if(month==11)
		System.out.print("November "+year+" has 30 days");
	else if(month==12)
		System.out.print("December "+year+" has 31 days");
}
}
