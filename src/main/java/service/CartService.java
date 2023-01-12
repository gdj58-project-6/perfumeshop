package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import dao.GoodsDao;
import util.DBUtil;
import vo.Cart;

public class CartService {
	private CartDao cartDao;
	
	// modifyCartList
	public int modifyCartList(Cart cart) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.cartDao = new CartDao();
			row = cartDao.modifyCartList(conn, cart);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} 
		return row;
	}
	
	// RemoveAllCartList
	public int removeAllCartList(String customerId) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.cartDao = new CartDao();
			row = cartDao.removeAllCartList(conn, customerId);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} 
		return row;
	}
	
	
	// RemoveCartList
	public int removeCartList(int goodsCode) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.cartDao = new CartDao();
			row = cartDao.removeCartList(conn, goodsCode);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} 
		return row;
	}
	
	
	// CartList
	public ArrayList<HashMap<String, Object>> getCartList(String id) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.cartDao = new CartDao();
			list = cartDao.selectCartList(conn, id);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
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
