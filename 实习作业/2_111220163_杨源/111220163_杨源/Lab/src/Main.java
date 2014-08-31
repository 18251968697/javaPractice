import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import receive.GmailRecv;
import sendwb.*;
import weibo4j.model.WeiboException;
public class Main extends JFrame{  
	private JFrame mainFrame;
	private New NewFrame;
	private AlterTask alterTaskFrame;
	private Control ControlFrame;
    private TaskQueue request, goal;
    private Change Change = null;
	private JTextArea taskDesc, info, result;
	private JComboBox taskSelection;
	public static void main(String[] args) {
	    Main frame = new Main(); 
  }
	public Main(){
		mainFrame = new JFrame();
		request = new TaskQueue(50);
		goal = new TaskQueue(50);
		NewFrame = new New(this);
		alterTaskFrame = new AlterTask(this);
		ControlFrame = new Control(this);
		addMenuBar();		
		addPanel();
		addTskSctLst();
		mainFrame.setSize(700, 500); // Set the frame size
	    mainFrame.setLocationRelativeTo(null); // New since JDK 1.4
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setVisible(true); // Display the frame 
	    mainFrame.pack();
	}	
	private void addMenuBar(){//���������ʵ��
		JMenuBar bar = new JMenuBar();
		JMenuItem[] Items= new JMenuItem[8];
		JMenu taskItem = new JMenu("����");
		JMenu ctrItem = new JMenu("����");
		JMenu helpItem = new JMenu("����");
		taskItem.add(Items[0] = new JMenuItem("�½�����"));
		taskItem.add(Items[1] = new JMenuItem("�޸�����"));
		taskItem.add(Items[2] = new JMenuItem("ɾ������"));
		taskItem.add(Items[3] = new JMenuItem("�鿴��������"));
		ctrItem.add(Items[4] = new JMenuItem("��ʼ����"));
		ctrItem.add(Items[5] = new JMenuItem("��ͣ����"));
		ctrItem.add(Items[6] = new JMenuItem("��������"));
		helpItem.add(Items[7] = new JMenuItem("��Ϣ"));	
		bar.add(taskItem);
		bar.add(ctrItem);
		bar.add(helpItem);
		Items[0].addActionListener(//�½�����
			new ActionListener(){
				public void actionPerformed(ActionEvent e)	{
					NewFrame.display();	
				}	
			}
		);
		Items[1].addActionListener(//�޸�����
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						alterTaskFrame.display();
					}					
				}
		);
		Items[2].addActionListener(//ɾ������
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ControlFrame.display();		
					}		
				}
		);
		Items[3].addActionListener(//�鿴��������
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						ControlFrame.display();	
					}
				}
		);
		Items[4].addActionListener(//��ʼ����
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int i = taskSelection.getSelectedIndex();
						
						if(i<0 || taskSelection.getItemAt(i).toString().equals(""))
							return;
						if(Change != null){
							if(Change.isAlive() != true)
								Change.interrupt();
							return;
						}
						TaskRequest r = ((TaskRequest)(request.task[i]));
						TaskGoal g = ((TaskGoal)(goal.task[i]));
						r.getJta(result);
						g.getJta(result);
						Change = new Change(r,g);
						Change.start();
					}
				}
		);
		Items[5].addActionListener(//��ͣ����
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(Change == null)
						{
							result.setText(result.getText()+"\n�����ѽ���");
							return;
						}
						Change.interrupt();
						Change = null;
						result.setText(result.getText()+"\n�����ѽ���");
					}
				}
		);
		Items[6].addActionListener(//��������
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							if(Change == null)
							{
								result.setText(result.getText()+"\n�����ѽ���");
								return;
							}
							Change.interrupt();
							Change = null;
							result.setText(result.getText()+"\n�����ѽ���");
						}
					}
		);
		mainFrame.setJMenuBar(bar);	
	}

	private void addPanel(){//�������ʵ��
	    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		JLabel Label1 = new JLabel("������IFTTT",SwingConstants.CENTER);
		Label1.setFont(new Font("����", 1, 30));
		p1.add(Label1);
	    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		JLabel Label2 = new JLabel("ѡ��Ҫ���е�����");
		p2.add(Label2);	
		taskSelection = new JComboBox();
	    taskSelection.setPreferredSize(new Dimension(120, 24));
		p2.add(taskSelection);
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	    p3.add(new JLabel("�������е�����������"));
	    JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		taskDesc = new JTextArea();
		taskDesc.setLineWrap(true);
		taskDesc.setColumns(30);
	    taskDesc.setRows(6);
		taskDesc.setWrapStyleWord(true);
		taskDesc.setEditable(false);
		JScrollPane scrollPane1 = new JScrollPane(taskDesc);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		p4.add(scrollPane1);
		JPanel q = new JPanel();
	    q.setLayout(new BoxLayout(q, BoxLayout.Y_AXIS));
	    q.add(p1);
	    q.add(p2);
	    q.add(p3);
	    q.add(p4);
	    JPanel s = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		info = new JTextArea();
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		info.setEditable(false);
		info.setColumns(31);
	    info.setRows(14);
		String str = "ʹ��˵����\n1�������û��½�����\n2��Ȼ����ѡ�����->������������ʼ����\n" +
					 "3�����������ı����ڲ鿴��ǰ�������������\n";
		info.setText(str);
		JScrollPane scrollPane = new JScrollPane(info);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		s.add(info);
		JPanel t = new JPanel(new GridLayout(1,2,10,10));
	    t.add(q);
	    t.add(s);
	    mainFrame.add(t, BorderLayout.CENTER);
	    JPanel s1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	    s1.add(new JLabel("�����Ϣ��"));
	    JPanel s2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	    result = new JTextArea();
	    result.setColumns(64);
	    result.setRows(8);
	    result.setEditable(false);
	    JScrollPane scro=new JScrollPane(result);
	    scro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    s2.add(scro);
	    JPanel r = new JPanel();
	    r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
	    r.add(s1);
	    r.add(s2);
		mainFrame.add(r, BorderLayout.SOUTH);
	}
	private void addTskSctLst(){//�����б�
		class tskSctLst implements ItemListener{			
			public void itemStateChanged(ItemEvent e){
				int i = taskSelection.getSelectedIndex();
				if(i<0 || taskSelection.getItemAt(i).equals(""))
					return;
				String str = descOfTask(i);
				taskDesc.setText(str);
			}
		}
		taskSelection.addItemListener(new tskSctLst());
	}
	protected void resetTaskSelection(){	
		String tskName[] = new String[50];
		taskSelection.removeAllItems();
		for(int i=0; i < request.top; i++){
			tskName[i] = request.task[i].getName();
			taskSelection.addItem(tskName[i]);
		}
		alterTaskFrame.resetShowTaskName();
		ControlFrame.resetJlst();
	}
	public void addRequest(Task tsk, String tskName){
		request.task[request.top] = tsk;
		request.task[request.top].rename(tskName);
		request.top++;
	}
	public void addGoal(Task tsk, String tskName){
		goal.task[goal.top] = tsk;
		request.task[goal.top].rename(tskName);
		goal.top++;
	}
	public void deleteTask(int index){
		request.deleteItem(index);
		goal.deleteItem(index);
	}
	public void deleteTaskWithoutMove(int index){
		request.deleteItemWithoutMove(index);
		goal.deleteItemWithoutMove(index);
	}
	public void insertTask(int index){
		request.task[index] = request.task[request.top-1];
		request.task[request.top-1] = null;
		request.top--;
		goal.task[index] = goal.task[goal.top-1];
		goal.task[goal.top-1] = null;
		goal.top--;
	}
	public int getTaskNum(){		
		return request.top;
	}
	public String getTaskName(int index){
		return request.task[index].getName();
	}
	public int getRequsetTaskType(int index){
		return request.task[index].getTaskType();
	}
	public int getGoalTaskType(int index){
		return goal.task[index].getTaskType();
	}
	public Task getRequestTask(int index){
		return request.task[index];
	}
	public Task getGoalTask(int index){
		return goal.task[index];
	}
	public String descOfTask(int i){
		String str = null, strP1 = null, strP2 = null;
		myDate date;
		Time time;
		Acount mailAcount, wbAcount, goalMailAcount;
		String wbCst, mailCst;
		switch(request.task[i].getTaskType()){
			case 0:	date = new myDate();
					time = new Time();
					((OrderTime)request.task[i]).getDateAndTime(date, time);
					strP1 = "�ڱ���ʱ�䣺\n"+date.year+"-"+date.month+"-"+date.day
							+"\n"+time.hour+":"+time.minute+"\n";
					date = null;
					time = null;
					break;
			case 1: mailAcount = new Acount();
					((RecvMail)request.task[i]).getRecvMailAcount(mailAcount);
					strP1 = "������:\n"+mailAcount.id+"\n�յ��ʼ�\n";
					mailAcount = null;
					break;			
		}
		switch(goal.task[i].getTaskType()){
		case 2:	wbAcount = new Acount();
				wbCst = ((SendWb)goal.task[i]).getAcountAndCst(wbAcount);
				strP2 = "����һ��΢����\n"+wbCst;
				wbAcount = null;
				break;
		case 3:	goalMailAcount = new Acount();;
				mailCst = ((SendMail)goal.task[i]).getAcountAndCst(goalMailAcount);
				strP2 = "��\n"+goalMailAcount.id+"\n����һ���ʼ���\n"+mailCst;
				goalMailAcount = null;
				break;
		}
		str = strP1+strP2;
		return str;
	}
}







