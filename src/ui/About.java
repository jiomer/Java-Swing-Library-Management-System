package ui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.Main.DeleteListener;
import ui.Main.LoginActionListener;
import ui.Main.RegisterListener;
public class About {
	 JFrame frame = new JFrame();
	 JButton jbutton1;
	 Container content = frame.getContentPane();
	
	public About(){
		  frame.setTitle("about");
		  content.add(new JLabel("<html><font size='6' color='red'>大一比赛的作品</font>" +
				"<br>"+  
				"<font>基于swing做得图书管理系统</font>"+  
		  		"<p align='right'>-记忆角落</p></html>",JLabel.CENTER),BorderLayout.CENTER);
		  frame.setBounds(450, 200, 400, 400);
		  frame.setVisible(true);
}
	 public static void main(String[] args) {
		  // TODO Auto-generated method stub
		  new About();
		 }
}
