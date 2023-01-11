package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.OrderDao;
import util.DBUtil;
import vo.Customer;

public class OrderService {
	private OrderDao orderDao;
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
