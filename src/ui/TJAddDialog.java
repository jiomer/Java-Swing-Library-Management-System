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


public class TJAddDialog implements ActionListener {
	
	private DB db = new DB();

	//定义框架
	JFrame jf = new JFrame("推荐图书界面");
	//定义面板
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("书名：");
	JLabel j2 = new JLabel("编号：");
	JLabel j3 = new JLabel("作者：");
	JLabel j4 = new JLabel("出版社：");
	
	JTextField jtext1 = new JTextField(10);
	JTextField jtext2 = new JTextField(10);
	JTextField jtext3 = new JTextField(10);
	JTextField jtext4 = new JTextField(10);
	
	JButton addButton = new JButton("推荐图书");
	
	//定义存储系统界面的变量
	SystemDialog sys;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//获取输入的值
		String name = jtext1.getText();
		String bianhao = jtext2.getText();
		String zuozhe = jtext3.getText();
		String chubanshe = jtext4.getText();
		
		String sql = "INSERT INTO t_tj(name,bianhao,zuozhe,chubanshe) VALUES('"
				+ name + "', '" + bianhao + "', '" + zuozhe + "', '" + chubanshe + "');";
				
		System.out.println(sql);
		if (db.updataData(sql) == 1) {
			JOptionPane.showMessageDialog(null, "推荐成功，待审核");
			//System.out.println("推荐成功，待审核");

			// 销毁登录界面
			jf.dispose();
			} else {
			//System.out.println("推荐失败");
				JOptionPane.showMessageDialog(null, "推荐失败");
			}
			db.close();
			}


public void showTJAddDialog(SystemDialog sys){
		
		//保存传递过来的系统界面对象
		this.sys =sys;
		
		// 设置框架大小
		jf.setSize(415, 350);
		// 设置位置
		jf.setLocation(400,100);
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
		
		jf.add(jp);
		ImageIcon TP = new ImageIcon("3.jpg");// 这是背景图片
		JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
		jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
		Container cp = jf.getContentPane();
		cp.setLayout(new BorderLayout());
		
		
		j1.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j2.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j3.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		j4.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
		((JPanel) cp).setOpaque(false);
		((JPanel) jp).setOpaque(false); 
		jtext1.setOpaque(false);
		jtext2.setOpaque(false);
		jtext3.setOpaque(false);
		jtext4.setOpaque(false);
		cp.add(jp);
		
		
		jp.add(addButton);
		addButton.addActionListener(this);
		
		jf.add(jp);
		
		
	}
}
