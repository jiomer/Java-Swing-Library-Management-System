package ui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.JShu;
import model.TJShu;
import model.TTuShu;
import dao.DB;
import dao.UserDao;

public class TJXXDialog implements ActionListener{

	private DB db = new DB();
	// ����洢ϵͳ����ı���
	GLSystemDialog sys;
	//������
	JFrame jf = new JFrame("�Ƽ�ͼ����ϸ����");
	//�������
	JPanel jp = new JPanel();

	// ������
	JTable jt = null;
	// �������
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	JButton jb1=new JButton("ͬ��");
	JButton jb2=new JButton("�ܾ�");
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			int row = jt.getSelectedRow(); 
			if(row == -1){
				//System.out.println("��ѡ��ͬ���ͼ�飡");
				JOptionPane.showMessageDialog(null, "��ѡ��ͬ���ͼ�飡");
				return;
			}
			//����ѡ����л�ȡID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//��װSQL���
			
			String sql ="insert into t_shu select * from t_tj where t_tj.name='" + name + "'";
			String sql1 = " DELETE FROM t_tj WHERE name = '" + name + "'";
					
			System.out.println(sql);
			System.out.println(sql1);
			if(db.updataData(sql) == 1){
				//System.out.println("�ɹ�");
				JOptionPane.showMessageDialog(null, "�ɹ�");
				this.updateTable("");
				}else{
					//System.out.println("ʧ��");
					JOptionPane.showMessageDialog(null, "ʧ�ܣ�");
			}
				
			if (db.updataData(sql1) == 1) {
				//System.out.println("�����ɹ�");
				JOptionPane.showMessageDialog(null, "�Ƽ��ɹ���");
				// ���ٽ���
				jf.dispose();
				new GLSystemDialog().initSystemDialog(name);
				
				} else {
				//System.out.println("����ʧ��");
					JOptionPane.showMessageDialog(null, "�Ƽ�ʧ�ܣ�");
				}
				db.close();
				}
		
		if(e.getSource()==jb2){
			
			//��ȡѡ�����
			int row = jt.getSelectedRow(); 
			if(row == -1){
				//System.out.println("��ѡ��ܾ���ͼ�飡");
				JOptionPane.showMessageDialog(null, "��ѡ��ܾ���ͼ�飡");
				return;
			}
			//����ѡ����л�ȡID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//��װSQL���
			String sql = " DELETE FROM t_tj WHERE name = '" + name + "'";
			//�������ݿ����ɾ��
			
			System.out.println(sql);
				
			if(db.updataData(sql) == 1){
				//System.out.println("�ܾ��ɹ�");
				JOptionPane.showMessageDialog(null, "�ܾ��ɹ�");
				//����ϵͳ�������ˢ������
				
				this.updateTable("");
			}else{
				//System.out.println("�ܾ�ʧ��");
				JOptionPane.showMessageDialog(null, "�ܾ�ʧ��");
			}
			db.close();
			}
	}
	
	
		public void  updateTable(String name) {
		// 1 ��JScrollPanel���ɾ��
		jf.getContentPane().remove(jsp);

		// �������ñ������
		setTableData1(name);
		// ˢ�¿��
		jf.validate();
		}
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_tj";

		String sql = "select * from t_tj";
		
		
		int row = db.getNum(sql_num);
		tb = new Object[row][4];

		///////////////////////////////////////////
		
		List<TJShu> f = db.getTJShu(sql);
		int i = 0;
		for(TJShu u : f){
			//��ȡ�����б��е����ݣ����η���������
			tb[i] = new Object[] {u.getName(), u.getBianhao(), u.getZuozhe(), u.getChubanshe()};
			i++;
		}
		db.close();


		// ��ͷ
		Object[] c = { "����", "���", "����", "������"};

		// ���������Ҫ�������ݺͱ�ͷ
		jt = new JTable(tb, c);

		// ��������ݼ������
		
		jsp = new JScrollPane(jt);
		// ����������
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//�¼�������
	public void initTJXXDialog(){
		
		// //////////////////////////////////////////////////////////
			// ���ÿ�ܴ�С
			jf.setSize(600, 300);
			// ����λ��
			jf.setLocation(400,100);
			// ���ÿ�ܿɼ�
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			jp.add(jb1);
			jp.add(jb2);
			
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			// ���ñ������
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////

			// ���������ܣ����ñ���λ��
			jf.add(jp, BorderLayout.NORTH);
	}

}
