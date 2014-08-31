package task;

import java.io.StringReader;

public class Translate {	//该类为各个任务所需信息与一个整字符串间的转换
	
	public static myDate StringToMyDate(String str){   
		if(!str.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")){
			System.out.println("[Error]: Error date");
			return null;
		}
		return new myDate(str.substring(0,4), str.substring(5,7), str.substring(8, 10));
	}
	public static Time StringToTime(String str){
		if(!str.matches("[0-9]{2}:[0-9]{2}:[0-9]{2}")){
			System.out.println("[Error]: Error time");
			return null;
		}
		return new Time(str.substring(0,2), str.substring(3, 5), str.substring(6,8));
	}
	public static String OrderTimeToString(String date, String time){
		myDate md = Translate.StringToMyDate(date);
		Time t = Translate.StringToTime(time);
		return md.year+'\n'+md.month+'\n'+md.day+'\n'+t.hour+'\n'+t.minute+'\n'+t.seconde;
	}
	public static String RecvMailToString(Acount a){
		return a.id+'\n'+a.passkey;
	};
	public static String SendWbToString(Acount a, String cst){
		return a.id+'\n'+a.passkey+'\n'+cst;
	}
	public static String SendMailToString(Acount a, String toAddr, String title, String cst){
		return a.id+'\n'+a.passkey+'\n'+toAddr+'\n'+title+'\n'+cst;
	}
	
	public static String[] getLines(String str){
		if(str.length() <= 0)
			return null;
		String tmp[] = new String[20];
		int num=0;
		while(str.length() > 0){
			int index=str.indexOf('\n');
			if(index < 0){
				tmp[num] = str;
				num++;
				str = "";
				continue;
			}
			tmp[num] = str.substring(0, index);
			num++;
			str = str.substring(index+1, str.length());
		}
		String ret[] = new String[num];
		for(int i=0; i<num; i++)
			ret[i] = tmp[i];
		return ret;
	}
	public static void StringToOrderTime(String str, myDate md[], Time t[]){
		String ret[] = getLines(str);
		md[0] = new myDate(ret[0], ret[1], ret[2]);
		t[0] = new Time(ret[3], ret[4], ret[5]);		
	}
	public static void StringToRecvMail(String str, Acount a[]){
		String ret[] = getLines(str);
		a[0] = new Acount(ret[0], ret[1]);
	}
	public static String StringToSendWb(String str, Acount a[]){
		String ret[] = getLines(str);
		a[0] = new Acount(ret[0], ret[1]);
		return ret[2];
	}
	public static String[] StringToSendMail(String str, Acount a[]){
		String ret[] = getLines(str);
		a[0]=new Acount(ret[0], ret[1]);
		String mailInfo[] = new String[3];
		mailInfo[0] = ret[2];
		mailInfo[1] = ret[3];
		mailInfo[2] = ret[4];
		return mailInfo;
	}
}
