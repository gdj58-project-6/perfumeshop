package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CounterDao {
	// 오늘 첫번째 접속자가 발생했을때 : insert 실행
	public void insertCount(Connection conn) throws Exception {
		String sql = "INSERT INTO site_counter(counter_date, counter_num) VALUES(CURDATE(), 1)"; // 하루에 첫번째 접속자만 insert하기때문에 1로 설정
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	// 오늘 첫번째 접속자가 아닐때 : update 실행
	public void updateCount(Connection conn) throws Exception {
		String sql = "UPDATE site_counter SET counter_num = counter_num + 1 WHERE counter_date = CURDATE()"; // 기존에 있던 접속자수에 +1을 해줌
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		
		stmt.close();
	}
	
	// 오늘 총 접속자 수
	public int selectTodayCount(Connection conn) throws Exception {
		// 총 접속자 수
		int todayCount = 0;
		String sql = "SELECT counter_num counterNum FROM site_counter WHERE counter_date = CURDATE()";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			todayCount = rs.getInt("counterNum");
		}
		
		rs.close();
		stmt.close();
		
		return todayCount;
	}
	
	// 사이트가 생성된 후 부터 누적 접속자 수
	public int selectTotalCount(Connection conn) throws Exception {
		// 누적 접속자 수
		int totalCount = 0;
		String sql = "SELECT sum(counter_num) totalCount FROM site_counter";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			totalCount = rs.getInt("totalCount");
		}
		
		rs.close();
		stmt.close();
		
		return totalCount;
	}
}
