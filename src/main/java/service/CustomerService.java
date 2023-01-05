package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CustomerDao;
import dao.EmpDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Customer;
import vo.Emp;

public class CustomerService {
	private CustomerDao customerDao;
	private OutidDao outidDao;

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
