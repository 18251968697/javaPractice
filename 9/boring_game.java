import java.util.Scanner;
public class boring_game {
	public static void main(String []args){
		String[] words={"write","that","key","first","number","congratulations","java",
				"source","old","new","target","replace","boring","game"};
		char input='1';
		Scanner in=new Scanner(System.in);
		while(input!='n'){
			String word=words[(int)(Math.random()*14)];
			String d="";
			int count=0;
			int length=word.length();
			System.out.print("(Guess)Enter a letter in word ");
			for(int i=count;i<length;i++){
				d+="*";
				System.out.print("*");
			}
			System.out.print('>');
			d+=">";
			boolean right=false;
			boolean left=false;
			while(count<length){
				int x=0;
				right=left=false;
			input=in.next().charAt(0);
			for(int i=0;i<count-1;i++){
				if(input==word.charAt(i))
					left=true;
			}
			for(int i=count;i<length;i++){
			if(input==word.charAt(i)){
				right=true;
				if(i==length-1)
				{
					System.out.print("The word is "+word);
					if(count!=length-1)
						System.out.print("\nYou missed "+(length-count)+" time");
					x=1;
					break;
				}
				String f=d;
				d="";
				for(int j=0;j<i;j++)
				d+=f.charAt(j);
			d+=input;
			for(int j=i+1;j<length;j++)
				d+="*";
			d+='>';
			break;
			}
		}
			if((right==false)&&(left==false))
				System.out.println(input+" is not in the word");
			else if((right==false)&&(left==true))
				System.out.println(input+" is already in the word");
			else
				count++;
			if(x!=1){
			System.out.print("(Guess)Enter a letter in word ");
			System.out.print(d);
			}
			else
				break;
			}
			System.out.println("\nDo you want to guess for another word?Enter y or n");
			input=in.next().charAt(0);
			}
		}
}
