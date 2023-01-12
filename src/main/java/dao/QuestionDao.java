package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.GoodsQuestion;

public class QuestionDao {
	// 상품문의 수정폼
	public GoodsQuestion selectGoodsQuestionByModifyGoodswQuestion(Connection conn, int goodsQuestionCode) throws Exception {
		//객체 초기화
		GoodsQuestion g = null;
		// 쿼리문 작성
		String sql = "SELECT"
				+ "		goods_question_code goodsQuestionCode"
				+ "		, goods_code goodsCode"
				+ "		, customer_id customerId"
				+ "		, category"
				+ "		, goods_question_memo goodsQuestionMemo"
				+ "		, createdate"
				+ "		FROM goods_question WHERE goods_question_code = ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsQuestionCode);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			g = new GoodsQuestion();
			g.setGoodsQuestionCode(rs.getInt("goodsQuestionCode"));
			g.setGoodsCode(rs.getInt("goodsCode"));
			g.setCustomerId(rs.getString("customerId"));
			g.setCategory(rs.getString("category"));
			g.setGoodsQuestionMemo(rs.getString("goodsQuestionMemo"));
			g.setCreatedate(rs.getString("createdate"));
		}
		stmt.close();
		rs.close();
		return g;
	}
	// 고객센터 문의 수정, 삭제 목록의수
	public int selectCountByGoodsQuestionModify(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM goods_question";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	// 고객센터 문의 수정, 삭제를 위한 리스트 출력
	public ArrayList<HashMap<String, Object>> selectGoodsQuestion(Connection conn, String customerId, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql = "SELECT gq.goods_question_code goodsQuestionCode"
				+ "		, gq.customer_id customerId"
				+ "		, gq.category category"
				+ "		, gq.goods_question_memo goodsQuestionMemo"
				+ "		, gq.createdate gqCreatedate"
				+ "		, gqc.goods_comment_code goodsCommentCode"
				+ "		, gqc.goods_comment_memo goodsCommentMemo"
				+ "		, gqc.createdate gqcCreatedate"
				+ "		FROM goods_question gq LEFT JOIN goods_question_comment gqc ON gq.goods_question_code = gqc.goods_question_code"
				+ "		WHERE gq.customer_id=?"
				+ "		ORDER BY gq.goods_question_code desc LIMIT ?, ?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, customerId);
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
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
			m.put("goodsCommentCode", rs.getInt("goodsCommentCode"));
			m.put("goodsCommentMemo", rs.getString("goodsCommentMemo"));
			m.put("gqcCreatedate", rs.getString("gqcCreatedate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
	// 상품문의글 페이징 목록수
	public int selectCountByGoodsQuestion(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM goods_question";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	// 상품문의 리스트
	public ArrayList<HashMap<String, Object>> selectGoodsQuestionList(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql="SELECT gq.goods_question_code goodsQuestionCode"
				+ "		, gq.goods_code goodsCode"
				+ "		, gq.category category"
				+ "		, gq.goods_question_memo goodsQuestionMemo"
				+ "		, gq.createdate createdate"
				+ "		, g.goods_name goodsName"
				+ "		, gqc.goods_comment_memo goodsCommentMemo"
				+ "		FROM goods_question gq INNER JOIN goods g"
				+ "		ON gq.goods_code = g.goods_code"
				+ "		left JOIN goods_question_comment gqc"
				+ "		ON gq.goods_question_code = gqc.goods_comment_code"
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
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("category", rs.getString("category"));
			m.put("goodsQuestionMemo", rs.getString("goodsQuestionMemo"));
			m.put("createdate", rs.getString("createdate"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsCommentMemo", rs.getString("goodsCommentMemo"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
	
	// 상품문의 문의하기
	public int insertGoodsQuestion(Connection conn, GoodsQuestion goodsQuestion) throws Exception {
		// 객체 생성
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO goods_question(goods_code, customer_id"
				+ "		, category, goods_question_memo, createdate)"
				+ "		VALUES(?, ?, ?, ?, NOW())";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsQuestion.getGoodsCode());
		stmt.setString(2, goodsQuestion.getCustomerId());
		stmt.setString(3, goodsQuestion.getCategory());
		stmt.setString(4, goodsQuestion.getGoodsQuestionMemo());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
}
