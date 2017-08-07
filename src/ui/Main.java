package ui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Main {
	
	 JFrame frame = new JFrame();
	 Container content = frame.getContentPane();
	 JMenuBar menubar = new JMenuBar();
	 JMenu LoginMenu = new JMenu("ϵͳ��¼");
	 JMenu UserMangeMenu = new JMenu("�û�����");
//	 JMenu TushuMenu = new JMenu("ͼ�����");
	 JMenu AboutMenu = new JMenu("����");
	 
	 
 	public Main(){
		  JMenuItem userLoginMenu= new JMenuItem("�û���¼");
		  userLoginMenu.addActionListener(new LoginActionListener());
		  
		  JMenuItem userRegister= new JMenuItem("�û�ע��");
		  userRegister.addActionListener(new RegisterListener());
		  
		  JMenuItem userDelete= new JMenuItem("�û�ɾ��");
		  userDelete.addActionListener(new DeleteListener());
		  
		  JMenuItem userUpdate= new JMenuItem("�����޸�");
		  userUpdate.addActionListener(new UpdateListener());
		  
//		  JMenuItem tushutuijian= new JMenuItem("ͼ���Ƽ�");
//		  tushutuijian.addActionListener(new tuijianLister());
		  
		  JMenuItem About= new JMenuItem("about");
		  About.addActionListener(new AboutListener());
		  
		  LoginMenu.add(userLoginMenu);
		  UserMangeMenu.add(userRegister);
		  UserMangeMenu.add(userDelete);
		  UserMangeMenu.add(userUpdate);
//		  TushuMenu.add(tushutuijian);
		  AboutMenu.add(About);
		  //LoginMenu.add(exitLoginMenu);
		  
		  
		  menubar.add(LoginMenu);
		  menubar.add(UserMangeMenu);
//		  menubar.add(TushuMenu);
		  menubar.add(AboutMenu);
		  frame.setTitle("ͼ�����ϵͳ");
		  content.add(menubar,BorderLayout.NORTH);
		  content.add(new JLabel("<html><font size='6' color='red'>��ӭʹ��ͼ�����ϵͳ</font></html>",JLabel.CENTER),BorderLayout.CENTER);
		//content.add(new JLabel("��ӭʹ��ѧ������ϵͳ",JLabel.CENTER),BorderLayout.CENTER);
		  frame.setBounds(450, 200, 400, 400);
		  frame.setVisible(true);
 }
 
 public class LoginActionListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
		 new LoginDialog().initLoginDialog();
	 }
 }
 public class RegisterListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
		 new Register();
	 }
 }
 public class DeleteListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
		 new Delete();
	 }
 }
 public class AboutListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
		 new About();
	 }
 }
 public class UpdateListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
		 new Update();
	 }
 }
// SystemDialog sys;
// public class tuijianLister implements ActionListener{
//	 public void actionPerformed(ActionEvent e) {
//		 new TJAddDialog().showTJAddDialog(sys);
//	 }
// }

 public static void main(String[] args) {
	  // TODO Auto-generated method stub
	  new Main();
	 }
 }