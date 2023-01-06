package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.Emp;

public class EmpDao {
	// 직원 레벨 수정
	public int updateEmpLevel(Connection conn, int authCode, String empId, int empCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "UPDATE emp SET auth_code = ? WHERE emp_id = ? AND emp_code = ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, authCode);
		stmt.setString(2, empId);
		stmt.setInt(3, empCode);
		// 쿼리 실행
		row = stmt.executeUpdate();

		stmt.close();
		return row;
	}
	// 직원 레벨수정 페이지 페이징에 필요한 목록수 출력
	public int selectCountByEmpModify(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM emp";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	
	// 직원 레벨 수정 직원 리스트
	public ArrayList<Emp> selectEmpListByEmpModify(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<Emp> list = null;
		// 쿼리문 작성
		String sql = "SELECT"
				+ "		emp_code empCode"
				+ "		, emp_id empId"
				+ "		, emp_name empName"
				+ "		, active"
				+ "		, auth_code authoCode"
				+ "		, createdate"
				+ "		FROM emp ORDER BY emp_code ASC LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<Emp>();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpCode(rs.getInt("empCode"));
			e.setEmpId(rs.getString("empId"));
			e.setEmpName(rs.getString("empName"));
			e.setActive(rs.getString("active"));
			e.setAuthCode(rs.getInt("authoCode"));
			e.setCreatedate(rs.getString("createdate"));
			list.add(e);
		}
		stmt.close();
		rs.close();
		return list;
	}
	// 직원 로그인
	public Emp loginEmp(Connection conn, Emp paramEmp) throws Exception {
		// 객체 초기화
		Emp emp = null;
		// 쿼리문 작성
		String sql = "SELECT emp_id empId, emp_name empName, active, auth_code authCode FROM emp WHERE emp_id = ? AND emp_pw = PASSWORD(?)";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, paramEmp.getEmpId());
		stmt.setString(2, paramEmp.getEmpPw());
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			emp = new Emp();
			emp.setEmpId(rs.getString("empId"));
			emp.setEmpName(rs.getString("empName"));
			emp.setActive(rs.getString("active"));
			emp.setAuthCode(rs.getInt("authCode"));
		}
		stmt.close();
		rs.close();
		return emp;
	}
	
	// 아이디 중복검사 : 사용가능 - false, 사용불가능 - true
	public String selectEmpId(Connection conn, Emp emp) throws Exception {
		String selectId = null;
		ResultSet rs = null;
		String sql = "SELECT t.id from (SELECT customer_id id FROM customer UNION SELECT emp_id id FROM emp UNION SELECT id id FROM outid) t WHERE t.id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		  
		rs = stmt.executeQuery();
		  
		if(rs.next()) {
		selectId = rs.getString("t.id");
		}
		  
		stmt.close();
		  
		return selectId; // 사용가능한 아이디면 null 반환
	}
	
	// 직원 가입
	public int insertEmp(Connection conn, Emp emp) throws Exception {
		int row = 0;
		String sql = "INSERT INTO emp(emp_id, emp_pw, emp_name, active, auth_code, createdate) VALUES(?, PASSWORD(?), ?, DEFAULT(`active`), DEFAULT(`auth_code`), NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		stmt.setString(3, emp.getEmpName());
		
		row = stmt.executeUpdate();
		
		return row;
	}
	
	// 직원 상세정보
	public Emp selectEmpOne(Connection conn, Emp emp) throws Exception {
		Emp resultEmp = null;
		ResultSet rs = null;
		String sql = "SELECT emp_code empCode, emp_id empId, emp_pw empPw, emp_name empName, active, auth_code authCode, createdate FROM emp WHERE emp_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			resultEmp = new Emp();
			resultEmp.setAuthCode(rs.getInt("empCode"));
			resultEmp.setEmpId(rs.getString("empId"));
			resultEmp.setEmpPw(rs.getString("empPw"));
			resultEmp.setEmpName(rs.getString("empName"));
			resultEmp.setActive(rs.getString("active"));
			resultEmp.setAuthCode(rs.getInt("authCode"));
			resultEmp.setCreatedate(rs.getString("createdate"));
		}
		
		return resultEmp;
	}
	
	// 직원 탈퇴
	public int deleteEmp(Connection conn, String id, String pw) throws Exception {
		int row = 0;
		String sql = "DELETE from emp WHERE emp_id = ? AND emp_pw = PASSWORD(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, pw);
		
		row = stmt.executeUpdate();
		
		return row;
	}

}
