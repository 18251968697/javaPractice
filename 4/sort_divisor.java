import java.util.Scanner;
public class sort_divisor {
	public static void main(String []args){
		Scanner input=new Scanner(System.in);
		System.out.println("Enter a number");
		int put=input.nextInt();
		int divisor=2;
		while(put!=1){
			if(put%divisor==0)
			{
				put=put/divisor;
				System.out.print(divisor+",");
			}
			else
				divisor++;
		}
	}
}
