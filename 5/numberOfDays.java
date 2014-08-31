
public class numberOfDays {
	public static int numberOfDaysInYear(int year){
		boolean isLeapYear=(((year%4==0)&&(year%100!=0))||(year%400==0));
		if(isLeapYear)
			return 366;
		else	
			return 365;
	}
	public static void main(String []args){
		int i=2000;
		for(;i<2011;i++)
		System.out.print("第"+i+"年有"+numberOfDaysInYear(i)+"天\n");
	}
}
