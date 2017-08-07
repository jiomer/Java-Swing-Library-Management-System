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
//用户图书管理界面
	public class SystemDialog implements ActionListener {
		
		private DB db = new DB();
		
		// 定义框架
		JFrame jf = new JFrame("图书查询界面");
		// 定义表格
		JTable jt = null;
		// 定义面板
		JScrollPane jsp = null;

		Object[][] tb = null;
		Object[][] tb1 = null;
		Object[][] tb2 = null;
		// 定义搜索面板
		JPanel jp = new JPanel();
		JTextField jtext = new JTextField(10);
		JButton jb = new JButton("查找");
		JButton jb1 = new JButton("是否借书");
		JButton jb2 = new JButton("推荐图书");
		JButton jb3 = new JButton("图书详细信息");
		
		JLabel jl = new JLabel("");

		// 事件处理方法
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getActionCommand().equals("查找")){
				
				// 获取关键字
				String name = jtext.getText();
				System.out.println(name);
				// 重新查找名字为name的数据
				updateTable(name);
				jsp.setOpaque(false);
				jsp.getViewport().setOpaque(false);
				
			}
			//if(e.getActionCommand().equals("是否借书")){
			if(e.getSource()==jb1){	
				//System.out.println("打开借书对话框");
				//传当前对象
				new AddDialog().showAddDialog(this);
			}
			if(e.getSource()==jb2){
				new TJAddDialog().showTJAddDialog(null);
			}
			if(e.getSource()==jb3){
				new TuShuXinXi().initTuShuXinXi();
			}
		}
		//编写数据面板刷新方法
		public void  updateTable(String name) {
			// 将数据赋值给表格，替换掉原来的内容
			// 1 将JScrollPanel面板删除
			jf.getContentPane().remove(jsp);
			// 2 将加载了新数据的ScrollPanel加载进来

			// 重新设置表格数据
			setTableData1(name);
			// 刷新框架
			jf.validate();
		}

		// ////////////////////////////////////////////////////////////////////
		// 设置表格数据
		public void setTableData1(String name) {
			
			String sql_num = "select count(*) as num from t_shu";

			String sql = "select * from t_shu";
			
			if (!name.equals("")) {
				
				sql_num = "select count(*) as num from t_shu where name like '%" + name + "%'";
				sql = "select * from t_shu where name like '%" + name + "%'";
			}
			
			//连接数据库，判断是否有当前输入的用户和密码匹配的数据
		
			
			
			int row = db.getNum(sql_num);
			tb = new Object[row][4];

			///////////////////////////////////////////
			//返回结果集（行记录）
			//我们希望返回的不是结果集，而是对象
			List<TShu> l = db.getTusers(sql);
			int i = 0;
			for(TShu u : l){
				//获取数组列表中的数据，依次放入数组中
				tb[i] = new Object[] {u.getName(), u.getBianhao(), u.getZuozhe(), 
								u.getCBS()};
				i++;
			}
			db.close();


			// 表头
			Object[] c = { "书名", "编号","作者" ,"出版社"};

			// 创建表格需要传入数据和表头
			jt = new JTable(tb, c);

			// 将表格数据加入面板
			jsp = new JScrollPane(jt);
			// 将面板加入框架
			jf.add(jsp);
		}

		// 初始化系统主界面
		public void initSystemDialog(String name) {

			// //////////////////////////////////////////////////////////
			// 设置框架大小
			jf.setSize(600, 505);
			// 设置位置
			jf.setLocation(400,100);
			// 设置框架可见
			jf.setVisible(true);
			// //////////////////////////////////////////////////////////

			// 设置表格数据
			setTableData1("");

			// ////////////////////////////////////////////////////////////////////
			// 将组建加载进面板
			jp.add(jtext);
			jp.add(jb);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);

			// 将查找按钮绑定处理事件类

			jb.addActionListener(this);
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb3.addActionListener(this);
			
			ImageIcon TP = new ImageIcon("8.jpg");// 这是背景图片
			JLabel jl0 = new JLabel(TP);// 将背景图放在标签里。
			jf.getLayeredPane().add(jl0, new Integer(Integer.MIN_VALUE));// 注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
			jl0.setBounds(0, 0,TP.getIconWidth(), TP.getIconHeight());// 设置背景标签的位置
			Container cp = jf.getContentPane();
			cp.setLayout(new BorderLayout());
			
			jtext.setFont(new Font("华文新魏",Font.CENTER_BASELINE,17));
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
			// 将面板加入框架，设置北边位置
			
			jl.setText(name + "，欢迎进入系统！");
			jf.add(jl, BorderLayout.SOUTH);
			jf.add(jp, BorderLayout.NORTH);
			// ///////////////////////////////////////////////////////////////////////

		}
	}