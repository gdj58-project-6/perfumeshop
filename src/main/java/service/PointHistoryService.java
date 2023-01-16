package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PointHistoryDao;
import util.DBUtil;
import vo.PointHistory;

public class PointHistoryService {
	private PointHistoryDao pointHistoryDao;
	// 리뷰작성시 포인트 적립, 구매시 포인트 사용, 주문취소시 포인트 취소
	public int getInsertPoint(PointHistory pointHistory) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pointHistoryDao = new PointHistoryDao();
			row = pointHistoryDao.insertPoint(conn, pointHistory);
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
	// 회원이 사용, 적립한 포인트
	public int getSelectPoint(String id, String kind) {
		int usePoint = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pointHistoryDao = new PointHistoryDao();
			usePoint = pointHistoryDao.selectPoint(conn, id, kind);
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
}
