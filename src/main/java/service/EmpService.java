package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.EmpDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	private OutidDao outidDao;
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
			this.empDao = new EmpDao();
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
	
	// 직원 상세 정보
	public Emp getSelectEmpOne(Emp emp) {
		Emp resultEmp = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.empDao = new EmpDao();
			resultEmp = empDao.selectEmpOne(conn, emp);
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
		
		return resultEmp;
	}
	
	// 직원탈퇴 후 outid에 아이디 저장
	public void getDeleteEmp(String id, String pw) {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.outidDao = new OutidDao();
			if(outidDao.insertOutid(conn, id) == 1) {
				this.empDao = new EmpDao();
				int row = empDao.deleteEmp(conn, id, pw);
				if(row != 1) {
					throw new Exception();
					// 회원탈퇴가 안되면 강제예외발생시켜 catch절로 이동하여 rollback되게
				}
			}
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
	}
}
