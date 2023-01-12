package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CartDao;
import dao.OrderDao;
import dao.OrderGoodsDao;
import util.DBUtil;
import vo.Customer;
import vo.Orders;

public class OrderService {
	private OrderDao orderDao;
	private OrderGoodsDao orderGoodsDao;
	private CartDao cartDao;
	// 주문하기 : OrderDao -> OrderGoodsDao
	
	public int getInsertOrder(Orders orders, ArrayList<HashMap<String, Object>> list) {
		int row = 0;
		int orderCode = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			/*HashMap<String, Integer> map = orderDao.insertOrderByCustomer(conn, orders);
			orderCode = (int)map.get("autoKey");
			if(map == null) { // order insert가 진행되었으면
				this.orderGoodsDao = new OrderGoodsDao();
				int result = orderGoodsDao.insertOrderGoods(conn, list, orderCode);
				if(result != 1) { // insertOrderGoods안되었으면 강제로 예외발생시켜서 catch절로 이동 후 rollback;
					throw new Exception();
				} else {
					this.cartDao = new CartDao();
					row = cartDao.deleteCartList(conn, orders.getCustomerId());
					if(row != 1) {
						throw new Exception();							
					}
				}
			} else {
				throw new Exception();
			}*/
			HashMap<String, Integer> map = orderDao.insertOrderByCustomer(conn, orders);
			orderCode = (int)map.get("autoKey");
			if(map == null) {
				throw new Exception();
			} else {
				this.orderGoodsDao = new OrderGoodsDao();
				int result = orderGoodsDao.insertOrderGoods(conn, list, orderCode);
				if(result == 0) {
					throw new Exception();
				} else {
					this.cartDao = new CartDao();
					row = cartDao.deleteCartList(conn, orders.getCustomerId());
					if(row == 0) {
						throw new Exception();
					}
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				orderCode = 0;
				conn.rollback();
			} catch (SQLException e1) {
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
		
		return orderCode;
	}
	
	// orderOne
	// orderCode에 대한 주문자 정보 출력
	public HashMap<String, Object> getSelectCustomerByOrderList(int orderCode) {
		HashMap<String, Object> customerByOrder = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			customerByOrder = orderDao.selectCustomerByOrder(conn, orderCode);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return customerByOrder;
	}
	
	// orderCode에 대한 구매 굿즈 출력
	public ArrayList<HashMap<String, Object>> getSelectOrderByGoodsList(int orderCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			list = orderDao.selectOrderByGoodsList(conn, orderCode);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 관리자용 모든 주문 리스트
	public ArrayList<HashMap<String, Object>> getSelectAllOrderList() {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		 try {
			 conn = DBUtil.getConnection();
			 this.orderDao = new OrderDao();
			 list = orderDao.selectAllOrderList(conn);
			 conn.commit();
		 } catch (Exception e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 conn.close();
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
		 }
		 
		 return list;
	}
	
	// 고객용 주문 리스트
	public ArrayList<HashMap<String, Object>> getSelectOrderByCustomerLIst(Customer customer) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			list = orderDao.selectOrderByCustomerList(conn, customer);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
