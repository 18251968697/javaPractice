import java.math.BigInteger;

public class MaxValue {
	public static void find(){
		int num = 0;
		BigInteger tmp1 = new BigInteger("2");
		BigInteger tmp2 = new BigInteger("1");
		BigInteger tmp3 = new BigInteger("0");
		BigInteger i= new BigInteger("9223372036854775808");
		boolean flag = true;
		for(;num<=5;i=i.add(tmp2)){
			for(BigInteger j=new BigInteger("2"); j.compareTo(i.divide(tmp1))<=0; j=j.add(tmp2))
				if(i.remainder(j).equals(tmp3)){
					flag = false;
					break;
				}
			if(flag){
				num++;
				System.out.println(num+": "+i.toString());
			}
		}
			
	}
	public static void main(String args[]){
		System.out.println("大于Long.MAX_VALUE的五个素数为：");
		find();
	}
}
