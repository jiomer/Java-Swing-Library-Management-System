package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DB {

	//�����û�����ȡȨ�ޱ�ʾsf
	
	public String getSF(String name){
		ResultSet rs = this.getResultSet("select * from t_user where name = '" + name + "'");
		String sf = null;
		try {
			rs.next();
			sf = rs.getString("sf");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sf;
	}
}
