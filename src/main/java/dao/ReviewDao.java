package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class ReviewDao {
	// 리뷰 페이징
	public int selectCountByReview(Connection conn) throws Exception {
		// 객체 생성
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM review";
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
	// goodsOne에 리뷰 출력
	public ArrayList<HashMap<String, Object>> selectReview(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 생성
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql = "SELECT r.order_code orderCode, r.review_memo reviewMemo"
				+ "		FROM orders o INNER JOIN review r "
				+ "		ON o.order_code = r.order_code "
				+ "		ORDER BY r.order_code ASC LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 ?값 지정
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("reviewMemo", rs.getString("reviewMemo"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
}
