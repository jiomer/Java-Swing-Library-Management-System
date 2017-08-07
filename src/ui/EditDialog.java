package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DB;


//图书修改界面
public class EditDialog implements ActionListener {

	private DB db = new DB();

	// 定义框架
	JFrame jf = new JFrame("图书修改界面");
	// 定义面板
	JPanel jp = new JPanel();
	
	JLabel j1 = new JLabel("书名：");
	JLabel j2 = new JLabel("图书详细信息：");

	JLabel jl1 = new JLabel();
	JTextField jtext2 = new JTextField(10);
	JButton addButton = new JButton("修改");

	// 定义存储系统界面的变量
	GLTuShuXinXi sys;

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 获取输入的值
		String name = jl1.getText();
		String xinxi = jtext2.getText();
		

		String sql ="UPDATE t_tushu SET name = '" + name + "'" +
				", xinxi = '"+ xinxi + "' WHERE name = '" + name + "'";

		if (db.updataData(sql) == 1) {
			JOptionPane.showMessageDialog(null, "修改成功");
			//System.out.println("修改成功");
			// 告诉系统界面对象，刷新数据
			sys.updateTable("");

			jf.dispose();
		} else {
			//System.out.println("修改失败");
			JOptionPane.showMessageDialog(null, "修改失败");
		}
		//关闭程序
		db.close();
	}
	//创建一个增加界面的方法
	public void showAddDialog(GLTuShuXinXi sys, String id,String name,String xinxi) {

		// 保存传递过来的系统界面对象
		this.sys = sys;

		// 设置框架大小
		jf.setSize(600, 300);
		// 设置位置
		jf.setLocation(600, 400);
		// 设置框架可见
		jf.setVisible(true);
		
		jp.add(j1);
		jp.add(jl1);
		jl1.setText(name);

		jp.add(j2);
		jp.add(jtext2);
		jtext2.setText(xinxi);//传递给showAddDialog()中的xinxi
		

		jp.add(addButton);

		addButton.addActionListener(this);

		jf.add(jp);
	}
}
