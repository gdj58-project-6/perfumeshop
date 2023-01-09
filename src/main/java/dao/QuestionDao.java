package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionDao {
	// 상품문의 리스트
	public ArrayList<HashMap<String, Object>> selectQuestionList(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 쿼리문 작성
		String sql="SELECT "
				+ "		q.question_code questionCode"
				+ "		, q.orders_code ordersCode"
				+ "		, o.goods_code goodsCode"
				+ "		, o.customer_id customerId"
				+ "		, q.category category"
				+ "		, q.question_memo questionMemo"
				+ "		, q.createdate createdate"
				+ "		FROM question q INNER JOIN orders o ON q.orders_code = o.order_code"
				+ "		ORDER BY q.question_code ASC LIMIT ?,?";
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
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("ordersCode", rs.getInt("ordersCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("customerId", rs.getString("customerId"));
			m.put("category", rs.getString("category"));
			m.put("questionMemo", rs.getString("questionMemo"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
}
