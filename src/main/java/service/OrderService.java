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
	
	// 로그인한 회원의 주문 리스트
	public ArrayList<HashMap<String, Object>> getSelectOrderList(Customer customer) {
		 ArrayList<HashMap<String, Object>> list = null;
		 Connection conn = null;
		 
		 try {
			 conn = DBUtil.getConnection();
			 this.orderDao = new OrderDao();
			 list = orderDao.selectOrderList(conn, customer);
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
	
	// 주문내역 상세보기
	public HashMap<String, Object> getSelectOrderOne(int orderCode) {
		HashMap<String, Object> orderOne = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.orderDao = new OrderDao();
			orderOne = orderDao.selectOrderOne(conn, orderCode);
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
		
		return orderOne;
	}
}
