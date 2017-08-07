package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DB;

//让当前类处理按钮事件（必须让当前类成为事件类，并编写事件处理方法）
public class LoginDialog implements ActionListener {

	private DB db = new DB();
	
	//定义框架
	JFrame jf = new JFrame("登陆界面");
	JPanel jp = new JPanel(new GridLayout(5, 1));
	//定义面板
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	JPanel jp5 = new JPanel();
	//定义标签
	JLabel jl = new JLabel("图书管理系统");
	JLabel jl1 = new JLabel("用户名：");
	JLabel jl2 = new JLabel("密码：");
	JLabel jl3 = new JLabel("");
	//定义文本
	JTextField jt1 = new JTextField(10);
	JPasswordField jt2 = new JPasswordField(10);
	//定义按钮
	JButton jb1 = new JButton("管理员登录");
	JButton jb2 = new JButton("学 生 登 录");
	JButton jb3 = new JButton("注册");
	
	
	//事件处理方法
	
	public void actionPerformed(ActionEvent e) {
		
		//管理员登陆判断
		if(e.getSource()==jb1){
			String name = jt1.getText();
			String password = jt2.getText();
			
			if(db.glUser(name, password)){
				
//				System.out.println("登陆成功！欢迎来到图书馆！");
				JOptionPane.showMessageDialog(null, "欢迎管理员！您登陆成功，请进行修改！");
				//销毁登录界面
				jf.dispose();
				//进入主界面
				new GLSystemDialog().initSystemDialog(name);
				return;
			}else{
				//jl3.setText("用户名或密码错误！");
				JOptionPane.showMessageDialog(null, "管理员用户名或密码错误！");
			}
			db.close();
		}
		//学生账号登陆判断
		if(e.getSource()==jb2){
			String name = jt1.getText();
			String password = jt2.getText();
			
			if(db.findUser(name, password)){
//				System.out.println("登陆成功！欢迎来到图书馆！");
				JOptionPane.showMessageDialog(null, "登陆成功！欢迎来到图书馆！");
				//销毁登录界面
				jf.dispose();
				//进入主界面
				new SystemDialog().initSystemDialog(name);
				return;
			}else{
				//jl3.setText("用户名或密码错误！");
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
			db.close();
		}
		
		if(e.getSource()==jb3){
			new Register();
		}
		
	}
	
	//通过空间创建一个界面
	public void initLoginDialog(){
		
		ImageIcon TP = new ImageIcon("1.jpg");// 这是背景图片
		JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
		jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
		Container cp = jf.getContentPane();
		cp.setLayout(new BorderLayout());
		
		jl.setFont(new java.awt.Font("Dialog", 1, 35));//对话框(Dialog)
		//设置框架大小
		jf.setSize(600,450);
		//设置位置
		jf.setLocation(400, 100);
		//设置框架可见
		jf.setVisible(true);
		
		
		//将控件加入面板
		jp1.add(jl);
		jp2.add(jl1);
		jp2.add(jt1);
		jp3.add(jl2);
		jp3.add(jt2);
		jp4.add(jb1);
		jp4.add(jb2);
		jp5.add(jb3);
		jp5.add(jl3);
		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);
		jp.add(jp4);
		jp.add(jp5);
		//将面板加入框架
		cp.add(jp);
		
		((JPanel) cp).setOpaque(false);
		((JPanel) jp).setOpaque(false); 
		((JPanel) jp1).setOpaque(false); 
		((JPanel) jp2).setOpaque(false);
		((JPanel) jp3).setOpaque(false);
		((JPanel) jp4).setOpaque(false);
		((JPanel) jp5).setOpaque(false);
		
		jl.setFont(new Font("华文新魏",Font.CENTER_BASELINE,55));
		jl1.setFont(new Font("华文新魏",Font.CENTER_BASELINE,27));
		jl2.setFont(new Font("华文新魏",Font.CENTER_BASELINE,27));
		jl3.setFont(new Font("华文新魏",Font.CENTER_BASELINE,20));
		jl3.setForeground(Color.red);
		jb1.setFont(new Font("华文楷体",Font.CENTER_BASELINE,17));
		jb2.setFont(new Font("华文楷体",Font.CENTER_BASELINE,17));
		jb3.setFont(new Font("华文楷体",Font.CENTER_BASELINE,17));

		jt1.setOpaque(false);
		jt2.setOpaque(false);
		jb1.setOpaque(false);
		jb2.setOpaque(false);
		jb3.setOpaque(false);
		
		//将登录按钮事件委托给那个类处理
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	
	public static void main(String[] args) {
		new LoginDialog().initLoginDialog();
		
	}

}
