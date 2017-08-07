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
	
	
	//定义框架
	JFrame jf = new JFrame("图书详细界面");
	//定义面板
	JPanel jp = new JPanel();

	// 定义表格
	JTable jt = null;
	// 定义面板
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	
	//////////////////////////////////////////////
	
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_tushu";

		String sql = "select * from t_tushu";
		
		
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
			jf.setSize(600, 300);
			// 设置位置
			jf.setLocation(400,100);
			// 设置框架可见
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			// 设置表格数据
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////
		
			// 将面板加入框架，设置北边位置
			jf.add(jp, BorderLayout.NORTH);
	}

}
