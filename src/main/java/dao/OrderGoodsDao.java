package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import vo.OrderGoods;

public class OrderGoodsDao {
	// 카트에서 주문  orders에서 order_code 
	public int insertOrderGoodsByCart(Connection conn, ArrayList<HashMap<String, Object>> list, int orderCode) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		
		for(HashMap<String, Object> m : list) {
			String sql = "INSERT INTO order_goods(order_code, goods_code, order_goods_price, order_goods_quantity) VALUES(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orderCode);
			stmt.setInt(2, (int)m.get("goodsCode"));
			stmt.setInt(3, (int)m.get("goodsPrice"));
			stmt.setInt(4, (int)m.get("cartQuantity"));
			
			row = stmt.executeUpdate();
		}
		
		stmt.close();
		
		return row;
	}
	
	public int insertOrderGoods(Connection conn, int orderCode, HashMap<String, Object> goodsOne) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		
		String sql = "INSERT INTO order_goods(order_code, goods_code, order_goods_price, order_goods_quantity) VALUES(?, ?, ?, ?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderCode);
		stmt.setInt(2, (int)goodsOne.get("goodsCode"));
		stmt.setInt(3, (int)goodsOne.get("goodsPrice"));
		stmt.setInt(4, (int)goodsOne.get("goodsQuantity"));
		
		row = stmt.executeUpdate();
		
		stmt.close();
		
		return row;
	}
}
