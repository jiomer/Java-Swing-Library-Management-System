package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DB;

//借书界面
public class AddDialog implements ActionListener {
	
	private DB db = new DB();

	//定义框架
	JFrame jf = new JFrame("借书界面");
	//定义面板
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("学号：");
	JLabel j2 = new JLabel("专业班级：");
	JLabel j3 = new JLabel("联系电话：");
	JLabel j4 = new JLabel("借书名称：");

	
	JTextField jtext1 = new JTextField(10);
	JTextField jtext2 = new JTextField(10);
	JTextField jtext3 = new JTextField(10);
	JTextField jtext4 = new JTextField(10);

	
	JButton addButton = new JButton("借书");
	
	
	//定义存储系统界面的变量
	SystemDialog sys;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//获取输入的值
		String xuehao = jtext1.getText();
		String dep = jtext2.getText();
		String let = jtext3.getText();
		String name  = jtext4.getText();

		
		String sql = "INSERT INTO t_jieshu(xuehao, dep, let, name) VALUES('"
				+ xuehao + "', '" + dep + "', '" + let + "', '" + name
				+ "');";
				
		System.out.println(sql);
		if (db.updataData(sql) == 1) {
			JOptionPane.showMessageDialog(null, "借书成功");
			//System.out.println("借书成功");
			// 告诉系统界面对象，刷新数据
			sys.updateTable("");
			// 销毁登录界面
			jf.dispose();
			} else {
				JOptionPane.showMessageDialog(null, "借书失败");
			//System.out.println("借书失败");
			}
			db.close();
			}
	public void showAddDialog(SystemDialog sys){
		
		//保存传递过来的系统界面对象
		this.sys = sys;
		
		// 设置框架大小
		jf.setSize(450, 300);
		// 设置位置
		jf.setLocation(700, 350);
		// 设置框架可见
		jf.setVisible(true);
		
		jp.add(j1);
		jp.add(jtext1);
		jp.add(j2);
		jp.add(jtext2);
		jp.add(j3);
		jp.add(jtext3);
		jp.add(j4);
		jp.add(jtext4);

		jp.add(addButton);
		
		addButton.addActionListener(this);
		
		jf.add(jp);
		ImageIcon TP = new ImageIcon("3.jpg");// 这是背景图片
		JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
		jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
		Container cp = jf.getContentPane();
		cp.setLayout(new BorderLayout());  //边界布局
		
		//设置字体
		j1.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j2.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j3.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j4.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		//设置控件不透明
		((JPanel) cp).setOpaque(false); 
		((JPanel) jp).setOpaque(false); 
		jtext1.setOpaque(false);
		jtext2.setOpaque(false);
		jtext3.setOpaque(false);
		jtext4.setOpaque(false);
		cp.add(jp);
	}

}
