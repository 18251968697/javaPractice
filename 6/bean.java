import java.util.Scanner;
public class bean {
	public static void main(String []args){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter the number of balls to drop:");
		int number=input.nextInt();
		System.out.print("Enter thhe number of slots in the bean machine:");
		int slot=input.nextInt();
		int []x=new int [slot];
		int []y=new int [number];
		int i=0,j;
		for(;i<number;i++){
			y[i]=(slot+1)/2;
			for(j=0;j<slot;j++){
				x[j]=(int)(Math.random()*10)/5;
				if(x[j]==0)
				{
					System.out.print("L");
					if(y[i]>0)
					y[i]--;
				}
				else
				{
					System.out.print("R");
					if(y[i]<slot)
					y[i]++;
				}
			}
			System.out.print("\n");
		}
		int []z=new int [slot];
		for(i=0;i<slot;i++){
			z[i]=0;
		}
		for(i=0;i<number;i++){
			z[y[i]]++;
		}
		int max=0;
		for(i=0;i<slot;i++){
			if(max<z[i])
				max=z[i];
		}
		while(max!=0)
		{
			print(z,slot);
			for(i=0;i<slot;i++)
			{
				if(z[i]!=0)
					z[i]--;
			}
			max--;
		}
	}
	public static void print(int []z,int slot){
		int i;
		for(i=0;i<slot;i++)
		{
			if(z[i]!=0)
				System.out.print("0");
			else
				System.out.print(" ");
		}
		System.out.print("\n");
	}
}
