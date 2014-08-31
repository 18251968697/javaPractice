package task;

import java.sql.ResultSet;
import java.sql.SQLException;

import sql.Sql;

public class Execute implements Runnable{		//执行任务线程
	private String userId, tskName;				//用户名，任务名
	
	public Execute(String userId, String tskName){	//初始化
		this.userId = userId;
		this.tskName = tskName;
	}
	
	public void run(){								//执行任务
		Sql sql = new Sql();						//从数据库中获取任务信息
		ResultSet rs = sql.queryTask(userId, tskName);
		Task tsk = new Task();;
		try {
			rs.next();
			//ifThis任务类别， thenThat任务类别
			int ifType = rs.getInt(3), thenType = rs.getInt(5);		
			
			//ifThis任务所需信息， thenThat任务所需信息
			String ifStr = rs.getString(4).trim(), thenStr = rs.getString(6).trim();	
			//任务状态
			String state = rs.getString(7).trim(), lastState=null;	
			//如果任务不是出于准备状态，则线程结束
			if(!state.equals("ready"))			
				return;
			//任务开始执行，将任务状态置为运行态
			sql.updateTaskState(userId, tskName, Task.RUNNING);	
			
			//根据ifThis任务类别创建ifThis任务
			switch(ifType){
			case SubTask.ORDERTIME: myDate md[] = new myDate[1];	//获取定时类任务信息，
									Time t[] = new Time[1];			//创建定时类对象
									Translate.StringToOrderTime(ifStr, md, t);
									tsk.ifTask = new OrderTime(md[0], t[0]);
									break;
			case SubTask.RECVMAIL:	Acount mail[] = new Acount[1];	//获取接受邮件类任务信息
																	//创建接受邮件类对象
									Translate.StringToRecvMail(ifStr, mail);
									tsk.ifTask = new RecvMail(mail[0]);
									break;
			}
			
			//根据thenThat任务类别创建thenThat任务
			switch(thenType){											
			case SubTask.SENDMAIL:	Acount mail[] = new Acount[1];		//获取发送邮件类任务信息，
									String toInfo[] = new String[3];	//创建发送邮件类对象
									toInfo = Translate.StringToSendMail(thenStr, mail);
									tsk.thenTask = new SendMail(mail[0], toInfo[0], toInfo[1], toInfo[2]);
									break;
			case SubTask.SENDWEIBO:	Acount wb[] = new Acount[1];		//获取发送微博类任务信息
																		//创建发送微博类任务信息
									String cst = Translate.StringToSendWb(thenStr, wb);
									tsk.thenTask = new SendWb(wb[0], cst);
									break;
			}
			tsk.ifTask.taskStart();						//跟新任务结果描述
			sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());		
			while(true){
				rs = sql.queryTask(userId, tskName);	//每次循环均要到数据库中查询下任务的状态，
														//如果被置为暂停活结束，则分别作出相应动作。
				rs.next();		
				state = rs.getString(7).trim();
				lastState = rs.getString(9);
				
				//所有任务共有5种状态，预备态：ready， 运行态：running, 暂停态：suspend
									//继续态：continue(如果任务呗暂停，再次开始运行就会被置为继续态)
									//结束态：end, 
				if(state.equals(Task.CONTINUE)){		//如果当前任务态为继续态，则查看该任务前一个状态
					if(!lastState.equals(Task.CONTINUE)){	//如果前一个状态不是继续态，说明该任务被中断
						tsk.ifTask.taskStart();				//且被再次开启，因此跟新运行信息
						sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());
						sql.updateTaskLastState(userId, tskName, Task.CONTINUE);
					}
				}
				else if(state.equals(Task.SUSPEND)){	//如果状态为暂停，则结束当前循环，睡眠2秒后进行下次循环
					try{
						if(!lastState.equals(Task.SUSPEND)){
							tsk.ifTask.taskSuspend();
							sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());
							sql.updateTaskLastState(userId, tskName, Task.SUSPEND);
						}
						Thread.sleep(2000);
						continue;
					}catch(Exception e){e.printStackTrace();return;}
				}
				else if(state.equals(Task.END)){			//如果状态为结束，则结束该线程
					tsk.ifTask.taskFailed();
					tsk.ifTask.taskEnd();
					sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());
					sql.updateTaskLastState(userId, tskName, Task.END);
					return;
				}
				
				if(((IfTypeTask)tsk.ifTask).ifThis()){	//如果ifThis被满足，则执行thenThat,否则，进行下次循环
					tsk.ifTask.taskSuccess();
					tsk.ifTask.taskEnd();
					tsk.thenTask.taskStart();
					sql.updateTaskDescription(userId, tskName, 
							((SubTask)tsk.ifTask).getDescription()+((SubTask)tsk.thenTask).getDescription());
					
					//如果thenThat被满足，则任务完成。否则，任务失败。
					if(((ThenTypeTask)tsk.thenTask).thenThat()){
						tsk.thenTask.taskSuccess();
						tsk.thenTask.taskEnd();
						sql.updateTaskDescription(userId, tskName, 
								((SubTask)tsk.ifTask).getDescription()+((SubTask)tsk.thenTask).getDescription());
						sql.updateTaskState(userId, tskName, Task.END);
						return;
					}
					else{
						tsk.thenTask.taskFailed();
						tsk.thenTask.taskEnd();
						sql.updateTaskDescription(userId, tskName, 
								((SubTask)tsk.ifTask).getDescription()+((SubTask)tsk.thenTask).getDescription());
						sql.updateTaskState(userId, tskName, Task.END);
						return;
					}
			
				}
				try{
					Thread.sleep(1000);
				}catch(Exception e){
					e.printStackTrace(); 
					sql.updateTaskDescription(userId, tskName, "任务失败！");
					return;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tsk.ifTask.taskFailed();
			tsk.ifTask.taskEnd();
			sql.updateTaskDescription(userId, tskName, 
					((SubTask)tsk.ifTask).getDescription()+((SubTask)tsk.thenTask).getDescription());
			return;
		}
		
		
	}

	public static void main(String args[]){
		String userId = "yzheng";
		String taskName = "tsk2";
		Acount gmail = new Acount("yangzh.nju@gmail.com", "yz344047780");
		String toAddr = "yz_tank@163.com", title = "testTask", cst = "Hello, boy!";
		String ifStr = Translate.RecvMailToString(gmail);
		String thenStr = Translate.SendMailToString(gmail, toAddr, title, cst);
		
		Sql sql = new Sql();
		
		sql.addTask(userId, taskName, SubTask.RECVMAIL, ifStr, SubTask.SENDMAIL, thenStr, null);

/*
Execute exe = new Execute(userId, taskName);
		new Thread(exe).start();
		while(true){
			try {
				ResultSet rs = sql.queryTask(userId, taskName);
				rs.next();
				System.out.println("---------------------------------");
				System.out.println(rs.getString(8).trim());
				System.out.println("---------------------------------\n");
				Thread.sleep(5000);
			} catch (SQLException e) {e.printStackTrace();} catch(Exception e) {e.printStackTrace();
			}
		} */
	}
}
