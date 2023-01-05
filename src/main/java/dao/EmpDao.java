package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Emp;

public class EmpDao {
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
