import java.util.Scanner;
public class Guess_sqrt {
	public static void main(String[] args){
	Scanner input=new Scanner(System.in);
	System.out.print("请输入一个数:");
	int data=input.nextInt();
	System.out.println("请输入猜测值:");
	Scanner inpu=new Scanner(System.in);
	double lastGuess=inpu.nextDouble();
	System.out.print("这个数的哦平方根近似是:"+sqrt(data,lastGuess));
	}
	public static double sqrt(int number,double lastGuess){
		double nextGuess=(lastGuess+(number/lastGuess))/2;
		while((nextGuess-lastGuess>0.0001)||(nextGuess-lastGuess<-0.001)){
			lastGuess=nextGuess;
			nextGuess=(lastGuess+(number/lastGuess))/2;
		}
		return nextGuess;
	}
}
