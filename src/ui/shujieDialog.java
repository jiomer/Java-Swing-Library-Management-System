package ui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.JShu;
import model.TTuShu;
import dao.DB;
import dao.UserDao;


//���鴰��
public class shujieDialog {
private DB db = new DB();
	
	
	//������
	JFrame jf = new JFrame("������ϸ����");
	//�������
	JPanel jp = new JPanel();

	// ������
	JTable jt = null;
	// �������
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	
		public void  updateTable(String name) {
		// 1 ��JScrollPanel���ɾ��
		jf.getContentPane().remove(jsp);
		// 2 �������������ݵ�ScrollPanel���ؽ���

		// �������ñ������
		setTableData1(name);
		// ˢ�¿��
		jf.validate(); 	//ȷ�����������Ч�Ĳ��֡�������Ҫ�������� Container ʵ���Ͻ��в�����
		}
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_jieshu";

		String sql = "select * from t_jieshu";
		
		
		int row = db.getNum(sql_num);
		tb = new Object[row][4];

		///////////////////////////////////////////
		//���ؽ�������м�¼��
		//����ϣ�����صĲ��ǽ���������Ƕ���
		List<JShu> f = db.getJShu(sql);//ʹ�÷���ָ��Jshu
		int i = 0;
		for(JShu u : f){
			//��ȡ�����б��е����ݣ����η���������
			tb[i] = new Object[] {u.getXuehao(), u.getDep(),u.getLet(),u.getName()};
			i++;
		}
		db.close();


		// ��ͷ
		Object[] c = { "ѧ��", "רҵ�༶", "��ϵ�绰", "ͼ����Ϣ"};

		// ���������Ҫ�������ݺͱ�ͷ
		jt = new JTable(tb, c);

		// ��������ݼ������
		
		jsp = new JScrollPane(jt);
		// ����������
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//�¼�������
	public void initshujieDialog(){
		
		// //////////////////////////////////////////////////////////
			// ���ÿ�ܴ�С
			jf.setSize(600, 300);
			// ����λ��
			jf.setLocation(400,100);
			// ���ÿ�ܿɼ�
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			// ���ñ������
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////

			// ���������ܣ����ñ���λ��
			jf.add(jp, BorderLayout.NORTH);
	}

}
