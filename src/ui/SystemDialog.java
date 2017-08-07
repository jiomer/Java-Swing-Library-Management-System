package ui;
import java.awt.BorderLayout;
import java.awt.Color;
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

import model.TShu;
import dao.DB;
import dao.UserDao;
//�û�ͼ��������
	public class SystemDialog implements ActionListener {
		
		private DB db = new DB();
		
		// ������
		JFrame jf = new JFrame("ͼ���ѯ����");
		// ������
		JTable jt = null;
		// �������
		JScrollPane jsp = null;

		Object[][] tb = null;
		Object[][] tb1 = null;
		Object[][] tb2 = null;
		// �����������
		JPanel jp = new JPanel();
		JTextField jtext = new JTextField(10);
		JButton jb = new JButton("����");
		JButton jb1 = new JButton("�Ƿ����");
		JButton jb2 = new JButton("�Ƽ�ͼ��");
		JButton jb3 = new JButton("ͼ����ϸ��Ϣ");
		
		JLabel jl = new JLabel("");

		// �¼�������
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getActionCommand().equals("����")){
				
				// ��ȡ�ؼ���
				String name = jtext.getText();
				System.out.println(name);
				// ���²�������Ϊname������
				updateTable(name);
				jsp.setOpaque(false);
				jsp.getViewport().setOpaque(false);
				
			}
			//if(e.getActionCommand().equals("�Ƿ����")){
			if(e.getSource()==jb1){	
				//System.out.println("�򿪽���Ի���");
				//����ǰ����
				new AddDialog().showAddDialog(this);
			}
			if(e.getSource()==jb2){
				new TJAddDialog().showTJAddDialog(null);
			}
			if(e.getSource()==jb3){
				new TuShuXinXi().initTuShuXinXi();
			}
		}
		//��д�������ˢ�·���
		public void  updateTable(String name) {
			// �����ݸ�ֵ������滻��ԭ��������
			// 1 ��JScrollPanel���ɾ��
			jf.getContentPane().remove(jsp);
			// 2 �������������ݵ�ScrollPanel���ؽ���

			// �������ñ������
			setTableData1(name);
			// ˢ�¿��
			jf.validate();
		}

		// ////////////////////////////////////////////////////////////////////
		// ���ñ������
		public void setTableData1(String name) {
			
			String sql_num = "select count(*) as num from t_shu";

			String sql = "select * from t_shu";
			
			if (!name.equals("")) {
				
				sql_num = "select count(*) as num from t_shu where name like '%" + name + "%'";
				sql = "select * from t_shu where name like '%" + name + "%'";
			}
			
			//�������ݿ⣬�ж��Ƿ��е�ǰ������û�������ƥ�������
		
			
			
			int row = db.getNum(sql_num);
			tb = new Object[row][4];

			///////////////////////////////////////////
			//���ؽ�������м�¼��
			//����ϣ�����صĲ��ǽ���������Ƕ���
			List<TShu> l = db.getTusers(sql);
			int i = 0;
			for(TShu u : l){
				//��ȡ�����б��е����ݣ����η���������
				tb[i] = new Object[] {u.getName(), u.getBianhao(), u.getZuozhe(), 
								u.getCBS()};
				i++;
			}
			db.close();


			// ��ͷ
			Object[] c = { "����", "���","����" ,"������"};

			// ���������Ҫ�������ݺͱ�ͷ
			jt = new JTable(tb, c);

			// ��������ݼ������
			jsp = new JScrollPane(jt);
			// ����������
			jf.add(jsp);
		}

		// ��ʼ��ϵͳ������
		public void initSystemDialog(String name) {

			// //////////////////////////////////////////////////////////
			// ���ÿ�ܴ�С
			jf.setSize(600, 505);
			// ����λ��
			jf.setLocation(400,100);
			// ���ÿ�ܿɼ�
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			// ���ñ������
			setTableData1("");

			// ////////////////////////////////////////////////////////////////////
			// ���齨���ؽ����
			jp.add(jtext);
			jp.add(jb);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);

			// �����Ұ�ť�󶨴����¼���

			jb.addActionListener(this);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb3.addActionListener(this);
			
			ImageIcon TP = new ImageIcon("8.jpg");// ���Ǳ���ͼƬ
			JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
			Container cp = jf.getContentPane();
			cp.setLayout(new BorderLayout());
			
			jtext.setFont(new Font("������κ",Font.CENTER_BASELINE,17));
			jl.setForeground(Color.red);

			((JPanel) cp).setOpaque(false);
			((JPanel) jp).setOpaque(false); 
			jtext.setOpaque(false);
			jb.setOpaque(false);
			jb1.setOpaque(false);
			jb2.setOpaque(false);
			jb3.setOpaque(false);
			cp.add(jp);
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			jf.add(jsp);
			// ���������ܣ����ñ���λ��
			
			jl.setText(name + "����ӭ����ϵͳ��");
			jf.add(jl, BorderLayout.SOUTH);
			jf.add(jp, BorderLayout.NORTH);
			// ///////////////////////////////////////////////////////////////////////

		}
	}