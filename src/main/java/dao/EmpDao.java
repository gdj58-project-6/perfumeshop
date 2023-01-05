package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Emp;

public class EmpDao {
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
	// 아이디 중복검사 : ..
	
	// 직원 가입
	public int insertEmp(Connection conn, Emp emp) throws Exception {
		int row = 0;
		String sql = "INSERT INTO emp(emp_id, emp_pw, emp_name, active, auth_code, createdate) VALUES(?, PASSWORD(?), ?, DEFAULT(`active`), DEFAULT(`auth_code`), CURDATE())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, emp.getEmpId());
		stmt.setString(2, emp.getEmpPw());
		stmt.setString(3, emp.getEmpName());
		
		row = stmt.executeUpdate();
		
		return row;
	}
}
