package ui;


import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.TTuShu;
import dao.DB;
public class YHTuShuXinXi {
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
	
	
	//////////////////////////////////////////////
	
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_tushu";

		String sql = "select * from t_tushu";
		
		
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
