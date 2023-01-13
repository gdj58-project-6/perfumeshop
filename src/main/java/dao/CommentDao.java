package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.GoodsQuestionComment;


public class CommentDao {
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
	public ArrayList<HashMap<String, Object>> selectGoodsQuestionListByAdmin(Connection conn, int beginRow, int goodsRowPerPage) throws Exception {
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
		stmt.setInt(2, goodsRowPerPage);
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
			m.put("goodsCommentMemo", rs.getString("goodsCommentMemo"));
			m.put("gqcCreatedate", rs.getString("gqcCreatedate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
}
