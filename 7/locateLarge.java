import java.util.Scanner;
public class locateLarge {
	public static void main(String []args){
	Scanner input=new Scanner(System.in);
	System.out.print("Enter the number of rows and columns of the array:");
	int row=input.nextInt();
	int col=input.nextInt();
	int i,j;
	System.out.print("Enter the array:\n");
	double [][]array=new double [row][col];
	for(i=0;i<row;i++)
		for(j=0;j<col;j++)
			array[i][j]=input.nextDouble();
	print(array,row,col);
	}
	public static void print(double[][]array,int row,int col){
		double max=array[0][0];
		int i,j;
		System.out.print("The location of the largest element is at ");
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				if(max<array[i][j])
					max=array[i][j];
		for(i=0;i<row;i++)
			for(j=0;j<col;j++)
				if(max==array[i][j])
					System.out.print("("+i+","+j+")\n");
	}
}
