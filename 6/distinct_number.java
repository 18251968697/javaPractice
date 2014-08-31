import java.util.Scanner;
public class distinct_number {
	public static void main(String[] args){
		int[] data=new int [10];
		int[] tip=new int [10];  //标志位，若data数组里的数与之前的数重复，标志位置1
		int[] output=new int [10];
		Scanner input=new Scanner(System.in);
		System.out.print("Enter ten numbers:");
		int i,j,k;
		int count=10;
		for(i=0;i<10;i++)
		{
			tip[i]=0;
			data[i]=input.nextInt();
		}
			for(i=0;i<10;i++)
			for(j=i+1;j<10;j++)
			{
				if(data[i]==data[j])
				{
				count--;
				tip[j]=1;
				break;
				}
			}
		System.out.println("The distinct numbers are:");
		j=0;
		for(i=0;i<10;i++)
			if(tip[i]==0)
			output[j++]=data[i];	
		for(i=0;i<count;i++)
		System.out.print(output[i]+" ");
	}
}
