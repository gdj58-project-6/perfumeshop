package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.EmpDao;
import util.DBUtil;
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
