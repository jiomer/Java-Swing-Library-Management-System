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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.TTuShu;
import dao.DB;
import dao.UserDao;


//ͼ����ϸ��Ϣ����
public class GLTuShuXinXi implements ActionListener{
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
	
	JButton jb = new JButton("�޸�");
	JButton jb1 = new JButton("ɾ��");
	JButton jb2 = new JButton("����");
	
	
	//////////////////////////////////////////////
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		
		if(e.getActionCommand().equals("�޸�")){
			
			int row = jt.getSelectedRow(); 
			if(row == -1){
				System.out.println("�����Աѡ���޸ģ�");
				return;
			}
			//����ѡ����л�ȡID
			TableModel model = (TableModel)jt.getModel(); 		 
			String id = model.getValueAt(row,0).toString();
			String name = model.getValueAt(row,0).toString();
			String xinxi = model.getValueAt(row,1).toString();
			new EditDialog().showAddDialog(this, id, name, xinxi);
			//��ֵ��ֵ���ı���
			
			//�޸�
			
		}
		if(e.getActionCommand().equals("ɾ��")){
			System.out.println("ɾ���û�");
			
			//��ȡѡ�����
			int row = jt.getSelectedRow(); 
			if(row == -1){
				System.out.println("�����Աѡ��ɾ����");
				return;
			}
			//����ѡ����л�ȡID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//��װSQL���
			String sql = " DELETE FROM t_tushu WHERE name = '" + name + "'";
			//�������ݿ����ɾ��
			
			System.out.println(sql);
			
			if(db.updataData(sql) == 1){
				//System.out.println("ɾ���ɹ�");
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				
				//����ϵͳ�������ˢ������
				this.updateTable("");
				
			}else{
				//System.out.println("ɾ��ʧ��");
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
			}
			db.close();
		}
		if(e.getSource()==jb2){
			jf.dispose();
			new TuShuAddDialog().showAddDialog(null);
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

			// ���ñ������
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////
			// ���齨���ؽ����
			jp.add(jb);
			jp.add(jb1);
			jp.add(jb2);
			
			jf.add(jp);
			ImageIcon TP = new ImageIcon("9.jpg");// ���Ǳ���ͼƬ
			JLabel jl0 = new JLabel(TP);// ������ͼ���ڱ�ǩ�
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// ע�������ǹؼ�����������ǩ��ӵ�jfram��LayeredPane����
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// ���ñ�����ǩ��λ��
			Container cp = jf.getContentPane();
			cp.setLayout(new BorderLayout());
			
			
			((JPanel) cp).setOpaque(false);
			((JPanel) jp).setOpaque(false); 


			cp.add(jp);
			
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			jf.add(jsp);
			// �����Ұ�ť�󶨴����¼���

			jb.addActionListener(this);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			// ���������ܣ����ñ���λ��
			jf.add(jp, BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		new GLTuShuXinXi().initTuShuXinXi();	
	}

}
