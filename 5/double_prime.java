
public class double_prime {
	public static void main(String []args){
		int i;
		for(i=2;i<1000;i++){
			if(isprime(i)&&(isprime(i+2)))
				System.out.print("("+i+","+(i+2)+")"+"  ");
		}
	}
	public static boolean isprime(int number){
		int sqrt=(int)(Math.sqrt((double)number));
		int i;
		int k=1;
		for(i=2;i<=sqrt;i++){
			if(number%i==0)
				k=0;
		}
		if (k==0)
			return false;
		else
			return true;
	}
}
