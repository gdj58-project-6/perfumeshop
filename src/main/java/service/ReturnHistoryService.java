package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CustomerDao;
import dao.OrderDao;
import dao.PointHistoryDao;
import dao.ReturnHistoryDao;
import util.DBUtil;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;
import vo.ReturnHistory;

public class ReturnHistoryService {
	private ReturnHistoryDao returnHistoryDao;
	private PointHistoryDao pointHistoryDao;
	private CustomerDao customerDao;
	private OrderDao orderDao;
	// 반품신청하면 history테이블에 insert되고 환불신청으로 상태변경
	public int getInsertCancelByOrder(ReturnHistory refundHistory, Orders orders) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.returnHistoryDao = new ReturnHistoryDao();
			int result = returnHistoryDao.insertCancelByOrder(conn, refundHistory);
			if(result == 0) {
				throw new Exception();
			} else {
				this.orderDao = new OrderDao();
				row = orderDao.updateOrderState(conn, orders);
				if(row == 0) {
					throw new Exception();
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
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
		
		return row;
	}
	
	// 관리자용 반품 리스트
	public ArrayList<HashMap<String, Object>> getSelectReturnHistoryList(String sort, String returnState, String customerId) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.returnHistoryDao = new ReturnHistoryDao();
			list = returnHistoryDao.selectReturnHistoryList(conn, sort, returnState, customerId);
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
	
	// 관리자가 반품승인하면 returnState
	public int getUpdateReturnStateByAdmin(Orders orders) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.returnHistoryDao = new ReturnHistoryDao();
			row = returnHistoryDao.updateReturnStateByAdmin(conn, orders);
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
		
		return row;
	}
}
