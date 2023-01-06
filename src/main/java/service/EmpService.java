package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import dao.EmpDao;
import util.DBUtil;
import vo.Customer;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	// 직원 로그인
	public Emp loginEmp(Emp paramEmp) {
		Emp emp = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.empDao = new EmpDao();
			emp = empDao.loginEmp(conn, paramEmp);
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
		
		return emp;
	}
	// 아이디 중복검사 : 사용가능 - true, 사용불가능 - false
	public String getSelectEmpId(Emp emp) {
		String selectId = null;
		Connection conn = null;
	  
		try {
			conn = DBUtil.getConnection();
			this.empDao = new EmpDao();
			selectId = empDao.selectEmpId(conn, emp);
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
		return selectId;
	}
	
	// 직원 가입
	public int getInsertEmp(Emp emp) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.empDao = new  EmpDao();
			row = empDao.insertEmp(conn, emp);
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
}
