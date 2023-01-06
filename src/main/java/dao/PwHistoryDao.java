package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Customer;

public class PwHistoryDao {
	// 회원 비밀번호 유효성 검사(최근 비밀번호 3개 중에) : 사용가능 - null 반홤
	public String selectPwhistory(Connection conn, String id, String changePw) throws Exception {
		String result = null;
		ResultSet rs = null;
		String sql = "SELECT pw FROM pw_history WHERE customer_id = ? AND pw = PASSWORD(?) ORDER BY createdate DESC LIMIT 0, 3";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, changePw);

		rs = stmt.executeQuery();

		if (rs.next()) {
			result = rs.getString("pw");
		}

		stmt.close();
		rs.close();

		return result;
	}

	// 회원 비밀번호 수정 후 pw_history에 저장
	public int insertPwHistory(Connection conn, String id, String changePw) throws Exception {
		int row = 0;
		String sql = "INSERT INTO pw_history(customer_id, pw, createdate) VALUES(?, PASSWORD(?), NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, changePw);
		
		row = stmt.executeUpdate();

		stmt.close();

		return row;
	}
	
	// 회원탈퇴할때 history 삭제 
	public int deletePwHistory(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "DELETE FROM pw_history WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}