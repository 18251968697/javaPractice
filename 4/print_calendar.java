import java.util.Scanner;
public class print_calendar {
	public static void main(String []args){
		System.out.println("请输入年份：");
		Scanner input=new Scanner(System.in);
		int year=input.nextInt();
		System.out.println("请输入1月1号是星期几：");
		Scanner data=new Scanner(System.in);
		int week=data.nextInt();
		week=week%7+1;
		boolean isLeapYear=(((year%4==0)&&(year%100!=0))||(year%400==0));
		int i,j,month;
		for(i=1;i<=12;i++){
			if(i==1)
				System.out.print("\t\t\tJanuary "+year);
			else if(i==2)
				System.out.print("\t\t\tFebruary "+year);
			else if(i==3)
				System.out.print("\t\t\tMarch "+year);
			else if(i==4)
				System.out.print("\t\t\tApril "+year);
			else if(i==5)
				System.out.print("\t\t\tMay "+year);
			else if(i==6)
				System.out.print("\t\t\tJune "+year);
			else if(i==7)
				System.out.print("\t\t\tJuly "+year);
			else if(i==8)
				System.out.print("\t\t\tAugust "+year);
			else if(i==9)
				System.out.print("\t\t\tSeptember "+year);
			else if(i==10)
				System.out.print("\t\t\tOctober "+year);
			else if(i==11)
				System.out.print("\t\t\tNovember "+year);
			else if(i==12)
				System.out.print("\t\t\tDecember "+year);
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Sun\tMon\tTue\tWed\tThu\tFri\tSat\n");
			for(j=1;j<week;j++)
				System.out.print("\t");
			j=1;
			if((i==1)||(i==3)||(i==5)||(i==7)||(i==8)||(i==10)||(i==12))
			month=31;
			else
				if(i==2)
					if(isLeapYear)
						month=29;
					else
						month=28;
				else
					month=30;
			for(;j<=month;j++){
			System.out.print(j+"\t");
			week++;
			if(week==8){
				System.out.print("\n");
				week=1;
			}
			}
			System.out.print("\n");
			week=week%7;
		}
	}
}
