package sql;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

import task.Task;

public class Sql {
	private static Semaphore smq= new Semaphore(1);
	private Connection dbConn=null;
	public Statement st=null;
	public Sql(){
		try {
			String url="jdbc:sqlserver://localhost;datebase=test;";
			String user="sa";
			String pw="112804";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			dbConn = DriverManager.getConnection(url, user, pw);
			st = dbConn.createStatement();
		} catch (Exception e) {e.printStackTrace();}
	}
		
	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表Adminiter操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean queryAdminiter(String userId, String pw){
		try{
			ResultSet rt = st.executeQuery("select * from administer where id='"+userId+"'");
			if(!rt.next())
				return false;
			if(rt.getString(2).trim().equals(pw))
				return true;
			else
				return false;
		}catch(Exception e){e.printStackTrace();return false;}
	}

	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表myuser操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean addNewUser(String userId, String pw){
		try{
			st.executeUpdate("insert into myuser values('"+userId+"','"+pw+"');");
			st.executeUpdate("insert into vip values('"+userId+"', 0, 0, 0, 0);");
			addTranselation(userId, "w", 0, 0, "创建账户");
		}catch(Exception e){e.printStackTrace(); return false;}
		return true;
	}
	
	public boolean alterPassword(String userId, String pw){
		try{
			if(st.executeUpdate("upate myuser set pw='"+pw+"' where id='"+userId+"'")==0)
				return false;
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public boolean queryUser(String userId, String pw){
		try{
			ResultSet rt = st.executeQuery("select * from myuser where id='"+userId+"'");
			if(!rt.next())
				return false;
			if(rt.getString(2).trim().equals(pw))
				return true;
			else
				return false;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public boolean deleteUser(String userId){
		try{
			if(st.executeUpdate("delete from myuser where id='"+userId+"'")==0)
				return false;
			st.executeUpdate("delete from vip where id='"+userId+"'");
			st.executeUpdate("delete from task where id='"+userId+"'");
			st.executeUpdate("delete from info where id='"+userId+"'");
			st.executeUpdate("delete from transelation where id='"+userId+"'");
			st.executeUpdate("delete from mail where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace();}
		return true;
	}

	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表info操作函数-----------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean addUserInfo(String userId, String sex, int age, String mail){
		try{
			st.executeUpdate("insert into info values('"+userId+"','"+sex+"', "+Integer.toString(age)+",'"+mail+"');");
		}catch(Exception e){e.printStackTrace(); return false;}
		return true;
	}
	
	public boolean updateUserInfo(String userId, String sex, int age, String mail){
		try{
			if(st.executeUpdate("delete from info where id='"+userId+"'")==0)
				return false;
			addUserInfo(userId, sex, age, mail);
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public ResultSet queryInfo(String userId){
		try{
			return st.executeQuery("select * from info where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace(); return null;}
	}

	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表vip操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean updateVip(String userId, double balance, int level, int score, double discount){
		try{
			if(st.executeUpdate("delete from vip where id='"+userId+"'")==0)
				return false;
			st.executeUpdate("insert into vip values('"+userId+"', "+Double.toString(balance)+",  "+Integer.toString(level)+",  "+Integer.toString(score)+", "+Double.toString(discount)+")");
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public ResultSet queryAllVipInfo(){
		try{
			return st.executeQuery("select * from myuser, vip where myuser.id=vip.id");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	
	public ResultSet queryVip(String userId){
		try{
			return st.executeQuery("select * from vip where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	
	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表task操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean addTask(String userId, String taskName, int ifThisType, 
			String ifThis, int thenThatType, String thenThat, String description){
		try{
			st.executeUpdate("insert into task values('"+userId+"','"+taskName+"' , "+
					Integer.toString(ifThisType)+",'"+ifThis+"', "+Integer.toString(thenThatType)+",'"+thenThat+"', '"
					+Task.READY+"', '"+description+"', '"+Task.READY+"');");
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public boolean updateTaskState(String userId, String taskName, String state){
		try{
			if(st.executeUpdate("update task set state='"+state+"' where id='"+userId+"' and taskName='"+taskName+"'")==0)
				return false;
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public boolean updateTaskLastState(String userId, String taskName, String state){
		try{
			if(st.executeUpdate("update task set lastState='"+state+"' where id='"+userId+"' and taskName='"+taskName+"'")==0)
				return false;
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public boolean updateTaskDescription(String userId, String taskName, String description){
		try{
			if(st.executeUpdate("update task set description='"+description+"' where id='"+userId+"' and taskName='"+taskName+"'")==0)
				return false;
			st.close();
			st=dbConn.createStatement();
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public ResultSet queryTask(String userId, String tskName){
		try{
			return st.executeQuery("select * from task where id='"+userId+"' and taskName='"+tskName+"'");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	
	public ResultSet queryTask(String userId){
		try{
			return st.executeQuery("select * from task where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	
	public ResultSet queryAllTask(){
		try{
			return st.executeQuery("select * from task");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	public boolean deleteTask(String userId, String tskName){
		try{
			st.executeUpdate("delete from task where id='"+userId+"' and taskName='"+tskName+"'");
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表transelation操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean addTranselation(String userId, String type, double amount, double balance, String description){
		try{
			ResultSet rs = st.executeQuery("select * from counter where type='trans'");
			rs.next();
			int numOfTrans = rs.getInt(2)+1;
			String orderId = "YZ: "+Integer.toString(numOfTrans);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			st.executeUpdate("insert into transelation values('"+userId+"', '"+
					orderId+"', '"+type+"', "+Double.toString(amount)+", "
					+Double.toString(balance)+", '"+description+"', '"+date+"')");
			st.executeUpdate("update counter set num="+Integer.toString(numOfTrans)+"where type='trans'");
		}catch(Exception e){e.printStackTrace(); return false;}
		return true;
	}

	public ResultSet queryTranselate(String userId){
		try{
			return st.executeQuery("select * from transelation where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	
	public boolean constume(String vipId, float amount, String desc){
		try{
			ResultSet rs = st.executeQuery("select * from vip where id='"+vipId+"'");
			if(rs.next()){
				float balance = rs.getFloat(2);
				balance -= amount;
				st.executeUpdate("update vip set balance="+balance+" where id='"+vipId+"'");
				addTranselation(vipId, "c", amount, balance, desc);
				return true;
			}
			return false;
		}catch(Exception e){e.printStackTrace();return false;}
		
	}
	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表post操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/	
	public boolean sendPost(String title, String cst){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new java.util.Date());
			ResultSet rs=st.executeQuery("select * from counter where type='post'");
			rs.next();
			int postId = rs.getInt(2)+1;
			st.executeUpdate("insert into post values("+Integer.toString(postId)+", '"+title+"','"+cst+"', '"+date+"')");
			st.executeUpdate("update counter set num="+Integer.toString(postId)+" where type='post'");
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public boolean alterPost(int id, String title, String cst, String date){
		try{
			st.executeUpdate("delete from post where postId="+Integer.toString(id));
			st.executeUpdate("insert into post values("+Integer.toString(id)+" , '"+title+"', '"+cst+"', '"+date+"')");
			return true;
		}catch(Exception e){e.printStackTrace(); return false;}
	}
	
	public boolean deletePost(int postId){
		try{
			st.executeUpdate("delete from post where postId="+Integer.toString(postId));
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public ResultSet queryPost(){
		try{
			return st.executeQuery("select * from post");
		}catch(Exception e){e.printStackTrace();return null;}
	}
	
	public ResultSet queryPost(int postId){
		try{
			return st.executeQuery("select * from post where postId='"+postId+"'");
		}catch(Exception e){e.printStackTrace();return null;}
	}
	
	/*-------------------------------------------------------------------------------------------*/	
	/*-------------------------------表mail操作函数------------------------------------------*/
	/*-------------------------------------------------------------------------------------------*/		
	public boolean sendMail(String sender, String recv, String title,String cst){
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new java.util.Date());
			ResultSet rs = st.executeQuery("select * from counter where type='mail'");
			rs.next();
			int mailId = rs.getInt(2)+1;
			st.executeUpdate("insert into mail values("+Integer.toString(mailId)+", '"+recv+"', '"+sender+"', '"+title+"','"+cst+"','"+date+"')");
			st.executeUpdate("update counter set num="+Integer.toString(mailId)+" where type='mail'");
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	
	public boolean alterMail(int id, String sender,String recv, String title, String cst, String date){
		try{
			st.executeUpdate("delete from post where postId="+Integer.toString(id));
			st.executeUpdate("insert into post values("+Integer.toString(id)+" , '"+recv+"', '"+sender+"','"+title+"', '"+cst+"', '"+date+"')");
			return true;
		}catch(Exception e){e.printStackTrace(); return false;}
	}

	public boolean deleteMail(int mailId){
		try{
			st.executeUpdate("delete from mail where mailId="+Integer.toString(mailId));
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	public ResultSet queryMail(){
		try{
			return st.executeQuery("select * from mail");
		}catch(Exception e){e.printStackTrace(); return null;}
	}
	public ResultSet queryMail(String userId){
		try{
			return st.executeQuery("select * from mail where id='"+userId+"'");
		}catch(Exception e){e.printStackTrace();return null;}
	}
	public ResultSet queryMailByMailId(int mailId){
		try{
			return st.executeQuery("select * from mail where mailId="+mailId);
		}catch(Exception e){e.printStackTrace();return null;}
	}
	
	public void closeConnection(){
		try {
			st.close();
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();}
	}
	public static void main(String args[]){
		Sql sql = new Sql();
		try {
			sql.constume("yzheng", (float)10, "here");
		}catch(Exception e){e.printStackTrace();}
	
	}
	
}
