package user;

public class Email {
	private String src, dst, cst;
	private boolean readFlag;
	public Email(){
		src = null;
		dst = null;
		cst = null;
		readFlag = true;
	}
	public Email(String src, String dst, String cst){
		this.src = src;
		this.dst = dst;
		this.cst = cst;
		readFlag = true;
	}
	public Email(Email email){
		src = email.src;
		dst = email.dst;
		cst = email.cst;
		readFlag = true;
	}
	
	public String getSrc(){
		return src;
	}
	public String getDst(){
		return dst;
	}
	public String getCst(){
		return cst;
	}
	public boolean getReadFlag(){
		return readFlag;
	}
	public void setNotRead(){
		readFlag = false;
	}
	public void setRead(){
		readFlag = true;
	}
}
