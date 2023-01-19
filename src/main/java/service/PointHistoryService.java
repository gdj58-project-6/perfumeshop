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
}
