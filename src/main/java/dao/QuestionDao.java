package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.GoodsQuestion;

public class QuestionDao {
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
		String sql="SELECT"
				+ "		gq.goods_question_code goodsQuestionCode"
				+ "		, gq.goods_code goodsCode"
				+ "		, gq.category category"
				+ "		, gq.goods_question_memo goodsQuestionMemo"
				+ "		, gq.createdate createdate"
				+ "		, g.goods_name goodsName "
				+ "		FROM goods_question gq INNER JOIN goods g "
				+ "		ON gq.goods_code = g.goods_code "
				+ "		ORDER BY gq.goods_question_code ASC LIMIT ?, ?";
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
