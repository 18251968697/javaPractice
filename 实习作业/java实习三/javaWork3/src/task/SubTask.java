package task;

public abstract class SubTask{
	protected int type=-1;			//子任务类型
	protected String description="";	//子任务结果描述
	
	public final static int ORDERTIME=1;
	public final static int RECVMAIL=2;
	public final static int SENDWEIBO=3;
	public final static int SENDMAIL=4;
	
	public int getType(){
		return type;
	}
	public String getDescription(){
		return description;
	}
	public abstract void taskStart();
	public abstract void taskSuspend();
	public abstract void taskSuccess();
	public abstract void taskFailed();
	public abstract void taskEnd();
}