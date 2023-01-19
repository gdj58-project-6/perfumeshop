package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.SiteCounter;

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
	
	// 년도별 누적 접속자 수
	public ArrayList<SiteCounter> selectCountByYear(Connection conn) throws Exception {
		ArrayList<SiteCounter> list = new ArrayList<SiteCounter>();
		String sql = "SELECT SUBSTR(counter_date, 1, 4) date, sum(counter_num) totalCount FROM site_counter GROUP BY YEAR(counter_date)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			SiteCounter s = new SiteCounter();
			s.setCounterDate(rs.getString("date"));
			s.setCounterNum(rs.getInt("totalCount"));
			list.add(s);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 월별 누적 접속자 수
	public ArrayList<SiteCounter> selectCountByMonth(Connection conn) throws Exception {
		ArrayList<SiteCounter> list = new ArrayList<SiteCounter>();
		String sql = "SELECT SUBSTR(counter_date, 1, 7) date, sum(counter_num) totalCount FROM site_counter GROUP BY MONTH(counter_date)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			SiteCounter s = new SiteCounter();
			s.setCounterDate(rs.getString("date"));
			s.setCounterNum(rs.getInt("totalCount"));
			list.add(s);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 일별 누적 접속자 수
	public ArrayList<SiteCounter> selectCountByDay(Connection conn) throws Exception {
		ArrayList<SiteCounter> list = new ArrayList<SiteCounter>();
		String sql = "SELECT counter_date date, sum(counter_num) totalCount FROM site_counter GROUP BY DAY(counter_date)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			SiteCounter s = new SiteCounter();
			s.setCounterDate(rs.getString("date"));
			s.setCounterNum(rs.getInt("totalCount"));
			list.add(s);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
}
