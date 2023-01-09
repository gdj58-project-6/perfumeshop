package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CounterDao;
import util.DBUtil;

public class CounterService {
	private CounterDao counterDao;
	public void getInsertCount() {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			counterDao.insertCount(conn);
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
	}
	
	public void getUpdateCount() {
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			counterDao.updateCount(conn);
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
	}
	
	public int getSelectTodayCount() {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			row = counterDao.selectTodayCount(conn);
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
	
	public int getSelectTotalCount() {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			row = counterDao.selectTotalCount(conn);
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
