
public class employee {
	public static void main(String []args){
		int [][]number={
				{2,4,3,4,5,8,8},
				{7,3,4,3,3,4,4},
				{3,3,4,3,3,2,2},
				{9,3,4,7,3,4,1},
				{3,5,4,3,6,3,8},
				{3,4,4,6,3,4,4},
				{3,7,4,8,3,8,4},
				{6,3,5,9,2,7,9}
		};
		int []sum=new int[8];
		int i,j;
		for(i=0;i<8;i++){
			for(j=0;j<7;j++)
			sum[i]+=number[i][j];
		}
		int []cash={0,1,2,3,4,5,6,7};
		bubble(sum,cash);
		for(i=7;i>=0;i--){
		System.out.print("Employee"+cash[i]+":"+sum[i]+"\n");
		}
	}
	public static void bubble(int []data,int []cash)
	{
		int i,j;
	for(i=0;i<8;i++)
		for(j=i;j<8;j++){
			if(data[i]>data[j]){
				int temp=data[j];
				data[j]=data[i];
				data[i]=temp;
				temp=cash[j];
				cash[j]=cash[i];
				cash[i]=temp;
			}
		}
	}
}
