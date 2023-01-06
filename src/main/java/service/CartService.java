package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CartDao;
import util.DBUtil;
import vo.Cart;

public class CartService {
	private CartDao cartDao;
	
	// AddCart
	public int addCart(Cart cart) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.cartDao = new CartDao();
			row = cartDao.addCart(conn, cart);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}
