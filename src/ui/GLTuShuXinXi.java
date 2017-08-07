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


//图书详细信息管理
public class GLTuShuXinXi implements ActionListener{
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
	
	JButton jb = new JButton("修改");
	JButton jb1 = new JButton("删除");
	JButton jb2 = new JButton("增加");
	
	
	//////////////////////////////////////////////
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getActionCommand().equals("修改")){
			
			int row = jt.getSelectedRow(); 
			if(row == -1){
				System.out.println("请管理员选择修改！");
				return;
			}
			//依据选择的行获取ID
			TableModel model = (TableModel)jt.getModel(); 		 
			String id = model.getValueAt(row,0).toString();
			String name = model.getValueAt(row,0).toString();
			String xinxi = model.getValueAt(row,1).toString();
			new EditDialog().showAddDialog(this, id, name, xinxi);
			//将值赋值给文本框
			
			//修改
			
		}
		if(e.getActionCommand().equals("删除")){
			System.out.println("删除用户");
			
			//获取选择的行
			int row = jt.getSelectedRow(); 
			if(row == -1){
				System.out.println("请管理员选择删除！");
				return;
			}
			//依据选择的行获取ID
			TableModel model = (TableModel)jt.getModel(); 	
			
			String name = model.getValueAt(row,0).toString();
			System.out.println(name);
			//组装SQL语句
			String sql = " DELETE FROM t_tushu WHERE name = '" + name + "'";
			//链接数据库进行删除
			
			System.out.println(sql);
			
			if(db.updataData(sql) == 1){
				//System.out.println("删除成功");
				JOptionPane.showMessageDialog(null, "删除成功");
				
				//告诉系统界面对象，刷新数据
				this.updateTable("");
				
			}else{
				//System.out.println("删除失败");
				JOptionPane.showMessageDialog(null, "删除失败");
			}
			db.close();
		}
		if(e.getSource()==jb2){
			jf.dispose();
			new TuShuAddDialog().showAddDialog(null);
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

			// 设置表格数据
			setTableData1("");
			// ////////////////////////////////////////////////////////////////////
			// 将组建加载进面板
			jp.add(jb);
			jp.add(jb1);
			jp.add(jb2);
			
			jf.add(jp);
			ImageIcon TP = new ImageIcon("9.jpg");// 这是背景图片
			JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
			Container cp = jf.getContentPane();
			cp.setLayout(new BorderLayout());
			
			
			((JPanel) cp).setOpaque(false);
			((JPanel) jp).setOpaque(false); 


			cp.add(jp);
			
			jsp.setOpaque(false);
			jsp.getViewport().setOpaque(false);
			jf.add(jsp);
			// 将查找按钮绑定处理事件类

			jb.addActionListener(this);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			// 将面板加入框架，设置北边位置
			jf.add(jp, BorderLayout.NORTH);
	}
	public static void main(String[] args) {
		new GLTuShuXinXi().initTuShuXinXi();	
	}

}
