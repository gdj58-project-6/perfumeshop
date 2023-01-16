package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Customer;
import vo.PointHistory;

public class PointHistoryDao {
	// 리뷰작성시 포인트 적립, 구매시 포인트 사용, 주문취소시 포인트 취소
	public int insertPoint(Connection conn, PointHistory pointHistory) throws Exception {
		int row = 0;
		String sql = "INSERT INTO point_history(order_code, goods_code, customer_id, point_kind, POINT, createdate) VALUES(?, ?, ?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pointHistory.getOrderCode());
		stmt.setInt(2, pointHistory.getGoodsCode());
		stmt.setString(3, pointHistory.getCustomerId());
		stmt.setString(4, pointHistory.getPointKind());
		stmt.setInt(5, pointHistory.getPoint());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
	
	// 회원의 포인트 적립, 사용내역 출력
	public ArrayList<PointHistory> selectPointList(Connection conn, String id) throws Exception {
		ArrayList<PointHistory> list = new ArrayList<PointHistory>();
		ResultSet rs = null;
		String sql = "SELECT "
					+ "	order_code orderCode "
					+ ", goods_code goodsCode "
					+ ", point_kind pointKind "
					+ ", point "
					+ ", createdate "
					+ "FROM point_history "
					+ "WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			PointHistory p = new PointHistory();
			p.setOrderCode(rs.getInt("orderCode"));
			p.setGoodsCode(rs.getInt("goodsCode"));
			p.setPointKind(rs.getString("pointKind"));
			p.setPoint(rs.getInt("point"));
			p.setCreatedate(rs.getString("createdate"));
			list.add(p);
		}
		
		rs.close();
		stmt.close();
		
		return list;
		
	}
	
	// 현재 사용할 수 있는 포인트 집계용
	// 회원이 사용한 포인트
	public int selectPoint(Connection conn, String id, String kind) throws Exception {
		int usePoint = 0;
		ResultSet rs = null;
		String sql = "SELECT IFNULL(SUM(POINT), 0) point FROM point_history WHERE customer_id = ? AND point_kind = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.setString(2, kind);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			usePoint = rs.getInt("point");
		}
		
		rs.close();
		stmt.close();
		
		return usePoint;
	}
	
	// 탈퇴시 point history 지우고 탈퇴
	public int deletePointHistory(Connection conn, Customer customer) throws Exception {
		int row = 0;
		String sql = "DELETE FROM point_history WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
