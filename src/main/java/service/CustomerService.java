package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	private OutidDao outidDao;
	// 고객 레벨수정 페이지 페이징에 필요한 목록수 출력
	public int getCustomerCountByMemberModify() {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화
			this.customerDao = new CustomerDao();
			// dao호출
			row = customerDao.selectCountByMemberModify(conn);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// 고객 레벨수정 페이지 고객 리스트
	public ArrayList<Customer> getCustomerList(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<Customer> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 초기화
			this.customerDao = new CustomerDao();
			list = customerDao.selectCustomerListByMemberModify(conn, currentPage, rowPerPage);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 회원 로그인
	public Customer loginCustomer(Customer paramCustomer) {
		Customer customer = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.customerDao = new CustomerDao();
			customer = customerDao.loginCustomer(conn, paramCustomer);
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return customer;
	}
	// 아이디 중복검사 : 사용가능 - true, 사용불가능 - false
	public boolean getSelectCustomerId(String id) {
		boolean result = true;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.customerDao = new CustomerDao();
			result = customerDao.selectCustomerId(conn, id);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 회원가입
	public int getInsertCustomer(Customer customer) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.customerDao = new CustomerDao();
			row = customerDao.addCustomer(conn, customer);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 회원탈퇴
	public void getDeleteCustomer(Customer customer) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.outidDao = new OutidDao();
			if(this.outidDao.insertOutid(conn, customer) == 1) { // outid에 insert되면 진행
				this.customerDao = new CustomerDao();
				if(this.customerDao.deleteCustomer(conn, customer) != 1) { 
					// 회원탈퇴가 안되었으면 강제 예외발생시켜 catch절로 이동 후 rollback
					throw new Exception();
				}
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
