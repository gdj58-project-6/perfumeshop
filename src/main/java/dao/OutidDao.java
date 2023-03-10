package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Customer;

public class OutidDao {
	// 탈퇴한 아이디 저장 customer
	public int insertCustomerOutid(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "INSERT INTO outid(id, createdate) VALUES(?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// emp 탈퇴한 아이디 저장 customer
	public int insertEmpOutid(Connection conn, String id) throws Exception {
		int row = 0;
		String sql = "INSERT INTO outid(id, createdate) VALUES(?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
