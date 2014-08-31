import java.util.Scanner;
public class Sum_of_number {
	public static void main(String []args){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter a number(0~1000)");
		int x=input.nextInt();
		int y=x%10+(x/10)%10+x/100;
		System.out.println("Sum of each number: "+y);
	}
}
