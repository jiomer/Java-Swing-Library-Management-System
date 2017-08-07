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

	//������
	JFrame jf = new JFrame("�Ƽ�ͼ�����");
	//�������
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("������");
	JLabel j2 = new JLabel("��ţ�");
	JLabel j3 = new JLabel("���ߣ�");
	JLabel j4 = new JLabel("�����磺");
	
	JTextField jtext1 = new JTextField(10);
	JTextField jtext2 = new JTextField(10);
	JTextField jtext3 = new JTextField(10);
	JTextField jtext4 = new JTextField(10);
	
	JButton addButton = new JButton("�Ƽ�ͼ��");
	
	//����洢ϵͳ����ı���
	SystemDialog sys;
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ȡ�����ֵ
		String name = jtext1.getText();
		String bianhao = jtext2.getText();
		String zuozhe = jtext3.getText();
		String chubanshe = jtext4.getText();
		
		String sql = "INSERT INTO t_tj(name,bianhao,zuozhe,chubanshe) VALUES('"
				+ name + "', '" + bianhao + "', '" + zuozhe + "', '" + chubanshe + "');";
				
		System.out.println(sql);
		if (db.updataData(sql) == 1) {
			JOptionPane.showMessageDialog(null, "�Ƽ��ɹ��������");
			//System.out.println("�Ƽ��ɹ��������");

			// ���ٵ�¼����
			jf.dispose();
			} else {
			//System.out.println("�Ƽ�ʧ��");
				JOptionPane.showMessageDialog(null, "�Ƽ�ʧ��");
			}
			db.close();
			}


public void showTJAddDialog(SystemDialog sys){
		
		//���洫�ݹ�����ϵͳ�������
		this.sys =sys;
		
		// ���ÿ�ܴ�С
		jf.setSize(415, 350);
		// ����λ��
		jf.setLocation(400,100);
		// ���ÿ�ܿɼ�
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
		ImageIcon TP = new ImageIcon("3.jpg");// ���Ǳ���ͼƬ
		JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
		jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
		jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
		Container cp = jf.getContentPane();
		cp.setLayout(new BorderLayout());
		
		
		j1.setFont(new Font("������κ",Font.CENTER_BASELINE,17));
		j2.setFont(new Font("������κ",Font.CENTER_BASELINE,17));
		j3.setFont(new Font("������κ",Font.CENTER_BASELINE,17));
		j4.setFont(new Font("������κ",Font.CENTER_BASELINE,17));
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
