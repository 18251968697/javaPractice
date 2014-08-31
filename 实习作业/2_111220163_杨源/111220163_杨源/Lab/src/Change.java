public class Change extends Thread{
	private TaskRequest rst;
	private TaskGoal gal;
	private boolean finish = false, stop = false;
	public Change(TaskRequest r, TaskGoal g){
		rst = r;
		gal = g;
	}
	public void beFinish(){
		finish = true;
	}
	public void doStop(){
		stop = true;
	}
	public void reDo(){
		stop = false;
	}
	public void run(){
		try{
			if(rst.ifThis() == true)
				gal.thenThat();
		}catch(Exception e){
			if(finish == true) 
				return;
			if(stop == true)
				try{
					sleep(3600*1000);
				}catch(Exception a){}}
		}
}


