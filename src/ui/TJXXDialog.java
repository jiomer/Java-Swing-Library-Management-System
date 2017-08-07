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
	// 定义存储系统界面的变量
	GLSystemDialog sys;
	//定义框架
	JFrame jf = new JFrame("推荐图书详细界面");
	//定义面板
	JPanel jp = new JPanel();

	// 定义表格
	JTable jt = null;
	// 定义面板
	JScrollPane jsp = null;
	
	Object[][] tb = null;
	
	JButton jb1=new JButton("同意");
	JButton jb2=new JButton("拒绝");
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			int row = jt.getSelectedRow(); 
			if(row == -1){
				//System.out.println("请选择同意的图书！");
				JOptionPane.showMessageDialog(null, "请选择同意的图书！");
				return;
			}
			//依据选择的行获取ID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//组装SQL语句
			
			String sql ="insert into t_shu select * from t_tj where t_tj.name='" + name + "'";
			String sql1 = " DELETE FROM t_tj WHERE name = '" + name + "'";
					
			System.out.println(sql);
			System.out.println(sql1);
			if(db.updataData(sql) == 1){
				//System.out.println("成功");
				JOptionPane.showMessageDialog(null, "成功");
				this.updateTable("");
				}else{
					//System.out.println("失败");
					JOptionPane.showMessageDialog(null, "失败！");
			}
				
			if (db.updataData(sql1) == 1) {
				//System.out.println("操作成功");
				JOptionPane.showMessageDialog(null, "推荐成功！");
				// 销毁界面
				jf.dispose();
				new GLSystemDialog().initSystemDialog(name);
				
				} else {
				//System.out.println("操作失败");
					JOptionPane.showMessageDialog(null, "推荐失败！");
				}
				db.close();
				}
		
		if(e.getSource()==jb2){
			
			//获取选择的行
			int row = jt.getSelectedRow(); 
			if(row == -1){
				//System.out.println("请选择拒绝的图书！");
				JOptionPane.showMessageDialog(null, "请选择拒绝的图书！");
				return;
			}
			//依据选择的行获取ID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//组装SQL语句
			String sql = " DELETE FROM t_tj WHERE name = '" + name + "'";
			//链接数据库进行删除
			
			System.out.println(sql);
				
			if(db.updataData(sql) == 1){
				//System.out.println("拒绝成功");
				JOptionPane.showMessageDialog(null, "拒绝成功");
				//告诉系统界面对象，刷新数据
				
				this.updateTable("");
			}else{
				//System.out.println("拒绝失败");
				JOptionPane.showMessageDialog(null, "拒绝失败");
			}
			db.close();
			}
	}
	
	
		public void  updateTable(String name) {
		// 1 将JScrollPanel面板删除
		jf.getContentPane().remove(jsp);

		// 重新设置表格数据
		setTableData1(name);
		// 刷新框架
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
			//获取数组列表中的数据，依次放入数组中
			tb[i] = new Object[] {u.getName(), u.getBianhao(), u.getZuozhe(), u.getChubanshe()};
			i++;
		}
		db.close();


		// 表头
		Object[] c = { "书名", "编号", "作者", "出版社"};

		// 创建表格需要传入数据和表头
		jt = new JTable(tb, c);

		// 将表格数据加入面板
		
		jsp = new JScrollPane(jt);
		// 将面板加入框架
		jf.add(jsp);
	
	}
	////////////////////////////////////////////////

	//事件处理方法
	public void initTJXXDialog(){
		
		// //////////////////////////////////////////////////////////
			// 设置框架大小
			jf.setSize(600, 300);
			// 设置位置
			jf.setLocation(400,100);
			// 设置框架可见
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			jp.add(jb1);
			jp.add(jb2);
			
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			// 设置表格数据
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////

			// 将面板加入框架，设置北边位置
			jf.add(jp, BorderLayout.NORTH);
	}

}
