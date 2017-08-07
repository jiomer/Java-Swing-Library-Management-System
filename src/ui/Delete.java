package ui;

import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DB;

//用户注册
public class Delete implements ActionListener{
	
	private DB db = new DB();
	
	JTextField jtf1;
	JTextField jtf2;
	//JTextField jtf3;
	JButton jbutton1;
	JLabel jlb5;
	
	public Delete(){
		JFrame jFrame=new JFrame();
		JPanel jp=new JPanel(new GridLayout(4, 1));
	
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		//JPanel jp4=new JPanel();
		JPanel jp5=new JPanel();
		
		JLabel jlb1=new JLabel("用户删除");
		jlb1.setFont(new java.awt.Font("Dialog",   1,   20)); 
	    jp1.add(jlb1);
		
		JLabel jlb2=new JLabel("用  户  名： ");
		jtf1=new JTextField(10);
		jp2.add(jlb2);
		jp2.add(jtf1);
		
		JLabel jlb3=new JLabel("密       码：  ");
		jtf2=new JTextField(10);
		jp3.add(jlb3);
		jp3.add(jtf2);
		
//		JLabel jlb4=new JLabel("确认密 码：");
//		jtf3=new JTextField(10);
//		jp4.add(jlb4);
//		jp4.add(jtf3);
		
		jbutton1=new JButton("确定");
		jbutton1.addActionListener(this);
		jlb5=new JLabel("");
		jp5.add(jbutton1);
		jp5.add(jlb5);
		
		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);
		//jp.add(jp4);
		jp.add(jp5);
		
		ImageIcon TP = new ImageIcon("9.jpg");// 这是背景图片
		JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
		jFrame.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
		Container cp = jFrame.getContentPane();  //创建一个容器
		cp.setLayout(new BorderLayout());
		
		jFrame.add(jp);		
		jFrame.setTitle("用户删除");
		jFrame.setSize(600,450);
		jFrame.setLocationRelativeTo(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
		cp.add(jp);
		
		((JPanel) cp).setOpaque(false);
		((JPanel) jp).setOpaque(false); 
		((JPanel) jp1).setOpaque(false); 
		((JPanel) jp2).setOpaque(false);
		((JPanel) jp3).setOpaque(false);
		//((JPanel) jp4).setOpaque(false);
		((JPanel) jp5).setOpaque(false);
		
		jlb1.setFont(new Font("华文新魏",Font.CENTER_BASELINE,45));
		jlb2.setFont(new Font("华文新魏",Font.CENTER_BASELINE,25));
		jlb3.setFont(new Font("华文新魏",Font.CENTER_BASELINE,25));
		jlb1.setForeground(Color.red);
		jlb2.setForeground(Color.WHITE);
		jlb3.setForeground(Color.WHITE);
		jlb5.setForeground(Color.red);
		jlb5.setFont(new Font("华文楷体",Font.CENTER_BASELINE,20));
		jtf1.setOpaque(false);
		jtf2.setOpaque(false);
		//jtf3.setOpaque(false);
		jbutton1.setOpaque(false);
		
		
	}

	public static void main(String[] args){
		new Delete();
		
	}

	public void actionPerformed(ActionEvent arg0) {
		//获取输入的值
		if(arg0.getSource()==jbutton1){
				String name= jtf1.getText();
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "请输入账号或密码！");
				}else{
					String sql = " DELETE FROM t_user WHERE name = '" + name + "'";
					System.out.println(sql);
					if (db.updataData(sql) == 1) {
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败，账号不存在！");
					}
					db.close();
					}
	}
	}
}

