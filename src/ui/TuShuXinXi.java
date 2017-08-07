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
	
	
	//定义框架
	JFrame jf = new JFrame("图书详细界面");
	//定义面板
	JPanel jp = new JPanel();

	// 定义表格
	JTable jt = null;
	// 定义面板
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	JButton jb=new JButton("查找");
	JTextField jtext = new JTextField(10);
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getActionCommand().equals("查找")){
			
			// 获取关键字
			String name = jtext.getText();
			System.out.println(name);
			// 重新查找名字为name的数据
			updateTable(name);
		}
		}
		public void  updateTable(String name) {
		// 1 将JScrollPanel面板删除
		jf.getContentPane().remove(jsp);
		// 2 将加载了新数据的ScrollPanel加载进来

		// 重新设置表格数据
		setTableData1(name);
		// 刷新框架
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
		//返回结果集（行记录）
		//我们希望返回的不是结果集，而是对象
		List<TTuShu> t = db.getTTuShu(sql);
		int i = 0;
		for(TTuShu u : t){
			//获取数组列表中的数据，依次放入数组中
			tb[i] = new Object[] {u.getName(), u.getXinxi()};
			i++;
		}
		db.close();


		// 表头
		Object[] c = { "书名", "图书详细信息"};

		// 创建表格需要传入数据和表头
		jt = new JTable(tb, c);

		// 将表格数据加入面板
		
		jsp = new JScrollPane(jt);
		// 将面板加入框架
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//事件处理方法
	public void initTuShuXinXi(){
		
		// //////////////////////////////////////////////////////////
			// 设置框架大小
			jf.setSize(600, 450);
			// 设置位置
			jf.setLocation(400,100);
			// 设置框架可见
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////
			
			jp.add(jtext);
			jp.add(jb);
	
			jb.addActionListener(this);
			// 设置表格数据
			setTableData1("");
			
			jf.add(jp);
			ImageIcon TP = new ImageIcon("9.jpg");// 这是背景图片
			JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
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

			// 将面板加入框架，设置北边位置
			jf.add(jp, BorderLayout.NORTH);
	}

}
