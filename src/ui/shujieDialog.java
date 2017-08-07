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


//借书窗口
public class shujieDialog {
private DB db = new DB();
	
	
	//定义框架
	JFrame jf = new JFrame("借书详细界面");
	//定义面板
	JPanel jp = new JPanel();

	// 定义表格
	JTable jt = null;
	// 定义面板
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	
		public void  updateTable(String name) {
		// 1 将JScrollPanel面板删除
		jf.getContentPane().remove(jsp);
		// 2 将加载了新数据的ScrollPanel加载进来

		// 重新设置表格数据
		setTableData1(name);
		// 刷新框架
		jf.validate(); 	//确保组件具有有效的布局。此类主要适用于在 Container 实例上进行操作。
		}
		
		public void setTableData1(String name) {
		String sql_num = "select count(*) as num from t_jieshu";

		String sql = "select * from t_jieshu";
		
		
		int row = db.getNum(sql_num);
		tb = new Object[row][4];

		///////////////////////////////////////////
		//返回结果集（行记录）
		//我们希望返回的不是结果集，而是对象
		List<JShu> f = db.getJShu(sql);//使用泛型指定Jshu
		int i = 0;
		for(JShu u : f){
			//获取数组列表中的数据，依次放入数组中
			tb[i] = new Object[] {u.getXuehao(), u.getDep(),u.getLet(),u.getName()};
			i++;
		}
		db.close();


		// 表头
		Object[] c = { "学号", "专业班级", "联系电话", "图书信息"};

		// 创建表格需要传入数据和表头
		jt = new JTable(tb, c);

		// 将表格数据加入面板
		
		jsp = new JScrollPane(jt);
		// 将面板加入框架
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//事件处理方法
	public void initshujieDialog(){
		
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
