import java.text.SimpleDateFormat;
import java.util.Date;

public class House implements Cloneable, Comparable{
	private int id;
	private double area;
	public java.util.Date whenBuilt;
	
	public House(int id, double area){
		this.id = id;
		this.area = area;
		whenBuilt = new java.util.Date();
	}
	public int getId(){
		return id;
	}
	public double getArea(){
		return area;
	}
	public java.util.Date getWhenBuilt(){
		return whenBuilt;
	}
	
	protected void setWhenBulit(java.util.Date d){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat();
				whenBuilt = sdf.parse(sdf.format(d));
			}catch(Exception e){e.printStackTrace();}
	}
	public Object clone() throws CloneNotSupportedException{
		House tmp = (House)super.clone();
		tmp.setWhenBulit(tmp.getWhenBuilt());
		return tmp;
	}
	public int compareTo(Object o){
		if(area > ((House)o).area)
			return 1;
		else if(area < ((House)o).area)
			return -1;
		return 0;
	}
	
}
