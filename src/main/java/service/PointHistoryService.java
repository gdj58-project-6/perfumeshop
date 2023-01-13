package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PointHistoryDao;
import util.DBUtil;
import vo.PointHistory;

public class PointHistoryService {
	private PointHistoryDao pointHistoryDao;
	// 회원의 포인트 적립, 사용내역 출력
	public ArrayList<PointHistory> getSelectPointList(String id) {
		ArrayList<PointHistory> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pointHistoryDao = new PointHistoryDao();
			list = pointHistoryDao.selectPointList(conn, id);
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
	
	// 현재 사용할 수 있는 포인트 집계용
	// 회원이 사용한 포인트
	public int getSelectUsePoint(String id) {
		int usePoint = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pointHistoryDao = new PointHistoryDao();
			usePoint = pointHistoryDao.selectUsePoint(conn, id);
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
		
		return usePoint;
	}
	
	// 회원이 적립한 포인트
	public int getSelectSavePoint(String id) {
		int savePoint = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pointHistoryDao = new PointHistoryDao();
			savePoint = pointHistoryDao.selectSavePoint(conn, id);
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
		
		return savePoint;
	}
}
