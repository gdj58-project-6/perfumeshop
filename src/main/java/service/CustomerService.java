package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Customer;

public class CustomerService {
	private CustomerDao customerDao;
	private OutidDao outidDao;
	// 아이디 중복검사
	public int getSelectCustomerId(Customer customer) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.customerDao = new CustomerDao();
			row = customerDao.selectCustomerId(conn, customer);
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
	
	// 회원가입
	public int getAddCustomer(Customer customer) {
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
