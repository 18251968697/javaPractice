import java.util.Scanner;
public class ifAnagram {
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		String s1,s2;
		System.out.println("Pls enter two words:");
		s1=input.nextLine();
		s2=input.nextLine();
		if(isAnagram(s1,s2))
			System.out.println("anagram");
		else
			System.out.println("not anagram");
	}
	public static boolean isAnagram(String s1,String s2){
		char[] x1=new char [s1.length()];
		char[] x2=new char [s2.length()];
		if(s1.length()!=s2.length())
			return false;
		int []a=new int[26];
		int []b=new int[26];
		for(int j=0;j<26;j++)
			a[j]=b[j]=0;
		for(int i=0;i<s1.length();i++){
			x1[i]=s1.charAt(i);
			x2[i]=s2.charAt(i);
		//	a[char2number(x1[i])]++;
		//	b[char2number(x2[i])]++;
		}
		for(int j=0;j<26;j++){
			if(a[j]!=b[j])
				return false;
		}
		return true;
	}
	public static int char2number(char x){
		if(x>'a' && x<'z')
			return (int)(x-'a');
		else
			return (int)(x-'A');
		
	}
}
