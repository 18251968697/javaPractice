import java.util.Scanner;
public class bubble_sort {
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		double [] data=new double [10];
		System.out.print("Enter ten numbers:");
		int i,j;
		for(i=0;i<10;i++)
			data[i]=input.nextDouble();
		bubble(data);
		System.out.println("The sort numbers are:");
		for(i=0;i<10;i++)
			System.out.print(data[i]+" ");
	}
	public static void bubble(double []data)
	{
		int i,j;
	for(i=0;i<10;i++)
		for(j=i;j<10;j++){
			if(data[i]>data[j]){
				double temp=data[j];
				data[j]=data[i];
				data[i]=temp;
			}
		}
	}
}
