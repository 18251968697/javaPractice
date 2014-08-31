import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import sendwb.*;
import weibo4j.model.WeiboException;
public class Control extends JFrame{
	Main mainFrame;
	JFrame ControlFrame;
	JList jlst;
	JTextArea jta;
	DefaultListModel listModel;
	JButton jbtDel, jbtExit;
	public Control(Main frame){
		ControlFrame = new JFrame();
		mainFrame =  frame;
		ControlFrame.setLayout(new BorderLayout(10, 10));
		addControl();
		addListener();
		ControlFrame.setSize(650, 400);
		ControlFrame.setLocationRelativeTo(null);
		ControlFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		ControlFrame.setTitle("查看");
		ControlFrame.setAlwaysOnTop(true);
		ControlFrame.setVisible(false);	
	}
	public void resetJlst(){
		String tskName[] = new String[50];
		listModel.removeAllElements();
		for(int i=0; i < mainFrame.getTaskNum(); i++){
			tskName[i] = mainFrame.getTaskName(i);
			listModel.addElement(tskName[i]);
		}
	}
	private void addControl(){//查看任务的实现
		JLabel Label1 = new JLabel("查看任务", SwingConstants.CENTER);
		Label1.setPreferredSize(new Dimension(0, 40));	
		ControlFrame.add(Label1, BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(300, 240));
		JLabel Label2 = new JLabel("任务列表：");
		Label2.setLocation(0, 0);
		Label2.setSize(100, 25);
		panel.add(Label2);
		listModel = new DefaultListModel();
		jlst = new JList(listModel);
		jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrJlst = new JScrollPane(jlst);
		scrJlst.setLocation(0,30);
		scrJlst.setSize(280, 200);
		panel.add(scrJlst);
		ControlFrame.add(panel, BorderLayout.WEST);	
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setPreferredSize(new Dimension(300, 240));
		JLabel jlb = new JLabel("详细信息：");
		jlb.setLocation(0, 0);
		jlb.setSize(100, 25);
		panel1.add(jlb);
		jta = new JTextArea();
		JScrollPane scrJta = new JScrollPane(jta);
		scrJta.setLocation(0,30);
		scrJta.setSize(300, 200);
		panel1.add(scrJta);
		ControlFrame.add(panel1, BorderLayout.EAST);	
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new Dimension(0, 50));
		jbtDel = new JButton("删除");
		jbtDel.setLocation(200, 0);
		jbtDel.setSize(70,30);
		jbtExit = new JButton("退出");
		jbtExit.setLocation(340, 0);
		jbtExit.setSize(70,30);
		panel2.add(jbtDel);
		panel2.add(jbtExit);		
		ControlFrame.add(panel2, BorderLayout.SOUTH);	
	}
	private void addListener(){
		class jlstListener implements ListSelectionListener{
			public void valueChanged(ListSelectionEvent a){
					int i=jlst.getSelectedIndex();
					if(i<0)	{
						jta.setText("");
						return;
					}
					String str = mainFrame.descOfTask(i);
					jta.setText(str);
			}
		}
		jlst.addListSelectionListener(new jlstListener());
		jbtDel.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int i = jlst.getSelectedIndex();
						mainFrame.deleteTask(i);
						mainFrame.resetTaskSelection();
						jlst.setSelectedIndex(-1);
					}
				}
		);
	}
	public void display(){
		ControlFrame.setVisible(true);	
	}
}