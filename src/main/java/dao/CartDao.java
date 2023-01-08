package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Cart;

public class CartDao {
	
	// 장바구니 리스트 AddCartList
	public ArrayList<HashMap<String, Object>> selectCartList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT g.goods_code goodsCode, g.goods_name goodsName, g.goods_price goodsPrice, gi.filename fileName, c.customer_id customerId, c.cart_quantity cartQuantity FROM goods g INNER JOIN goods_img gi ON g.goods_code = gi.goods_code INNER JOIN cart c ON c.goods_code = gi.goods_code";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsPrice", rs.getInt("goodsPrice"));
			m.put("fileName", rs.getString("fileName"));
			m.put("customerId", rs.getString("customerId"));
			m.put("cartQuantity", rs.getInt("cartQuantity"));
			list.add(m);
		}
		rs.close();
		stmt.close();
	
		return list;
	}
		
		
	// 장바구니에 상품 추가 AddCart
	public int addCart(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "INSERT INTO cart(goods_code, customer_id, cart_quantity, createdate) VALUES(?, ?, ?, NOW())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cart.getGoodsCode());
		stmt.setString(2, cart.getCustomerId());
		stmt.setInt(3, cart.getCartQuantity());
		
		row = stmt.executeUpdate();
		return row;
	}
}
	
