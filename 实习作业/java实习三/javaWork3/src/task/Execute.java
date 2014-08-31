package task;

import java.sql.ResultSet;
import java.sql.SQLException;

import sql.Sql;

public class Execute implements Runnable{		//ִ�������߳�
	private String userId, tskName;				//�û�����������
	
	public Execute(String userId, String tskName){	//��ʼ��
		this.userId = userId;
		this.tskName = tskName;
	}
	
	public void run(){								//ִ������
		Sql sql = new Sql();						//�����ݿ��л�ȡ������Ϣ
		ResultSet rs = sql.queryTask(userId, tskName);
		Task tsk = new Task();;
		try {
			rs.next();
			//ifThis������� thenThat�������
			int ifType = rs.getInt(3), thenType = rs.getInt(5);		
			
			//ifThis����������Ϣ�� thenThat����������Ϣ
			String ifStr = rs.getString(4).trim(), thenStr = rs.getString(6).trim();	
			//����״̬
			String state = rs.getString(7).trim(), lastState=null;	
			//��������ǳ���׼��״̬�����߳̽���
			if(!state.equals("ready"))			
				return;
			//����ʼִ�У�������״̬��Ϊ����̬
			sql.updateTaskState(userId, tskName, Task.RUNNING);	
			
			//����ifThis������𴴽�ifThis����
			switch(ifType){
			case SubTask.ORDERTIME: myDate md[] = new myDate[1];	//��ȡ��ʱ��������Ϣ��
									Time t[] = new Time[1];			//������ʱ�����
									Translate.StringToOrderTime(ifStr, md, t);
									tsk.ifTask = new OrderTime(md[0], t[0]);
									break;
			case SubTask.RECVMAIL:	Acount mail[] = new Acount[1];	//��ȡ�����ʼ���������Ϣ
																	//���������ʼ������
									Translate.StringToRecvMail(ifStr, mail);
									tsk.ifTask = new RecvMail(mail[0]);
									break;
			}
			
			//����thenThat������𴴽�thenThat����
			switch(thenType){											
			case SubTask.SENDMAIL:	Acount mail[] = new Acount[1];		//��ȡ�����ʼ���������Ϣ��
									String toInfo[] = new String[3];	//���������ʼ������
									toInfo = Translate.StringToSendMail(thenStr, mail);
									tsk.thenTask = new SendMail(mail[0], toInfo[0], toInfo[1], toInfo[2]);
									break;
			case SubTask.SENDWEIBO:	Acount wb[] = new Acount[1];		//��ȡ����΢����������Ϣ
																		//��������΢����������Ϣ
									String cst = Translate.StringToSendWb(thenStr, wb);
									tsk.thenTask = new SendWb(wb[0], cst);
									break;
			}
			tsk.ifTask.taskStart();						//��������������
			sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());		
			while(true){
				rs = sql.queryTask(userId, tskName);	//ÿ��ѭ����Ҫ�����ݿ��в�ѯ�������״̬��
														//�������Ϊ��ͣ���������ֱ�������Ӧ������
				rs.next();		
				state = rs.getString(7).trim();
				lastState = rs.getString(9);
				
				//����������5��״̬��Ԥ��̬��ready�� ����̬��running, ��̬ͣ��suspend
									//����̬��continue(�����������ͣ���ٴο�ʼ���оͻᱻ��Ϊ����̬)
									//����̬��end, 
				if(state.equals(Task.CONTINUE)){		//�����ǰ����̬Ϊ����̬����鿴������ǰһ��״̬
					if(!lastState.equals(Task.CONTINUE)){	//���ǰһ��״̬���Ǽ���̬��˵���������ж�
						tsk.ifTask.taskStart();				//�ұ��ٴο�������˸���������Ϣ
						sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());
						sql.updateTaskLastState(userId, tskName, Task.CONTINUE);
					}
				}
				else if(state.equals(Task.SUSPEND)){	//���״̬Ϊ��ͣ���������ǰѭ����˯��2�������´�ѭ��
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
				else if(state.equals(Task.END)){			//���״̬Ϊ��������������߳�
					tsk.ifTask.taskFailed();
					tsk.ifTask.taskEnd();
					sql.updateTaskDescription(userId, tskName, ((SubTask)tsk.ifTask).getDescription());
					sql.updateTaskLastState(userId, tskName, Task.END);
					return;
				}
				
				if(((IfTypeTask)tsk.ifTask).ifThis()){	//���ifThis�����㣬��ִ��thenThat,���򣬽����´�ѭ��
					tsk.ifTask.taskSuccess();
					tsk.ifTask.taskEnd();
					tsk.thenTask.taskStart();
					sql.updateTaskDescription(userId, tskName, 
							((SubTask)tsk.ifTask).getDescription()+((SubTask)tsk.thenTask).getDescription());
					
					//���thenThat�����㣬��������ɡ���������ʧ�ܡ�
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
					sql.updateTaskDescription(userId, tskName, "����ʧ�ܣ�");
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
