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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DB;

//�û�ע��
public class Register implements ActionListener{
	
	private DB db = new DB();
	
	JTextField jtf1;
	JTextField jtf2;
	JTextField jtf3;
	JButton jbutton1;
	JLabel jlb5;
	
	public Register(){
		JFrame jFrame=new JFrame();
		JPanel jp=new JPanel(new GridLayout(5, 1));
	
		JPanel jp1=new JPanel();
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel();
		JPanel jp4=new JPanel();
		JPanel jp5=new JPanel();
		
		JLabel jlb1=new JLabel("�û�ע��");
		jlb1.setFont(new java.awt.Font("Dialog",   1,   20)); 
	    jp1.add(jlb1);
		
		JLabel jlb2=new JLabel("��  ��  ���� ");
		jtf1=new JTextField(10);
		jp2.add(jlb2);
		jp2.add(jtf1);
		
		JLabel jlb3=new JLabel("��       �룺  ");
		jtf2=new JTextField(10);
		jp3.add(jlb3);
		jp3.add(jtf2);
		
		JLabel jlb4=new JLabel("ȷ���� �룺");
		jtf3=new JTextField(10);
		jp4.add(jlb4);
		jp4.add(jtf3);
		
		jbutton1=new JButton("ȷ��");
		jbutton1.addActionListener(this);
		jlb5=new JLabel("");
		jp5.add(jbutton1);
		jp5.add(jlb5);
		
		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);
		jp.add(jp4);
		jp.add(jp5);
		
		ImageIcon TP = new ImageIcon("2.jpg");// ���Ǳ���ͼƬ
		JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
		jFrame.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
		Container cp = jFrame.getContentPane();  //����һ������
		cp.setLayout(new BorderLayout());
		
		jFrame.add(jp);		
		jFrame.setTitle("ע�����");
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
		((JPanel) jp4).setOpaque(false);
		((JPanel) jp5).setOpaque(false);
		
		jlb1.setFont(new Font("������κ",Font.CENTER_BASELINE,45));
		jlb2.setFont(new Font("������κ",Font.CENTER_BASELINE,25));
		jlb3.setFont(new Font("������κ",Font.CENTER_BASELINE,25));
		jlb4.setFont(new Font("������κ",Font.CENTER_BASELINE,25));
		jlb1.setForeground(Color.red);
		jlb2.setForeground(Color.WHITE);
		jlb3.setForeground(Color.WHITE);
		jlb4.setForeground(Color.WHITE);
		jlb5.setForeground(Color.red);
		jlb5.setFont(new Font("���Ŀ���",Font.CENTER_BASELINE,20));
		jtf1.setOpaque(false);
		jtf2.setOpaque(false);
		jtf3.setOpaque(false);
		jbutton1.setOpaque(false);
		
		
	}

	public static void main(String[] args){
		new Register();
		
	}

	public void actionPerformed(ActionEvent arg0) {
		//��ȡ�����ֵ
		if(arg0.getSource()==jbutton1){
				String name= jtf1.getText();
				String password = jtf2.getText();
				String password2=jtf3.getText();
				
				if(name.equals("")){
					//jlb5.setText("�������˺Ż����룡");
					JOptionPane.showMessageDialog(null, "�������˺Ż����룡");
				}else{
					if(password.equals(password2)){
					
				
				String sql = "INSERT INTO t_user(name, password) VALUES('"
						+ name + "', '" + password + "');";
						
				System.out.println(sql);
				if (db.updataData(sql) == 1) {
					//jlb5.setText("ע��ɹ���");
					JOptionPane.showMessageDialog(null, "ע��ɹ�");
				
				} else {
					//jlb5.setText("�˺��Ѵ��ڣ�");
					JOptionPane.showMessageDialog(null, "�˺��Ѵ��ڣ�");
				}
				db.close();
				}else{
					//jlb5.setText("���벻һ��");
					JOptionPane.showMessageDialog(null, "���벻һ��");
				}
			}
		}
	}
}

