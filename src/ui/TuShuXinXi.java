package ui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import model.TTuShu;
import dao.DB;
import dao.UserDao;

public class TuShuXinXi implements ActionListener{
private DB db = new DB();
	
	
	//������
	JFrame jf = new JFrame("ͼ����ϸ����");
	//�������
	JPanel jp = new JPanel();

	// ������
	JTable jt = null;
	// �������
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	JButton jb=new JButton("����");
	JTextField jtext = new JTextField(10);
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("����")){
			
			// ��ȡ�ؼ���
			String name = jtext.getText();
			System.out.println(name);
			// ���²�������Ϊname������
			updateTable(name);
		}
		}
		public void  updateTable(String name) {
		// 1 ��JScrollPanel���ɾ��
		jf.getContentPane().remove(jsp);
		// 2 �������������ݵ�ScrollPanel���ؽ���

		// �������ñ������
		setTableData1(name);
		// ˢ�¿��
		jf.validate();
		}
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_tushu";

		String sql = "select * from t_tushu";
		
		if (!name.equals("")) {
			
			sql_num = "select count(*) as num from t_tushu where name like '%" + name + "%'";
			sql = "select * from t_tushu where name like '%" + name + "%'";
		}
		
		int row = db.getNum(sql_num);
		tb = new Object[row][2];

		///////////////////////////////////////////
		//���ؽ�������м�¼��
		//����ϣ�����صĲ��ǽ���������Ƕ���
		List<TTuShu> t = db.getTTuShu(sql);
		int i = 0;
		for(TTuShu u : t){
			//��ȡ�����б��е����ݣ����η���������
			tb[i] = new Object[] {u.getName(), u.getXinxi()};
			i++;
		}
		db.close();


		// ��ͷ
		Object[] c = { "����", "ͼ����ϸ��Ϣ"};

		// ���������Ҫ�������ݺͱ�ͷ
		jt = new JTable(tb, c);

		// ��������ݼ������
		
		jsp = new JScrollPane(jt);
		// ����������
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//�¼�������
	public void initTuShuXinXi(){
		
		// //////////////////////////////////////////////////////////
			// ���ÿ�ܴ�С
			jf.setSize(600, 450);
			// ����λ��
			jf.setLocation(400,100);
			// ���ÿ�ܿɼ�
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////
			
			jp.add(jtext);
			jp.add(jb);
	
			jb.addActionListener(this);
			// ���ñ������
			setTableData1("");
			
			jf.add(jp);
			ImageIcon TP = new ImageIcon("9.jpg");// ���Ǳ���ͼƬ
			JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
			Container cp = jf.getContentPane();
			cp.setLayout(new BorderLayout());
			
			
			((JPanel) cp).setOpaque(false);
			((JPanel) jp).setOpaque(false); 
			jtext.setOpaque(false);
			jb.setOpaque(false);
			cp.add(jp);
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			jf.add(jsp);
			
			// ////////////////////////////////////////////////////////////////////

			// ���������ܣ����ñ���λ��
			jf.add(jp, BorderLayout.NORTH);
	}

}
