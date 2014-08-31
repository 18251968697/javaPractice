import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import sendwb.*;
import weibo4j.model.WeiboException;
class AlterTask extends New{
	private JFrame alterTaskFrame;
	private JComboBox showTaskName;
	public AlterTask(Main frame){
		super(frame);
		alterTaskFrame = NewFrame;
		addNorthPanel();
		addAlterTaskListener();		
		alterTaskFrame.setVisible(false);
	}
	private void addNorthPanel(){//修改任务
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(0, 50));
		JLabel jlbNorth = new JLabel("修改任务",SwingConstants.CENTER);
		jlbNorth.setLocation(230, 0);
		jlbNorth.setSize(100,25);
		panel.add(jlbNorth);
		JLabel jlbWest = new JLabel("任务名称:", SwingConstants.CENTER);
		jlbWest.setLocation(20, 30);
		jlbWest.setSize(60, 20);
		panel.add(jlbWest);
		showTaskName = new JComboBox();
		showTaskName.setLocation(100, 30);
		showTaskName.setSize(500, 20);
		showTaskName.setSelectedIndex(-1);
		panel.add(showTaskName);
		alterTaskFrame.add(panel, BorderLayout.NORTH);
	}
	private void addAlterTaskListener(){//添加监视器
		class showTskNameLisner implements ItemListener{
			private Main frame;
			public showTskNameLisner(Main frame){
				this.frame = frame;
			}
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED){
						int i = showTaskName.getSelectedIndex();
						if(mainFrame.getRequsetTaskType(i) == 0){
							combo1.setSelectedIndex(0);
							myDate date = new myDate();
							Time time = new Time();
							((OrderTime)mainFrame.getRequestTask(i)).getDateAndTime(date, time);
							dateInput.setText(date.year+"-"+date.month+"-"+date.day);
							timeInput.setText(time.hour+":"+time.minute);
						}
						else{
							combo1.setSelectedIndex(1);
							Acount mailAcount = new Acount();
							((RecvMail)mainFrame.getRequestTask(i)).getRecvMailAcount(mailAcount);
							mailIdInput.setText(mailAcount.id);
							mailPassKeyInput.setText(mailAcount.passkey);
						}
						if(mainFrame.getGoalTaskType(i) == 2){
							combo2.setSelectedIndex(0);
							Acount wbAcount = new Acount();
							String wbCst = new String();
							wbCst = ((SendWb)mainFrame.getGoalTask(i)).getAcountAndCst(wbAcount);
							wbIdInput.setText(wbAcount.id);
							wbPassKeyInput.setText(wbAcount.passkey);
							wbCstInput.setText(wbCst);
						}
						else	{
							combo2.setSelectedIndex(1);
							Acount goalMailAcount = new Acount();
							String mailCst = new String();
							mailCst = ((SendMail)mainFrame.getGoalTask(i)).getAcountAndCst(goalMailAcount);
							goalMailIdInput.setText(goalMailAcount.id);
							mailCstInput.setText(mailCst);
						}
						alterTaskFrame.repaint();
				}
			}
		}
		showTaskName.addItemListener(new showTskNameLisner(mainFrame));
	}
	protected void addTask(){
			int index = showTaskName.getSelectedIndex();
			String tskName = showTaskName.getItemAt(index).toString();
			mainFrame.deleteTaskWithoutMove(index);
			getTask();;
			addTask(tskName);
			mainFrame.insertTask(index);		
	}
	public void display(){	
		NewFrame.repaint();
		NewFrame.setVisible(true);
	}
	public void resetShowTaskName(){
		TaskQueue r;
		String tskName[] = new String[50];
		showTaskName.removeAllItems();
		for(int i=0; i < mainFrame.getTaskNum(); i++){
			tskName[i] = mainFrame.getTaskName(i);
			showTaskName.addItem(tskName[i]);
		}
	}
	public void alterTask(){}
}