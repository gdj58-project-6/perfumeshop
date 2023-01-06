package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.PwHistoryDao;
import util.DBUtil;

public class PwHistoryService {
	private PwHistoryDao pwHistroyDao;
	public String getSelectCustomerPw(String id, String changePw) {
		String result = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.pwHistroyDao = new PwHistoryDao();
			result = pwHistroyDao.selectPwhistory(conn, id, changePw);
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
		
		return result;
	}
}
