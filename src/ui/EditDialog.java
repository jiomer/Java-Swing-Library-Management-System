package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DB;


//ͼ���޸Ľ���
public class EditDialog implements ActionListener {

	private DB db = new DB();

	// ������
	JFrame jf = new JFrame("ͼ���޸Ľ���");
	// �������
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("������");
	JLabel j2 = new JLabel("ͼ����ϸ��Ϣ��");

	JLabel jl1 = new JLabel();
	JTextField jtext2 = new JTextField(10);
	JButton addButton = new JButton("�޸�");

	// ����洢ϵͳ����ı���
	GLTuShuXinXi sys;

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ��ȡ�����ֵ
		String name = jl1.getText();
		String xinxi = jtext2.getText();
		

		String sql ="UPDATE t_tushu SET name = '" + name + "'" +
				", xinxi = '"+ xinxi + "' WHERE name = '" + name + "'";

		if (db.updataData(sql) == 1) {
			JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
			//System.out.println("�޸ĳɹ�");
			// ����ϵͳ�������ˢ������
			sys.updateTable("");

			jf.dispose();
		} else {
			//System.out.println("�޸�ʧ��");
			JOptionPane.showMessageDialog(null, "�޸�ʧ��");
		}
		//�رճ���
		db.close();
	}
	//����һ�����ӽ���ķ���
	public void showAddDialog(GLTuShuXinXi sys, String id,String name,String xinxi) {

		// ���洫�ݹ�����ϵͳ�������
		this.sys = sys;

		// ���ÿ�ܴ�С
		jf.setSize(600, 300);
		// ����λ��
		jf.setLocation(600, 400);
		// ���ÿ�ܿɼ�
		jf.setVisible(true);
		
		jp.add(j1);
		jp.add(jl1);
		jl1.setText(name);

		jp.add(j2);
		jp.add(jtext2);
		jtext2.setText(xinxi);//���ݸ�showAddDialog()�е�xinxi
		

		jp.add(addButton);

		addButton.addActionListener(this);

		jf.add(jp);
	}
}
