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

	//���ݿ�������Ϣ
	String url = "jdbc:mysql://localhost/login";
	String user = "root";
	String pwd = "root";
	
	//���ݿ������Ҫ��������
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	//������ݿ�����
	public Connection getConn(){
		try {
			// ��������
			Class.forName("com.mysql.jdbc.Driver");
			// ������MySQL������
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
	//���Statement,ִ��SQL����
	public Statement getStatement(){
		//�������
		conn = getConn();
		try {
			//��������������ִ��sql����
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stmt;
	}
	//����SQL����ý���������ң����漰�޸ģ�
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
	
	//���²�����ɾ�����޸ġ����ӣ�
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
	//��ȡ����
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
	
	//�ر����ݿ���Դ
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
	//����ѧ���û��Ƿ����
	public boolean findUser(String name, String password){
		//��װSQL���
		String sql = "select * from t_user where name = '" + name + "' and password = '" + password + "'";
		//���SQL���ҵ�ֵ
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
	//�жϹ���Ա�˺�
	public boolean glUser(String name, String password){
		//��װSQL���
		String sql = "select * from t_gl where name = '" + name + "' and password = '" + password + "'";
		//���SQL���ҵ�ֵ
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
	//����õĽ����ת��Ϊ����
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
	//����õĽ����ת��Ϊ����
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
//����õĽ����ת��Ϊ����
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
//����õĽ����ת��Ϊ����
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
	
	//�������ӡ��޸ġ�ɾ��������ҪResultSet����
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
//			rs=psmt.executeQuery();//���в�ѯ�ģ���ҪResultSet����
			int hangshu=psmt.executeUpdate();//�������ӡ��޸ġ�ɾ��������ҪResultSet����
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
