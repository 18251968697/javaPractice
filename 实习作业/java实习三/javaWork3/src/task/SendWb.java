package task;

class SendWb extends ThenTypeTask	//����΢����
{
	Acount wbAcount;
	String wbConstant;
	
	public SendWb(Acount a, String wbCst)
	{
		description += "��ʼ����΢������...\n";
		wbAcount = new Acount(a);
		wbConstant = wbCst;
		super.type = SubTask.SENDWEIBO;
		description += "��ʼ�����\n";
	}

	public boolean thenThat()	//������ͳɹ��򷵻�true,���򷵻�false
	{
		try{
			sendwb.Sina.sendout(wbAcount.id, wbAcount.passkey, wbConstant);
			return true;
		}catch(Exception e){e.printStackTrace();return false;}
	}
	
	public void taskStart(){
		description += "��ʼ����΢��...\n";
	}
	public void taskSuccess(){
		description += "���ͳɹ�.\n";
	}
	public void taskFailed(){
		description += "����ʧ��.\n";
	}
	public void taskSuspend(){
		description += "��΢��������ֹ.\n";
	}
	public void taskEnd(){
		description += "��΢���������.";
	}
}