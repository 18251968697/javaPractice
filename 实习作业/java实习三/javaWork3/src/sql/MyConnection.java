package sql;
import java.sql.*;
import java.util.LinkedList;

public class MyConnection {
	private static String url="jdbc:sqlserver://localhost;datebase=test;";
	private static String user="sa";
	private static String pw="112804";
	private static int MaxPoolSize = 10; 
	LinkedList<Connection> connPool = new LinkedList<Connection>();
	
	public MyConnection(){
		try{
			for(int i=0; i<MaxPoolSize; i++){
				connPool.addLast(createConn());

			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	private static Connection createConn()throws Exception{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return DriverManager.getConnection(url, user, pw);
	}
	
	public Connection getConn(){
		try{
			synchronized (connPool){
				if(connPool.size()>0)
					return connPool.removeFirst();
				return createConn();
			}
		}catch(Exception e){e.printStackTrace();}
		finally{return null;}	
	}
	
	public void freeConn(Connection c){
		try{
			if(connPool.size()>=MaxPoolSize){
				c.close();
				notifyAll();
			}
			else
				connPool.addLast(c);
		}catch(Exception e){e.printStackTrace();}
		
	}
	
}
