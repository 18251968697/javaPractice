
public class Circle2D {
	private double x;
	private double y;
	private double radius;
	public Circle2D(){
		this.x=this.y=0;
		this.radius=1;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getRadius(){
		return radius;
	}
	public Circle2D(double x,double y,double radius){
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	public double getArea(){
		return Math.PI*this.radius*this.radius;
	}
	public double getPerimeter(){
		return Math.PI*2*this.radius;
	}
	public double far(double x,double y){
		return Math.pow((this.x-x),2)+Math.pow((this.y-y),2);
	}
	public String contains(double x,double y){
		if(far(x,y)<Math.pow(this.radius,2))
			return "true";
		else
			return "false";
	}
	public String contains(Circle2D circle){
		if((far(circle.getX(),circle.getY())<Math.pow((this.radius-circle.getRadius()), 2))&&(this.radius>circle.getRadius()))
			return "true";
		else
			return "false";
	}
	public String overlaps(Circle2D circle){
		if((far(circle.getX(),circle.getY())>Math.pow((this.radius-circle.getRadius()), 2))&&(far(circle.getX(),circle.getY())<Math.pow((this.radius+circle.getRadius()), 2)))
			return "true";
		else
			return "false";
	}
	public static void main(String[] args){
		Circle2D c1=new Circle2D(2,2,5.5);
		System.out.println("周长是"+c1.getPerimeter());
		System.out.println("面积是"+c1.getArea());
		System.out.println(c1.contains(3,3));
		System.out.println(c1.contains(new Circle2D(4,5,10.5)));
		System.out.println(c1.overlaps(new Circle2D(3,5,2.3)));
	}
}
