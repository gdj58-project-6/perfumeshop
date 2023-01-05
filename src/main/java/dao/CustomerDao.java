package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Customer;
import vo.Emp;

public class CustomerDao {
	// 고객 로그인
	public Customer loginCustomer(Connection conn, Customer paramCustomer) throws Exception {
		// 객체 초기화
		Customer customer = null;
		// 쿼리문 작성
		String sql = "SELECT customer_id customerId, customer_name customerName, customer_phone customerPhone, point, auth_code authCode FROM customer WHERE customer_id=? AND customer_pw=PASSWORD(?);";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, paramCustomer.getCustomerId());
		stmt.setString(2, paramCustomer.getCustomerPw());
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			customer = new Customer();
			customer.setCustomerId(rs.getString("customerId"));
			customer.setCustomerName(rs.getString("customerName"));
			customer.setCustomerPhone(rs.getString("customerPhone"));
			customer.setPoint(rs.getInt("point"));
			customer.setAuthCode(rs.getInt("authCode"));
		}
		stmt.close();
		rs.close();
		return customer;
	}
	
	// 아이디 중복검사 : 사용가능 - false, 사용불가능 - true
	public boolean selectCustomerId(Connection conn, String id) throws Exception {
		boolean result = true;
		ResultSet rs = null;
		String sql = "SELECT customer_id FROM customer WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = false;
		}
		
		stmt.close();
		
		return result;
	}
	
	// 일반회원 회원가입
	public int addCustomer(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "INSERT INTO customer(customer_id, customer_pw, customer_name, customer_phone, point, auth_code, createdate) VALUES(?, PASSWORD(?), ?, ?, DEFAULT(`point`), DEFAULT(`auth_code`), CURDATE())";
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
	
	// 회원 정보
	public Customer selectCustomerOne(Connection conn, Customer customer) throws Exception {
		Customer resultCustomer = null;
		
		return resultCustomer;
	}
}
