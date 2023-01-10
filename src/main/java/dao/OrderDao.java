package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class OrderDao {
	// 주
	public HashMap<String, Object> selectOrderOne(Connection conn, int orderCode) throws Exception {
		// 초기화
		HashMap<String, Object> orderOne = null;
		ResultSet rs = null;
		String sql = "SELECT "
				+ "	o.order_code orderCode "
				+ "	, o.goods_code goodsCode "
				+ "	, t1.goods_name goodsName "
				+ "	, t1.filename filename "
				+ "	, t1.goods_price goodsPrice "
				+ "	, o.order_quantity orderQuantity "
				+ "	, o.order_price orderPrice "
				+ "	, t.customerId customerId "
				+ "	, t.customerName customerName "
				+ "	, t.customerPhone customerPhone "
				+ "	, t.address address "
				+ "	, o.order_memo orderMemo "
				+ "	, o.order_state orderState "
				+ "	, o.createdate createdate "
				+ "FROM( SELECT c.customer_id customerId "
				+ "				, c.customer_name customerName "
				+ "				, c.customer_phone customerPhone "
				+ "				, ca.address_code "
				+ "				, ca.address address "
				+ "			FROM customer c INNER JOIN customer_address ca "
				+ "			ON c.customer_id = ca.customer_id) t INNER JOIN orders o "
				+ "																ON t.customerId = o.customer_id "
				+ "																INNER JOIN (SELECT g.goods_code goodsCode, g.goods_name, gi.filename, g.goods_price	"
				+ "																					FROM goods g INNER JOIN goods_img gi "
				+ "																										ON g.goods_code = gi.goods_code) t1 "
				+ "																ON o.goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			orderOne = new HashMap<String, Object>();
			orderOne.put("orderCode", rs.getInt("orderCode"));
			orderOne.put("goodsCode", rs.getInt("goodsCode"));
			orderOne.put("goodsName", rs.getString("goodsName"));
			orderOne.put("filename", rs.getString("filename"));
			orderOne.put("goodsPrice", rs.getInt("goodsPrice"));
			orderOne.put("orderQuantity", rs.getInt("orderQuantity"));
			orderOne.put("orderPrice", rs.getInt("orderPrice"));
			orderOne.put("customerId", rs.getString("customerId"));
			orderOne.put("customerName", rs.getString("customerName"));
			orderOne.put("customerPhone", rs.getString("customerPhone"));
			orderOne.put("address", rs.getString("address"));
			orderOne.put("orderMemo", rs.getString("orderMemo"));
			orderOne.put("orderState", rs.getString("orderState"));
			orderOne.put("createdate", rs.getString("createdate"));
		}
		return orderOne;
	}
}
