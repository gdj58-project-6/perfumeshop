package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;

public class CustomerDao {
	// 고객 레벨 수정
	public int updateMemberLevel(Connection conn, int authCode, String customerId) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "UPDATE customer SET auth_code = ? WHERE customer_id = ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, authCode);
		stmt.setString(2, customerId);
		// 쿼리 실행
		row = stmt.executeUpdate();

		stmt.close();
		return row;
	}

	// 고객 레벨수정 페이지 페이징에 필요한 목록수 출력
	public int selectCountByMemberModify(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM customer";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}

	// 고객 레벨수정 페이지 고객 리스트
	public ArrayList<Customer> selectCustomerListByMemberModify(Connection conn, int beginRow, int rowPerPage)
			throws Exception {
		// 객체 초기화
		ArrayList<Customer> list = null;
		// 쿼리문 작성
		String sql = "SELECT" + "   customer_code customerCode" + " , customer_id customerId"
				+ " , customer_name customerName" + " , customer_phone customerPhone" + " , point"
				+ " , auth_code authCode" + " , createdate" + " FROM customer  ORDER BY customer_code ASC LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<Customer>();
		while (rs.next()) {
			Customer c = new Customer();
			c.setCustomerCode(rs.getInt("customerCode"));
			c.setCustomerId(rs.getString("customerId"));
			c.setCustomerName(rs.getString("customerName"));
			c.setCustomerPhone(rs.getString("customerPhone"));
			c.setPoint(rs.getInt("point"));
			c.setAuthCode(rs.getInt("authCode"));
			c.setCreatedate(rs.getString("createdate"));
			list.add(c);
		}
		stmt.close();
		rs.close();
		return list;
	}

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
		if (rs.next()) {
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

	// 아이디 중복검사 : 사용가능 - null반환
	public String selectCustomerId(Connection conn, Customer customer) throws Exception {
		String selectId = null;
		ResultSet rs = null;
		String sql = "SELECT t.id from (SELECT customer_id id FROM customer UNION SELECT emp_id id FROM emp UNION SELECT id id FROM outid) t WHERE t.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());

		rs = stmt.executeQuery();

		if (rs.next()) {
			selectId = rs.getString("t.id");
		}

		stmt.close();

		return selectId;
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

	// 회원 정보
	public ArrayList<HashMap<String, Object>> selectCustomerOne(Connection conn, Customer customer) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT c.customer_code customerCode, c.customer_id customerId, c.customer_pw customerPw, c.customer_name customerName, c.customer_phone customerPhone, c.point point, c.auth_code authCode, ca.address_code addressCode, ca.address address, c.createdate createdate "
					+"FROM customer c INNER JOIN customer_address ca "
					+"ON c.customer_id = ca.customer_id "
					+"WHERE c.customer_id = ? AND ca.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerId());

		rs = stmt.executeQuery();

		if (rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("customerCode", rs.getInt("customerCode"));
			m.put("customerId", rs.getString("customerId"));
			m.put("customerPw", rs.getString("customerPw"));
			m.put("customerName", rs.getString("customerName"));
			m.put("customerPhone", rs.getString("customerPhone"));
			m.put("point", rs.getInt("point"));
			m.put("authCode", rs.getInt("authCode"));
			m.put("addressCode", rs.getInt("addressCode"));
			m.put("address", rs.getString("address"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}

		stmt.close();
		rs.close();

		return list;
	}

	// 회원 비밀번호 수정
	public int updateCustomerPw(Connection conn, String id, String pw, String changePw) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET customer_pw = PASSWORD(?) WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, changePw);
		stmt.setString(2, id);
		stmt.setString(3, pw);

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