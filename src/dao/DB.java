package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TJShu;
import model.TShu;
import model.JShu;
import model.TTuShu;


public class DB {

	//数据库配置信息
	String url = "jdbc:mysql://localhost/login";
	String user = "root";
	String pwd = "root";
	
	//数据库操作需要三个变量
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	//获得数据库连接
	public Connection getConn(){
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 建立到MySQL的连接
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	//获得Statement,执行SQL对象
	public Statement getStatement(){
		//获得连接
		conn = getConn();
		try {
			//创建语句对象，用以执行sql语言
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stmt;
	}
	//依据SQL语句获得结果集（查找，不涉及修改）
	public ResultSet getResultSet(String sql){
		stmt = getStatement();
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	//更新操作（删除、修改、增加）
	public int updataData(String sql){
		int result = 0;
		stmt = getStatement();
		try {
			result = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//获取行数
	public int getNum(String sql){
		int row = 0;
		ResultSet rs = getResultSet(sql);
		try {
			rs.next();
			row = rs.getInt("num");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
	
	//关闭数据库资源
	public void close(){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//查找学生用户是否存在
	public boolean findUser(String name, String password){
		//组装SQL语句
		String sql = "select * from t_user where name = '" + name + "' and password = '" + password + "'";
		//获得SQL查找的值
		ResultSet rs = getResultSet(sql);
		
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//判断管理员账号
	public boolean glUser(String name, String password){
		//组装SQL语句
		String sql = "select * from t_gl where name = '" + name + "' and password = '" + password + "'";
		//获得SQL查找的值
		ResultSet rs = getResultSet(sql);
		
		try {
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//将获得的结果集转换为对象
	public List<TShu> getTusers(String sql){
		
		List<TShu> l = new ArrayList<TShu>();
		
		ResultSet rs = getResultSet(sql);
		try {
			while(rs.next()){
				TShu u = new TShu();
				u.setName(rs.getString("name"));
				u.setBianhao(rs.getString("bianhao"));
				u.setZuozhe(rs.getString("zuozhe"));
				u.setCBS(rs.getString("cBS"));
				l.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
	//将获得的结果集转换为对象
public List<TTuShu> getTTuShu(String sql){
		
		List<TTuShu> t = new ArrayList<TTuShu>();
		
		ResultSet rs = getResultSet(sql);
		try {
			while(rs.next()){
				TTuShu u = new TTuShu();
				u.setName(rs.getString("name"));
				u.setXinxi(rs.getString("xinxi"));
				t.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
//将获得的结果集转换为对象
public List<JShu> getJShu(String sql){
	
	List<JShu> f = new ArrayList<JShu>();
	
	ResultSet rs = getResultSet(sql);
	try {
		while(rs.next()){
			JShu u = new JShu();
			u.setXuehao(rs.getString("xuehao"));
			u.setDep(rs.getString("dep"));
			u.setLet(rs.getString("let"));
			u.setName(rs.getString("name"));
			f.add(u);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return f;
}
//将获得的结果集转换为对象
public List<TJShu> getTJShu(String sql){
	
	List<TJShu> o = new ArrayList<TJShu>();
	
	ResultSet rs = getResultSet(sql);
	try {
		while(rs.next()){
			TJShu u = new TJShu();
			u.setName(rs.getString("name"));
			u.setBianhao(rs.getString("bianhao"));
			u.setZuozhe(rs.getString("zuozhe"));
			u.setChubanshe(rs.getString("chubanshe"));
			o.add(u);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return o;
}
	
	//进行增加、修改、删除，不需要ResultSet集合
	public boolean doInsert(String name,String password){
		boolean flag=false;
		Connection conn2=null;
		conn2=getConn();
		PreparedStatement psmt=null;
		
		try {
			String DBSQL="insert into t_user VALUES(?,?)";
			psmt=conn2.prepareStatement(DBSQL);
			psmt.setString(1, name);
			psmt.setString(2, password);
//			rs=psmt.executeQuery();//进行查询的，需要ResultSet集合
			int hangshu=psmt.executeUpdate();//进行增加、修改、删除，不需要ResultSet集合
			if(hangshu>0){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
}
