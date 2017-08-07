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

//�õ�ǰ�ദ��ť�¼��������õ�ǰ���Ϊ�¼��࣬����д�¼���������
public class LoginDialog implements ActionListener {

	private DB db = new DB();
	
	//������
	JFrame jf = new JFrame("��½����");
	JPanel jp = new JPanel(new GridLayout(5, 1));
	//�������
	JPanel jp1 = new JPanel();
	JPanel jp2 = new JPanel();
	JPanel jp3 = new JPanel();
	JPanel jp4 = new JPanel();
	JPanel jp5 = new JPanel();
	//�����ǩ
	JLabel jl = new JLabel("ͼ�����ϵͳ");
	JLabel jl1 = new JLabel("�û�����");
	JLabel jl2 = new JLabel("���룺");
	JLabel jl3 = new JLabel("");
	//�����ı�
	JTextField jt1 = new JTextField(10);
	JPasswordField jt2 = new JPasswordField(10);
	//���尴ť
	JButton jb1 = new JButton("����Ա��¼");
	JButton jb2 = new JButton("ѧ �� �� ¼");
	JButton jb3 = new JButton("ע��");
	
	
	//�¼�������
	
	public void actionPerformed(ActionEvent e) {
		
		//����Ա��½�ж�
		if(e.getSource()==jb1){
			String name = jt1.getText();
			String password = jt2.getText();
			
			if(db.glUser(name, password)){
				
//				System.out.println("��½�ɹ�����ӭ����ͼ��ݣ�");
				JOptionPane.showMessageDialog(null, "��ӭ����Ա������½�ɹ���������޸ģ�");
				//���ٵ�¼����
				jf.dispose();
				//����������
				new GLSystemDialog().initSystemDialog(name);
				return;
			}else{
				//jl3.setText("�û������������");
				JOptionPane.showMessageDialog(null, "����Ա�û������������");
			}
			db.close();
		}
		//ѧ���˺ŵ�½�ж�
		if(e.getSource()==jb2){
			String name = jt1.getText();
			String password = jt2.getText();
			
			if(db.findUser(name, password)){
//				System.out.println("��½�ɹ�����ӭ����ͼ��ݣ�");
				JOptionPane.showMessageDialog(null, "��½�ɹ�����ӭ����ͼ��ݣ�");
				//���ٵ�¼����
				jf.dispose();
				//����������
				new SystemDialog().initSystemDialog(name);
				return;
			}else{
				//jl3.setText("�û������������");
				JOptionPane.showMessageDialog(null, "�û������������");
			}
			db.close();
		}
		
		if(e.getSource()==jb3){
			new Register();
		}
		
	}
	
	//ͨ���ռ䴴��һ������
	public void initLoginDialog(){
		
		ImageIcon TP = new ImageIcon("1.jpg");// ���Ǳ���ͼƬ
		JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
		jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
		Container cp = jf.getContentPane();
		cp.setLayout(new BorderLayout());
		
		jl.setFont(new java.awt.Font("Dialog", 1, 35));//�Ի���(Dialog)
		//���ÿ�ܴ�С
		jf.setSize(600,450);
		//����λ��
		jf.setLocation(400, 100);
		//���ÿ�ܿɼ�
		jf.setVisible(true);
		
		
		//���ؼ��������
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
		//����������
		cp.add(jp);
		
		((JPanel) cp).setOpaque(false);
		((JPanel) jp).setOpaque(false); 
		((JPanel) jp1).setOpaque(false); 
		((JPanel) jp2).setOpaque(false);
		((JPanel) jp3).setOpaque(false);
		((JPanel) jp4).setOpaque(false);
		((JPanel) jp5).setOpaque(false);
		
		jl.setFont(new Font("������κ",Font.CENTER_BASELINE,55));
		jl1.setFont(new Font("������κ",Font.CENTER_BASELINE,27));
		jl2.setFont(new Font("������κ",Font.CENTER_BASELINE,27));
		jl3.setFont(new Font("������κ",Font.CENTER_BASELINE,20));
		jl3.setForeground(Color.red);
		jb1.setFont(new Font("���Ŀ���",Font.CENTER_BASELINE,17));
		jb2.setFont(new Font("���Ŀ���",Font.CENTER_BASELINE,17));
		jb3.setFont(new Font("���Ŀ���",Font.CENTER_BASELINE,17));

		jt1.setOpaque(false);
		jt2.setOpaque(false);
		jb1.setOpaque(false);
		jb2.setOpaque(false);
		jb3.setOpaque(false);
		
		//����¼��ť�¼�ί�и��Ǹ��ദ��
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	
	
	public static void main(String[] args) {
		new LoginDialog().initLoginDialog();
		
	}

}
