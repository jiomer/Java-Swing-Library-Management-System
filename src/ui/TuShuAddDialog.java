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

	//������
	JFrame jf = new JFrame("ͼ�����ӽ���");
	//�������
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("������");
	JLabel j2 = new JLabel("ͼ����ϸ��Ϣ��");
	
	JTextField jtext1 = new JTextField(10);
	
	JTextArea jta=new JTextArea(10,20);
	
	JButton addButton = new JButton("����");
	
	//����洢ϵͳ����ı���
	GLTuShuXinXi sys;
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ȡ�����ֵ
		String name = jtext1.getText();
		String xinxi = jta.getText();

		
		String sql = "INSERT INTO t_tushu(name, xinxi) VALUES('"
				+ name + "', '" + xinxi + "');";
				
		System.out.println(sql);
		if (db.updataData(sql) == 1) {
//			System.out.println("���ӳɹ�");
			JOptionPane.showMessageDialog(null,"���ӳɹ�");
			// ���ٵ�¼����
			jf.dispose();
			//����ϵͳ�������ˢ������
			new GLTuShuXinXi().initTuShuXinXi();
			} else {
			//System.out.println("����ʧ��");
				JOptionPane.showMessageDialog(null,"����ʧ��");
			}
			db.close();
			}
	
	
	public void showAddDialog(GLTuShuXinXi sys){
		
		//���洫�ݹ�����ϵͳ�������
		this.sys = sys;
		
		// ���ÿ�ܴ�С
		jf.setSize(550, 280);
		// ����λ��
		jf.setLocation(400,100);
		// ���ÿ�ܿɼ�
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
