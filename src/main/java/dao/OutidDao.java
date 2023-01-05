package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Customer;

public class OutidDao {
	// 아이디 중복 검사
	public boolean selectOutid(Connection conn, String id) throws Exception {
		
		return true;
	}
	
	// 탈퇴한 아이디 저장
	public int insertOutid(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "INSERT INTO outid(id, createdate) VALUES(?, CURDATE())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
