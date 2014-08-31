import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import sendwb.*;
import weibo4j.model.WeiboException;
public class New extends JFrame{//新建任务类
	protected Main mainFrame;
	protected JFrame NewFrame;
	protected JTextArea taskNameInput, dateInput, timeInput, wbIdInput,mailIdInput,  goalMailIdInput, mailCstInput, wbCstInput;
	protected JPasswordField mailPassKeyInput,wbPassKeyInput;
	protected JComboBox combo1, combo2;
	protected JButton jOk, jReset, jCancel;
	private JPanel p2, p3, p4, p5, p6, p7;
	myDate date = null;
	Time time = null;
	Acount wbAcount = null, mailAcount = null, goalMailAcount = null;
	String wbCst = null, mailCst = null;
	public New(Main frame){//新建任务默认类
		mainFrame = frame;
		NewFrame = new JFrame();
		NewFrame.setLayout(new BorderLayout(10, 20));
		addPanel();
		addListener();
		NewFrame.setSize(650, 400);
		NewFrame.setLocationRelativeTo(null);
		NewFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		NewFrame.setTitle("新建任务");
		NewFrame.setVisible(false);	
	}
	private void addPanel(){//新建任务界面的搭建
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setPreferredSize(new Dimension(0, 50));
		JLabel Label3 = new JLabel("新建任务",SwingConstants.CENTER);
		Label3.setFont(new Font("宋体", 1, 15));
		Label3.setLocation(230, 0);
		Label3.setSize(100,25);
		p1.add(Label3);	
		JLabel Label4 = new JLabel(" 任务名称:", SwingConstants.CENTER);
		Label4.setLocation(20, 30);
		Label4.setSize(60, 20);
		p1.add(Label4);
		taskNameInput = new JTextArea();
		taskNameInput.setLocation(100, 30);;
		taskNameInput.setSize(500, 20);
		taskNameInput.setEditable(true);
		taskNameInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		p1.add(taskNameInput);	
		NewFrame.add(p1, BorderLayout.NORTH);
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setPreferredSize(new Dimension(250, 220));
		p2.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		JLabel jlb = new JLabel("If This");
		Font font = new Font("宋体", Font.BOLD, 22);
		jlb.setFont(font);
		jlb.setSize(100, 40);
		jlb.setLocation(0, 0);
		p2.add(jlb);
		String items[] = {"定时", "收到邮件"};
		combo1 = new JComboBox(items);
		combo1.setSize(100, 25);
		combo1.setLocation(20, 50);
		p2.add(combo1);
		p4 = new JPanel();
		p4.setLayout(null);
		p4.setSize(220, 60);
		p4.setLocation(20, 85);
		JLabel date = new JLabel("日期：");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tmp = df.format(new Date());
		dateInput = new JTextArea(tmp.substring(0, 10));
		dateInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		dateInput.setEditable(true);  
		JLabel time = new JLabel("时间：");
		timeInput = new JTextArea(tmp.substring(11, 16));
		timeInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		timeInput.setEditable(true);
		date.setLocation(0, 0);
		date.setSize(60, 20);
		dateInput.setLocation(70, 0);
		dateInput.setSize(150, 20);
		time.setLocation(0, 25);
		time.setSize(60, 20);
		timeInput.setLocation(70, 25);
		timeInput.setSize(150, 20);
		p4.add(date);
		p4.add(dateInput);
		p4.add(time);
		p4.add(timeInput);
		p5 = new JPanel();
		p5.setLayout(null);
		p5.setSize(220, 60);
		p5.setLocation(20, 85);
		JLabel id = new JLabel("用    户：");
		mailIdInput = new JTextArea();
		mailIdInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		mailIdInput.setEditable(true);  
		JLabel passKey = new JLabel("密 码：");
		mailPassKeyInput = new JPasswordField();
		mailPassKeyInput.setEchoChar('*');
		mailPassKeyInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		mailPassKeyInput.setEditable(true);
		id.setLocation(0, 0);
		id.setSize(60, 20);
		mailIdInput.setLocation(70, 0);
		mailIdInput.setSize(150, 20);
		passKey.setLocation(0, 25);
		passKey.setSize(60, 20);
		mailPassKeyInput.setLocation(70, 25);
		mailPassKeyInput.setSize(150, 20);
		p5.add(id);
		p5.add(mailIdInput);
		p5.add(passKey);
		p5.add(mailPassKeyInput);
		p2.add(p4);
		NewFrame.add(p2, BorderLayout.WEST);
		p3 = new JPanel();
		p3.setLayout(null);
		p3.setPreferredSize(new Dimension(350, 220));
		p3.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		JLabel Label1 = new JLabel("Then That");
		Label1.setFont(new Font("宋体", Font.BOLD, 22));
		Label1.setSize(200, 40);
		Label1.setLocation(0, 0);
		p3.add(Label1);
		String item[] = {"新浪微博", "发送邮件"};
		combo2 = new JComboBox(item);
		combo2.setSize(100, 25);
		combo2.setLocation(65, 50);
		p3.add(combo2);
		p6 = new JPanel();
		p6.setLayout(null);
		p6.setSize(280, 150);
		p6.setLocation(65, 85);
		JLabel wbId = new JLabel("用户：");
		wbIdInput = new JTextArea();
		wbIdInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		wbIdInput.setEditable(true);
		JLabel wbPassKey = new JLabel("密码：");
		wbPassKeyInput = new JPasswordField();
		wbPassKeyInput.setEchoChar('*');
		wbPassKeyInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		wbPassKeyInput.setEditable(true);  
		JLabel constant = new JLabel("微博内容:");
		wbCstInput = new JTextArea();
		wbCstInput.setLineWrap(true);
		wbCstInput.setWrapStyleWord(true);
		wbCstInput.setEditable(true);
		wbCstInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		wbId.setLocation(0, 0);
		wbId.setSize(60, 20);
		wbIdInput.setLocation(70, 0);
		wbIdInput.setSize(150, 20);
		wbPassKey.setLocation(0, 25);
		wbPassKey.setSize(60, 20);
		wbPassKeyInput.setLocation(70, 25);
		wbPassKeyInput.setSize(150, 20);
		constant.setLocation(0, 50);
		constant.setSize(100, 20);
		wbCstInput.setLocation(0, 75);
		wbCstInput.setSize(280,75);	
		p6.add(wbId);
		p6.add(wbIdInput);
		p6.add(wbPassKey);
		p6.add(wbPassKeyInput);
		p6.add(constant);
		p6.add(wbCstInput);	
		p7 = new JPanel();
		p7.setLayout(null);
		p7.setSize(280, 150);
		p7.setLocation(65, 85);
		JLabel Label2 = new JLabel("地    址：");
		goalMailIdInput = new JTextArea();
		goalMailIdInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		goalMailIdInput.setEditable(true);  
		JLabel mail = new JLabel("邮件内容:");
		mailCstInput = new JTextArea();
		mailCstInput.setLineWrap(true);
		mailCstInput.setWrapStyleWord(true);
		mailCstInput.setEditable(true);
		mailCstInput.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		Label2.setLocation(0, 0);
		Label2.setSize(100, 20);
		goalMailIdInput.setLocation(70, 0);
		goalMailIdInput.setSize(150, 20);
		mail.setLocation(0, 25);
		mail.setSize(100, 20);
		mailCstInput.setLocation(0, 50);
		mailCstInput.setSize(280,100);	
		p7.add(Label2);
		p7.add(goalMailIdInput);
		p7.add(mail);
		p7.add(mailCstInput);	
		p3.add(p6);
		NewFrame.add(p3, BorderLayout.EAST);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		panel.setPreferredSize(new Dimension(0,30));	
		jOk = new JButton("确定");
		jReset = new JButton("重置");
		jCancel = new JButton("取消");		
		panel.add(jOk);
		panel.add(jReset);
		panel.add(jCancel);	
		NewFrame.add(panel, BorderLayout.SOUTH);	
	}	
	private void addListener(){//添加监视器
		combo1.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if(combo1.getSelectedIndex() == 0){
							p2.remove(p5);
							p2.add(p4);
							NewFrame.repaint();
						}
						else{
							p2.remove(p4);
							p2.add(p5);
							NewFrame.repaint();			
						}
					}	
				}
		);
		combo2.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if(combo2.getSelectedIndex() == 0){
							p3.remove(p7);
							p3.add(p6);
							NewFrame.repaint();
						}
						else{
							p3.remove(p6);
							p3.add(p7);
							NewFrame.repaint();			
						}
					}		
				}
		);	
		class OkListener implements ActionListener{//确定按钮的实现
			Main frame;
			public OkListener(Main frame){
				this.frame = frame;
				this.frame.setVisible(false);
			}
			public void actionPerformed(ActionEvent e){
				addTask();	
				frame.resetTaskSelection();
			}
		}			
		jOk.addActionListener(new OkListener(mainFrame));
		jReset.addActionListener(//重置按钮的实现，将时间默认改为2013-11-10,12:00
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						taskNameInput.setText("");
						combo1.setSelectedIndex(0);
						dateInput.setText("2013-11-10");
						timeInput.setText("12:00");
						mailIdInput.setText("");
						mailPassKeyInput.setText("");
						combo2.setSelectedIndex(0);
						wbIdInput.setText("");
						wbPassKeyInput.setText("");
						wbCstInput.setText("");
						goalMailIdInput.setText("");
						mailCstInput.setText("");
					}
				}
		);
		jCancel.addActionListener(//取消按钮的实现
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						NewFrame.setVisible(false);
					}
				}
		);
	}
	protected void addTask(){//添加任务
		boolean rightName = true;
		String tskName = null;
		tskName= taskNameInput.getText();
		if(tskName.equals(""))
			rightName = false;
		for(int i=0; i<mainFrame.getTaskNum(); i++){
			if(tskName.equals(mainFrame.getTaskName(i)))
				rightName = false;
		}
		if(rightName == false)	{
			JOptionPane.showMessageDialog(null,"任务名不能为空或重复", "Error", 
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(getTask() == false)
			return;	
		addTask(tskName);
	}
	public boolean getTask(){//查看任务的实现
		if(combo1.getSelectedIndex() == 0){
			boolean right = true;
			String year = "2013", month = "11", day = "18", hour = "00", minute = "00";
			String dateStr = dateInput.getText();
			if(!dateStr.matches("^((?!0000)[0-9]{4}-((0[1-9]" +
					"|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]" +
					"|1[0-2])-(29|30)|(0[13578]|1[02])-31)|" +
					"([0-9]{2}(0[48]|[2468][048]|[13579][26])|" +
					"(0[48]|[2468][048]|[13579][26])00)-02-29)$"))
					right = false;
			if(right == false){
				JOptionPane.showMessageDialog(null,"日期错误!\n请确认格式是否正确：YYYY-MM-DD", "Error", 
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			year = dateStr.substring(0, 4);
			month = dateStr.substring(5, 7);
			day = dateStr.substring(8, 10);
			
			
			right = true;
			String timeStr = timeInput.getText();
			if(!timeStr.matches("([0-1][0-9]|2[0-4]):(60|[0-5][0-9])"))
				right = false;
			if(right == false){
				JOptionPane.showMessageDialog(null,"时间错误!\n请确认格式是否正确：hh-mm", "Error", 
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}	
			hour = timeStr.substring(0, 2);
			minute = timeStr.substring(3,5);
			
			date = new myDate(year, month, day);
			time = new Time(hour, minute, "00");
		}
		else{
			String mailIdStr = mailIdInput.getText();
			String mailPassKeyStr = String.valueOf(mailPassKeyInput.getPassword());
			mailAcount = new Acount(mailIdStr, mailPassKeyStr);
		}
		if(combo2.getSelectedIndex()==0){
			String wbIdStr = wbIdInput.getText();
			String wbPassKeyStr = String.valueOf(wbPassKeyInput.getPassword());
			wbCst = wbCstInput.getText(); 
			wbAcount = new Acount(wbIdStr, wbPassKeyStr);
		}
		else{
			String goalMailIdStr = goalMailIdInput.getText();
			mailCst = mailCstInput.getText();
			goalMailAcount = new Acount(goalMailIdStr, "");
		}
		return true;
	}
	public void addTask(String tskName){
		if(combo1.getSelectedIndex() == 0){
			mainFrame.addRequest(new OrderTime(date, time), tskName);
			date = null;
			time = null;
		}
		else{
			mainFrame.addRequest(new RecvMail(mailAcount), tskName);
			mailAcount = null;
		}
		if(combo2.getSelectedIndex()==0){
			mainFrame.addGoal(new SendWb(wbAcount, wbCst), tskName);
			wbAcount = null;
			wbCst = null;
		}
		else{
			mainFrame.addGoal(new SendMail(goalMailAcount, mailCst), tskName);
			mailCst = null;
		}
	}
	public void display(){
		NewFrame.setVisible(true);
	}
}