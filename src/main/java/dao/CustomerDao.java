package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;
import vo.Orders;

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
		String sql = "SELECT "
					+ "	customer_code customerCode "
					+ ", customer_id  customerId"
					+ ", customer_name customerName "
					+ ", customer_phone customerPhone "
					+ ", total_price totalPrice "
					+ ", point "
					+ ", auth_code authCode "
					+ ", createdate "
					+ "FROM customer "
					+ "ORDER BY customer_code DESC LIMIT ?, ?";
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
			c.setTotalPrice(rs.getInt("totalPrice"));
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
		String sql = "SELECT customer_id customerId, customer_name customerName, auth_code authCode FROM customer WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
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
		String sql = "INSERT INTO customer(customer_id, customer_pw, customer_name, customer_phone, total_price, POINT, auth_code, createdate) "
					+ "				VALUES(?, PASSWORD(?), ?, ?, DEFAULT(`total_price`), DEFAULT(`point`), DEFAULT(`auth_code`), NOW())";
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
	public HashMap<String, Object> selectCustomerOne(Connection conn, Customer customer) throws Exception {
		HashMap<String, Object> memberOne = null;
		ResultSet rs = null;
		String sql = "SELECT "
					+ "	c.customer_code customerCode "
					+ ", c.customer_id customerId "
					+ ", c.customer_pw customerPw "
					+ ", c.customer_name customerName "
					+ ", c.customer_phone customerPhone "
					+ ", ca.address_code addressCode "
					+ ", ca.address address "
					+ ", c.total_price totalPrice "
					+ ", c.point point "
					+ ", c.auth_code authCode "
					+ ", c.createdate createdate "
					+ "FROM customer c INNER JOIN customer_address ca "
					+ "ON c.customer_id = ca.customer_id "
					+ "WHERE c.customer_id = ? AND ca.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		stmt.setString(2, customer.getCustomerId());

		rs = stmt.executeQuery();

		if (rs.next()) {
			memberOne = new HashMap<String, Object>();
			memberOne.put("customerCode", rs.getInt("customerCode"));
			memberOne.put("customerId", rs.getString("customerId"));
			memberOne.put("customerPw", rs.getString("customerPw"));
			memberOne.put("customerName", rs.getString("customerName"));
			memberOne.put("customerPhone", rs.getString("customerPhone"));
			memberOne.put("addressCode", rs.getInt("addressCode"));
			memberOne.put("address", rs.getString("address"));
			memberOne.put("totalPrice", rs.getInt("totalPrice"));
			memberOne.put("point", rs.getInt("point"));
			memberOne.put("authCode", rs.getInt("authCode"));
			memberOne.put("createdate", rs.getString("createdate"));
		}

		stmt.close();
		rs.close();

		return memberOne;
	}
	
	// pointHistory에 insert될때마다 point update
	// 포인트 취소, 사용시
	public int updateUsePoint(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET POINT = POINT - ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer.getPoint());
		stmt.setString(2, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 포인트 적립시
	public int updateSavePoint(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET POINT = POINT + ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customer.getPoint());
		stmt.setString(2, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 회원등급 구분을 위한 누적 주문 금액
	// 주문시 누적 금액 플러스
	public int updateSaveTotalPrice(Connection conn, Orders orders) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET total_price = total_price + ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getOrderPrice());
		stmt.setString(2, orders.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 주문 취소, 반품시 마이너스
	public int updateUseTotalPrice(Connection conn, Orders orders) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET total_price = total_price - ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getOrderPrice());
		stmt.setString(2, orders.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 회원 정보 수정 (주소는 따로)
	public int updateCustomerOne(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "UPDATE customer SET customer_name = ?, customer_phone = ? WHERE customer_id = ? AND customer_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerName());
		stmt.setString(2, customer.getCustomerPhone());
		stmt.setString(3, customer.getCustomerId());
		stmt.setString(4, customer.getCustomerPw());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 회원 주소 수정
	public int updateCustomerAddress(Connection conn, String id, String address) throws Exception {
		int row = 0;
		String sql = "UPDATE customer_address SET address = ? WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, address);
		stmt.setString(2, id);
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
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