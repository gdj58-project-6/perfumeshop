package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CustomerDao;
import dao.EmpDao;
import dao.OutidDao;
import util.DBUtil;
import vo.Customer;
import vo.Emp;

public class EmpService {
	private EmpDao empDao;
	private OutidDao outidDao;
	
	// 고객 레벨 수정
	// 고객 레벨 수정
	public int updateEmpLevel(int authCode, String empId, int empCode) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;

		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화
			this.empDao = new EmpDao();
			// dao호출
			row = empDao.updateEmpLevel(conn, authCode, empId, empCode);
			// 커밋
			conn.commit();
		} catch (Exception e) {
			// 수정 실패시 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 성공시 자원반납
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// 고객 레벨수정 페이지 페이징에 필요한 목록수 출력
	public int getEmpCountByEmpModify() {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화
			this.empDao = new EmpDao();
			// dao호출
			row = empDao.selectCountByEmpModify(conn);
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
	public ArrayList<Emp> getEmpList(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<Emp> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 초기화
			this.empDao = new EmpDao();
			list = empDao.selectEmpListByEmpModify(conn, beginRow, rowPerPage);
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
	public int getDeleteEmp(String id) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.outidDao = new OutidDao();
			if(outidDao.insertEmpOutid(conn, id) == 1) {
				this.empDao = new EmpDao();
				row = empDao.deleteEmp(conn, id);
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
		
		return row;
	}
}
