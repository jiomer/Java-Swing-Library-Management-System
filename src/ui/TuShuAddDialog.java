package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.DB;


public class TuShuAddDialog implements ActionListener {
	
	private DB db = new DB();

	//定义框架
	JFrame jf = new JFrame("图书增加界面");
	//定义面板
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("书名：");
	JLabel j2 = new JLabel("图书详细信息：");
	
	JTextField jtext1 = new JTextField(10);
	
	JTextArea jta=new JTextArea(10,20);
	
	JButton addButton = new JButton("增加");
	
	//定义存储系统界面的变量
	GLTuShuXinXi sys;
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//获取输入的值
		String name = jtext1.getText();
		String xinxi = jta.getText();

		
		String sql = "INSERT INTO t_tushu(name, xinxi) VALUES('"
				+ name + "', '" + xinxi + "');";
				
		System.out.println(sql);
		if (db.updataData(sql) == 1) {
//			System.out.println("增加成功");
			JOptionPane.showMessageDialog(null,"增加成功");
			// 销毁登录界面
			jf.dispose();
			//告诉系统界面对象，刷新数据
			new GLTuShuXinXi().initTuShuXinXi();
			} else {
			//System.out.println("增加失败");
				JOptionPane.showMessageDialog(null,"增加失败");
			}
			db.close();
			}
	
	
	public void showAddDialog(GLTuShuXinXi sys){
		
		//保存传递过来的系统界面对象
		this.sys = sys;
		
		// 设置框架大小
		jf.setSize(550, 280);
		// 设置位置
		jf.setLocation(400,100);
		// 设置框架可见
		jf.setVisible(true);
		
		jp.add(j1);
		jp.add(jtext1);
		jp.add(j2);
		jp.add(jta);


		jp.add(addButton);
		
		addButton.addActionListener(this);
		
		jf.add(jp);
	}
}
