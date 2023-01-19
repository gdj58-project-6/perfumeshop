package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CounterDao;
import util.DBUtil;
import vo.SiteCounter;

public class CounterService {
	private CounterDao counterDao;
	// 오늘 첫 접속자 수 발생
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
	
	// 첫번째 접속자 수가 아닐때 누적으로
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
	
	// 오늘 총 접속자 수
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
	
	// 사이트가 생성되고 나서 총 접속자 수
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
	
	// 년도별 누적 접속자 수
	public ArrayList<SiteCounter> getSelectCountByYear() {
		ArrayList<SiteCounter> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			list = counterDao.selectCountByYear(conn);
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
	
	// 월별 누적 접속자 수
	public ArrayList<SiteCounter> getSelectCountByMonth() {
		ArrayList<SiteCounter> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			list = counterDao.selectCountByMonth(conn);
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
	
	// 일자별 누적 접속자 수
	public ArrayList<SiteCounter> getSelectCountByDay() {
		ArrayList<SiteCounter> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.counterDao = new CounterDao();
			list = counterDao.selectCountByDay(conn);
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
