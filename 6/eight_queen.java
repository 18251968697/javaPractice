
public class eight_queen {
	public static void main(String[] args){
		queen();
	}
		public static void queen(){
			int [] a=new int [9];  
			int i;
			for(i=1;i<9;i++)
				a[i]=1;
			    int m=1;
			    do
			    {
			        if(ifgood(a,m))
			        {
			            if(m==8)               
			            {
			                print(a);
			                while(a[m]==8)   
			                    m--;     
			                a[m]++; 
			                m=-1;
			            }
			            else                       
			            	a[++m]=1;                             
			        }
			        else
			        {
			            while(a[m]==8)           
			                m--;
			            a[m]++;                  
			        }
			    }while(m!=-1);
			}
		public static boolean ifgood(int[] a,int i){
			int k=1;
			int m,j;
			for(m=1;m<i;m++)
				for(j=m+1;j<=i;j++){
					if((a[m]==a[j])||(a[m]-a[j]==m-j)||(a[m]-a[j]==j-m))
						k=0;
				}
			if(k==1)
				return true;
			else
				return false;
		}
		public static void print(int[] a){
			int i;
			for(i=1;i<9;i++){
				int k=1;
				for(;k<a[i];k++)
					System.out.print("| ");
				System.out.print("|Q");
				for(k++;k<=8;k++)
					System.out.print("| ");
				System.out.print("|\n");
			}
		}
}
