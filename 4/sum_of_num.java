
public class sum_of_num {
	public static void main(String []args){
		final int NUMBER=50000;
		double i;
		double sum1=0;
		double sum2=0;
		for(i=1;i<=NUMBER;i++){
			sum1+=1/i;
		}
		for(i=NUMBER;i>=1;i--){
			sum2+=1/i;
		}
		System.out.print("从左向右相加得"+sum1+"\n"+"从右向左相加得"+sum2);
	}
}
