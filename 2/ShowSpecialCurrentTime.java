
import java.util.Scanner;
public class ShowSpecialCurrentTime {
public static void main(String []args){
	Scanner input=new Scanner(System.in);
	System.out.print("Enter the time zone offset to GMT: ");
	byte offset=input.nextByte();
	long totalMilliseconds=System.currentTimeMillis();
	long totalSeconds=totalMilliseconds/1000;
	long totalMinutes=totalSeconds/60;
	long totalHours=totalMinutes/60;
	long currentSecond=totalSeconds%60;
	long currentMinute=totalMinutes%60;
	long currentHour=totalHours%24;
	currentHour+=offset;
	System.out.print("Time is ");
	if(currentHour<0)
	{
		currentHour+=24;
		System.out.print("(yesterday)");
	}
	if(currentHour>24)
	{
		currentHour-=24;
		System.out.print("(nextday)");
	}
	System.out.println(currentHour+":"+currentMinute+":"
+currentSecond);}

}
