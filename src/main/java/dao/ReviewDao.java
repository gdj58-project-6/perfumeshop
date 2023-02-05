package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Review;

public class ReviewDao {
	// 직원용 리뷰리스트 출력
	public ArrayList<HashMap<String, Object>> selectReviewListByAdmin(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT "
					+ "r.order_code orderCode "
					+ ", r.goods_code goodsCode "
					+ ", g.goods_name goodsName "
					+ ", gi.filename filename "
					+ ", o.customer_id customerId "
					+ ", r.review_memo reviewMemo "
					+ ", ph.point point "
					+ ", r.createdate createdate "
					+ "FROM review r INNER JOIN orders o "
					+ "ON r.order_code = o.order_code "
					+ "INNER JOIN order_goods og "
					+ "ON o.order_code = og.order_code "
					+ "INNER JOIN goods g "
					+ "ON og.goods_code = g.goods_code "
					+ "INNER JOIN goods_img gi "
					+ "ON g.goods_code = gi.goods_code "
					+ "INNER JOIN point_history ph "
					+ "ON o.order_code = ph.order_code "
					+ "WHERE ph.point_kind = '적립'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("filename", rs.getString("filename"));
			m.put("customerId", rs.getString("customerId"));
			m.put("reviewMemo", rs.getString("reviewMemo"));
			m.put("point", rs.getInt("point"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 로그인한 회원이 작성한 리뷰리스트, 리뷰 내용까지 한 번에 출력
	public ArrayList<HashMap<String, Object>> selectReviewList(Connection conn, String id) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT " 
					+ "r.order_code orderCode " 
					+ ", r.goods_code goodsCode "
					+ ", g.goods_name goodsName " 
					+ ", gi.filename filename " 
					+ ", o.customer_id customerId"
					+ ", r.review_memo reviewMemo "
					+ ", ph.point point " 
					+ ", r.createdate createdate " 
					+ "FROM review r INNER JOIN orders o "
					+ "ON r.order_code = o.order_code " 
					+ "INNER JOIN order_goods og "
					+ "ON o.order_code = og.order_code " 
					+ "INNER JOIN goods g " 
					+ "ON og.goods_code = g.goods_code "
					+ "INNER JOIN goods_img gi " 
					+ "ON g.goods_code = gi.goods_code " 
					+ "INNER JOIN point_history ph "
					+ "ON o.order_code = ph.order_code " 
					+ "WHERE o.customer_id = ? AND ph.point_kind = '적립'";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);

		rs = stmt.executeQuery();

		while (rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("filename", rs.getString("filename"));
			m.put("customerId", rs.getString("customerId"));
			m.put("reviewMemo", rs.getString("reviewMemo"));
			m.put("point", rs.getInt("point"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}

		rs.close();
		stmt.close();

		return list;
	}

	
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
	public ArrayList<HashMap<String, Object>> selectReviewByCustomer(Connection conn, int goodsCode, int beginRow, int rowPerPage) throws Exception {
		// 객체 생성
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql = "SELECT"
			+ "	r.order_code orderCode"
			+ "	, c.customer_name customerName"
			+ "	, g.goods_name goodsName"
			+ "	, r.review_memo reviewMemo"
			+ "	, r.createdate createdate"
			+ "	FROM review r INNER JOIN goods g"
			+ "	ON r.goods_code = g.goods_code"
			+ "	INNER JOIN orders o"
			+ "	ON r.order_code = o.order_code"
			+ "	INNER JOIN customer c"
			+ "	ON o.customer_id = c.customer_id"
			+ "	WHERE g.goods_code = ?"
			+ "	ORDER BY r.order_code DESC LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 ?값 지정
		stmt.setInt(1, goodsCode);
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("customerName", rs.getString("customerName"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("reviewMemo", rs.getString("reviewMemo"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}

	// 리뷰 수정
	public int updateReview(Connection conn, Review review) throws Exception {
		int row = 0;
		String sql = "UPDATE review SET review_memo = ? WHERE order_code = ? AND goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, review.getReviewMemo());
		stmt.setInt(2, review.getOrderCode());
		stmt.setInt(3, review.getGoodsCode());
		  
		row = stmt.executeUpdate();
		
		stmt.close();
		  
		return row;
	}
	   
	// 리뷰 삭제
	public int deleteReview(Connection conn, Review review) throws Exception {
		int row = 0;
		String sql = "DELETE from review WHERE order_code = ? AND goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, review.getOrderCode());
		stmt.setInt(2, review.getGoodsCode());

		row = stmt.executeUpdate();

		stmt.close();

		return row;
	}

}
