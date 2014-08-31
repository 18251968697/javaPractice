import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindMax {

	public static Object max(Comparable[] a){
		Comparable max = a[0];
		for(int i=0; i<a.length; i++){
			if(max.compareTo(a[i])<0)
				max = a[i];
		}
		return max;
	}
	
	public static void main(String args[]) throws ParseException{
		String s[] = {"aa", "bb", "xx", "dd", "ee", "ff", "gg", "hh", "ii", "jj"};
		Integer i[] = {11,22,33,44,55,66,77,88,99,100};
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		Date d[] = {sdf.parse("1992-11-22"),sdf.parse("1659-03-31"),sdf.parse("1999-12-31"),
				sdf.parse("1992-06-11"),sdf.parse("1997-07-11"),sdf.parse("1999-11-11")};
		System.out.println("最大的字符串："+max(s));
		System.out.println("最大的整数："+max(i));
		System.out.println("最大的日期："+sdf.format(max(d)));
	}
	
}
