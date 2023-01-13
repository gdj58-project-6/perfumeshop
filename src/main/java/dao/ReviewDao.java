package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Review;

public class ReviewDao {
	// 구매확정시 리뷰등록
	public int insertReviewByCustomer(Connection conn, Review review) throws Exception {
		int row = 0;
		String sql = "INSERT INTO review(order_code, goods_code, review_memo, createdate) VALUES(?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, review.getOrderCode());
		stmt.setInt(2, review.getGoodsCode());
		stmt.setString(3, review.getReviewMemo());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 리뷰등록하면 리뷰쓰기 버튼 비활성화
	public String selectReview(Connection conn, Review review) throws Exception {
		String result = null;
		ResultSet rs = null;
		String sql = "SELECT review_memo reviewMemo FROM review WHERE order_code = ? AND goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, review.getOrderCode());
		stmt.setInt(2, review.getGoodsCode());
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			result = rs.getString("reviewMemo");
		}
		
		rs.close();
		stmt.close();
		
		return result; // 리뷰가 있으면 리뷰내용을 리턴하고 없으면 null 리턴
	}
	
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
				+ "		ORDER BY r.order_code DESC LIMIT ?, ?";
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
