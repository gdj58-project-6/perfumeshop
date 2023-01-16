package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.GoodsQuestionComment;
import vo.QuestionComment;


public class CommentDao {
	// 주문문의 답변삭제
	public int deleteQuestionCommentByAdmin(Connection conn, int commentCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "DELETE FROM question_comment WHERE comment_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, commentCode);
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 주문문의 답변 달기
	public int insertQuestionCommentByAdmin(Connection conn , QuestionComment questionComment) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO question_comment(question_code, comment_memo, createdate) VALUES(?, ?, NOW())";
		// 쿼리객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, questionComment.getQuestionCode());
		stmt.setString(2, questionComment.getCommentMemo());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	
	// 주문문의 목록수
	public int selectQuestionCountByAdmin(Connection conn, String search, String word) throws Exception {
		// 객체 초기화
		int row = 0;
		if(search == null || search.equals("")) {
			search = "";
		}
		if(word == null || word.equals("")) {
			word = "";
		}
		// 쿼리문 작성
		String sql = "";
		// 쿼리 객체 생성
		PreparedStatement stmt = null;
		if(search == null || search.equals("")) {
			sql = "SELECT COUNT(*) FROM question";
			stmt = conn.prepareStatement(sql);
		} else if(search.equals("orderCode")) {
			sql = "SELECT COUNT(*) FROM question WHERE orders_code LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, word);
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	// 주문문의
	public ArrayList<HashMap<String, Object>> selectQuestionListByAdmin(Connection conn, String search, String word, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		if(search == null || search.equals("")) {
			search = "";
		}
		if(word == null || word.equals("")) {
			word = "";
		}
		// 쿼리문
		String sql = "";
		// 쿼리 객체 생성
		PreparedStatement stmt = null;
		
		// 검색어 없을때
		if(search == null || search.equals("")) {
			// 검색어 없을때 쿼리문
			sql = "SELECT"
					+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
					+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
					+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
					+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
					+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
					+ "		inner JOIN orders o ON q.orders_code = o.order_code"
					+ "		inner JOIN order_goods og ON q.orders_code = og.order_code"
					+ "		inner JOIN goods g ON og.goods_code = g.goods_code"
					+ "		GROUP BY q.question_code"
					+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
			// 쿼리 객체 생성
			stmt = conn.prepareStatement(sql);
			// 쿼리문 ?값 지정
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
		} else if(search.equals("orderCode")) {
			// 주문번호로 검색
			sql = "SELECT"
					+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
					+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
					+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
					+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
					+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
					+ "		inner JOIN orders o ON q.orders_code = o.order_code"
					+ "		inner JOIN order_goods og ON q.orders_code = og.order_code"
					+ "		inner JOIN goods g ON og.goods_code = g.goods_code"
					+ "		WHERE q.orders_code LIKE ?"
					+ "		GROUP BY q.question_code"
					+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
			// 쿼리 객체 생성
			stmt = conn.prepareStatement(sql);
			// 쿼리문 ?값 지정
			stmt.setString(1, word);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("category", rs.getString("category"));
			m.put("questionMemo", rs.getString("questionMemo"));
			m.put("qCreatedate", rs.getString("qCreatedate"));
			m.put("commentCode", rs.getInt("commentCode"));
			m.put("commentMemo", rs.getString("commentMemo"));
			m.put("qcCreatedate", rs.getString("qcCreatedate"));
			m.put("customerId", rs.getString("customerId"));
			m.put("orderState", rs.getString("orderState"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
	// 상품문의 답변삭제
	public int deleteGoodsQuestionCommentByAdmin(Connection conn, int goodsCommentCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "DELETE FROM goods_question_comment WHERE goods_comment_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsCommentCode);
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 상품문의 답변달기
	public int insertGoodsQuestionCommentByAdmin(Connection conn, GoodsQuestionComment goodsQuestionComment) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO goods_question_comment(goods_question_code, goods_comment_memo, createdate) VALUES(?, ?, NOW())";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsQuestionComment.getGoodsQuestionCode());
		stmt.setString(2, goodsQuestionComment.getGoodsCommentMemo());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 상품문의 목록수
	public int selectGoodsQuestionCountByAdmin(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM goods_question";
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
	// 상품문의
	public ArrayList<HashMap<String, Object>> selectGoodsQuestionListByAdmin(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql= "SELECT gq.goods_question_code goodsQuestionCode"
				+ "		, gq.goods_code goodsCode"
				+ "		, gq.customer_id customerId"
				+ "		, gq.category category"
				+ "		, gq.goods_question_memo goodsQuestionMemo"
				+ "		, gq.createdate gqCreatedate"
				+ "		, g.goods_name goodsName"
				+ "		, gqc.goods_comment_code goodsCommentCode"
				+ "		, gqc.goods_comment_memo goodsCommentMemo"
				+ "		, gqc.createdate gqcCreatedate"
				+ "		FROM goods_question gq INNER JOIN goods g"
				+ "		ON gq.goods_code = g.goods_code"
				+ "		left JOIN goods_question_comment gqc"
				+ "		ON gq.goods_question_code = gqc.goods_question_code"
				+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsQuestionCode", rs.getInt("goodsQuestionCode"));
			m.put("customerId", rs.getString("customerId"));
			m.put("category", rs.getString("category"));
			m.put("goodsQuestionMemo", rs.getString("goodsQuestionMemo"));
			m.put("gqCreatedate", rs.getString("gqCreatedate"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsCommentCode", rs.getInt("goodsCommentCode"));
			m.put("goodsCommentMemo", rs.getString("goodsCommentMemo"));
			m.put("gqcCreatedate", rs.getString("gqcCreatedate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
}
