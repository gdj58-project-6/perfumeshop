package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import vo.Cart;
import vo.GoodsImg;

public class CartDao {
	
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
	
