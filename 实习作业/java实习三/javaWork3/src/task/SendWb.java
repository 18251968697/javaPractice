package task;

class SendWb extends ThenTypeTask	//发送微博类
{
	Acount wbAcount;
	String wbConstant;
	
	public SendWb(Acount a, String wbCst)
	{
		description += "初始化发微博任务...\n";
		wbAcount = new Acount(a);
		wbConstant = wbCst;
		super.type = SubTask.SENDWEIBO;
		description += "初始化完成\n";
	}

	public boolean thenThat()	//如果发送成功则返回true,否则返回false
	{
		try{
			sendwb.Sina.sendout(wbAcount.id, wbAcount.passkey, wbConstant);
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public void taskStart(){
		description += "开始发送微博...\n";
	}
	public void taskSuccess(){
		description += "发送成功.\n";
	}
	public void taskFailed(){
		description += "发送失败.\n";
	}
	public void taskSuspend(){
		description += "发微博任务中止.\n";
	}
	public void taskEnd(){
		description += "发微博任务结束.";
	}
}