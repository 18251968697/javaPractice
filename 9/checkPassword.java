import java.util.Scanner;
public class checkPassword {
	public static void main(String[] args){
		String password;
		System.out.println("Pls enter a password:");
		Scanner input=new Scanner(System.in);
		password=input.nextLine();
		ifValid(password);
	}
	public static void ifValid(String password){
		char []num=new char[password.length()];
		int numberSum=0;
		int abcSum=0;
		for(int i=0;i<password.length();i++){
			num[i]=password.charAt(i);
			if(ifAbc(num[i])==1)
				abcSum++;
			else
				if(ifNumber(num[i])==1)
					numberSum++;
		}
		if((password.length()<8)||(numberSum<2)||(numberSum+abcSum!=password.length()))
			System.out.print("Invalid Password");
		else
			System.out.print("valid Password");
	}
	public static int ifAbc(char abc){
		if((abc<='z' && abc>='a')||(abc<='Z' &&abc>='A'))
			return 1;
		else
			return 0;
	}
	public static int ifNumber(char number){
		if(number<='9' && number>='0')
			return 1;
		else
			return 0;
	}
}
