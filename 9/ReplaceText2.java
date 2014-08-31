import java.io.*;
import java.util.*;
public class ReplaceText2 {
	public static void main(String []args) throws Exception{
		if(args.length!=3){
			System.out.println("Usage:java RepalceText sourceFile targetFile oldStr newStr");
			System.exit(0);
		}
	File sourceFile=new File(args[0]);
	File targetFile=new File("args_1654312.txt");
	Scanner input =new Scanner(sourceFile);
	PrintWriter output=new PrintWriter(targetFile);
	while(input.hasNext()){
		String s1=input.nextLine();
		String s2=s1.replaceAll(args[1], args[2]);
		output.println(s2);
	}
	input.close();
	output.close();
	sourceFile.delete();
	targetFile.renameTo(new File(args[0]));
	}
}
