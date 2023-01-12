package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Orders;
import vo.ReturnHistory;

public class ReturnHistoryDao {
	// 회원이 취소신청
	public int insertCancelByOrder(Connection conn, ReturnHistory returnHistory) throws Exception {
		int row = 0;
		String sql = "INSERT INTO return_history(order_code, customer_id, return_memo, return_state, createdate) VALUES(?, ?, ?, DEFAULT(return_state), NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, returnHistory.getOrderCode());
		stmt.setString(2, returnHistory.getCustomerId());
		stmt.setString(3, returnHistory.getReturnMemo());
		
		row = stmt.executeUpdate();
		
		return row;
	}
	
	// 관리자용 반품 리스트
	public ArrayList<HashMap<String, Object>> selectReturnHistoryList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT "
					+ "	o.order_code orderCode "
					+ ", o.customer_id customerId "
					+ ", o.order_price orderPrice "
					+ ", o.order_state orderState "
					+ ", rh.return_memo returnMemo "
					+ ", rh.return_state returnState "
					+ ", rh.createdate createdate "
					+ "FROM return_history rh INNER JOIN orders o "
					+ "ON rh.order_code = o.order_code ";
					// + "WHERE rh.return_state LIKE '%?%'" 검색 기능 구현 예정
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("customerId", rs.getString("customerId"));
			m.put("orderPrice", rs.getInt("orderPrice"));
			m.put("orderState", rs.getString("orderState"));
			m.put("returnMemo", rs.getString("returnMemo"));
			m.put("returnState", rs.getString("returnState"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		
		rs.close();
		stmt.close();
		
		return list;
	}
	
	// 관리자가 반품승인하면 returnState 변경
	public int updateReturnStateByAdmin(Connection conn, Orders orders) throws Exception {
		int row = 0;
		String sql = "UPDATE return_history SET return_state = '승인완료' WHERE order_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orders.getOrderCode());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
