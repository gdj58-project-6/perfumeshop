package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Customer;

public class OrderDao {
	// 관리자용 모든 주문 리스트
	public ArrayList<HashMap<String, Object>> selectAllOrderList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT "
				+ "	o.order_code orderCode "
				+ "	, gi.filename filename "
				+ "	, g.goods_name goodsName "
				+ "	, g.goods_price goodsPrice "
				+ "	, o.order_quantity orderQuantity "
				+ "	, o.order_price orderPrice "
				+ "	, o.customer_id customerId "
				+ "	, c.customer_name customerName "
				+ "	, o.order_state orderState "
				+ "	, o.createdate createdate "
				+ "FROM orders o INNER JOIN goods g "
				+ "					ON o.goods_code = g.goods_code "
				+ "					INNER JOIN goods_img gi "
				+ "					ON g.goods_code = gi.goods_code "
				+ "					INNER JOIN customer c "
				+ "					ON o.customer_id = c.customer_id";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("filename", rs.getString("filename"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("orderQuantity", rs.getInt("orderQuantity"));
			m.put("orderPrice", rs.getInt("orderPrice"));
			m.put("customerId", rs.getString("customerId"));
			m.put("customerName", rs.getString("customerName"));
			m.put("orderState", rs.getString("orderState"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		
		return list;
	}
	
	// 로그인한 회원의 주문 리스트
	public ArrayList<HashMap<String, Object>> selectOrderList(Connection conn, Customer customer) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ResultSet rs = null;
		String sql = "SELECT "
					+ "	o.order_code orderCode "
					+ "	, gi.filename filename "
					+ " , g.goods_name goodsName "
					+ "	, o.order_quantity orderQuantity "
					+ "	, o.order_price orderPrice "
					+ "	, o.order_state orderState "
					+ "	, o.createdate createdate "
					+ "FROM orders o INNER JOIN goods g "
					+ "					ON o.goods_code = g.goods_code "
					+ "					INNER JOIN goods_img gi "
					+ "					ON g.goods_code = gi.goods_code "
					+ "WHERE o.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getCustomerId());
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("orderCode", rs.getString("orderCode"));
			m.put("goodsName",rs.getString("goodsName"));
			m.put("filename", rs.getString("filename"));
			m.put("orderQuantity", rs.getString("orderQuantity"));
			m.put("orderPrice", rs.getString("orderPrice"));
			m.put("orderState", rs.getString("orderState"));
			m.put("createdate", rs.getString("createdate"));
			list.add(m);
		}
		
		return list;
	}
	
	// 주문내역 상세보기
	public HashMap<String, Object> selectOrderOne(Connection conn, int orderCode) throws Exception {
		// 초기화
		HashMap<String, Object> orderOne = null;
		ResultSet rs = null;
		String sql = "SELECT "
					+ "	o.order_code orderCode "
					+ ", o.goods_code goodsCode "
					+ ", t1.goods_name goodsName "
					+ ", t1.filename filename "
					+ ", t1.goods_price goodsPrice "
					+ ", o.order_quantity orderQuantity "
					+ ", o.order_price orderPrice "
					+ ", t.customerId customerId "
					+ ", t.customerName customerName "
					+ ", t.customerPhone customerPhone "
					+ ", t.address address "
					+ ", o.order_memo orderMemo "
					+ ", o.order_state orderState "
					+ ", o.createdate createdate "
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
					+ "																ON o.goods_code = t1.goodsCode "
					+ "WHERE o.order_code = ?";
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
