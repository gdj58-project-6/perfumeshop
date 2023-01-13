package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.PointHistory;

public class PointHistoryDao {
	// 리뷰작성시 포인트 적립
	public int insertPointByReview(Connection conn, PointHistory pointHistory) throws Exception {
		int row = 0;
		String sql = "INSERT INTO point_history(goods_code, point_kind, POINT, createdate) VALUES(?, '적립', ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pointHistory.getGoodsCode());
		stmt.setInt(2, pointHistory.getPoint());
		
		row = stmt.executeUpdate();
		
		return row;
	}
}
