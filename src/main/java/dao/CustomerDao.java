package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Customer;

public class CustomerDao {
	// 아이디 중복검사
	public int selectCustomerId(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "SELECT customer_id FROM customer WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 일반회원 회원가입
	public int addCustomer(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "INSERT INTO customer(customer_id, customer_pw, customer_name, customer_phone, point, createdate) VALUES(?, PASSWORD(?), ?, ?, DEFAULT(`point`), CURDATE())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		stmt.setString(3, customer.getCustomerName());
		stmt.setString(4, customer.getCustomerPhone());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 회원 탈퇴
	public int deleteCustomer(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "DELETE FROM customer WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerPw());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
