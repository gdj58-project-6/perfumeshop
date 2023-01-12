package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Cart;

public class CartDao {
	
	// 장바구니 수정 modifyCartList
	public int modifyCartList(Connection conn, Cart cart) throws Exception {
		int row = 0;
		String sql = "UPDATE cart SET cart_quantity = ? WHERE goods_code = ? AND customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, cart.getCartQuantity());
		stmt.setInt(2, cart.getGoodsCode());
		stmt.setString(3, cart.getCustomerId());
		
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	
	// 장바구니 리스트 삭제(전부) RemoveAllCartList
	public int removeAllCartList(Connection conn, String customerId) throws Exception {
		int row = 0;
		String sql ="DELETE FROM cart WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customerId);
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	
	
	// 장바구니 리스트 삭제(개별) RemoveCartList
	public int removeCartList(Connection conn, int goodsCode) throws Exception {
		int row = 0;
		String sql = "DELETE FROM cart WHERE goods_code = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, goodsCode);
		row = stmt.executeUpdate();
	
		stmt.close();
		return row;
	}
	
	// 장바구니 리스트 AddCartList
	public ArrayList<HashMap<String, Object>> selectCartList(Connection conn, String id) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT"
					+ "	g.goods_code goodsCode "
					+ ", g.goods_name goodsName "
					+ ", g.goods_price goodsPrice "
					+ ", gi.filename fileName "
					+ ", c.customer_id customerId "
					+ ", c.cart_quantity cartQuantity "
					+ "FROM goods g INNER JOIN goods_img gi "
					+ "ON g.goods_code = gi.goods_code "
					+ "INNER JOIN cart c "
					+ "ON c.goods_code = gi.goods_code "
					+ "WHERE c.customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
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
		
		stmt.close();
		
		return row;
	}
	
	// 주문 후 customerId를 받아서 카트 리스트 삭제
	public int deleteCartList(Connection conn, String id) throws Exception {
		int row = 0;
		String sql = "DELETE FROM cart WHERE customer_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
	
