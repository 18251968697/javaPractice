
public class Course {
	private String courseName;
	private String[] students =new String[100];
	private int numberOfStudents;
	public Course(String courseName){
		this.courseName=courseName;
	}
	public void addStudent(String student){
		if(numberOfStudents==students.length){
			String[] newArray=new String[2*students.length];
			for(int i=0;i<students.length;i++)
				newArray[i]=students[i];
			students=new String[newArray.length];
			for(int i=0;i<students.length;i++)
				students[i]=newArray[i];
		}
		students[numberOfStudents]=student;
		numberOfStudents++;
	}
	public String[] getStudents(){
		return students;
	}
	public int getNumberOfStudents(){
		return numberOfStudents;
	}
	public String getCourseName(){
		return courseName;
	}
	public void dropStudent(String student){
		for(int i=0;i<students.length;i++)
			if(students[i]==student){
				for(int j=i;j<students.length-1;j++)
					students[j]=students[j+1];
				numberOfStudents--;
			}
	}
	public void clear(){
		this.students=null;
	}
	public static void main(String[] args){
		Course course=new Course("max");
		course.addStudent("T");
		course.addStudent("P");
		course.addStudent("R");
		course.dropStudent("P");
		String[] a=new String[3];
		a=course.getStudents();
		for(int i=0;i<course.getNumberOfStudents();i++)
			System.out.print(a[i]+",");
	}
}
