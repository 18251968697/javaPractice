
public class print_number {
	public static void main(String[] args){
		int i,j,m;
		for(i=1;i<=8;i++)
		{
			for(j=1;j<=(8-i)*8;j++)
				System.out.print(" ");//´òÓ¡¿Õ¸ñ
			j=1;
			for(m=1;m<=i;j=j*2){
				System.out.print(j+"\t");
				m++;
			}
			for(j=j/4;m>1;j=j/2)
			{
				if(j!=0)
				System.out.print(j+"\t");
				m--;
			}
			System.out.print("\n");
		}
	}
}
