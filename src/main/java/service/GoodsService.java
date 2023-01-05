package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import dao.GoodsDao;
import util.DBUtil;
import vo.Goods;
import vo.GoodsImg;

public class GoodsService {
	private GoodsDao goodsDao;
	private GoodsImg goodsImg;
	
	public void AddGoods(Goods goods) {
		
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			conn.setAutoCommit(false);
			
			HashMap<String, Integer> map = goodsDao.AddGoods(conn, goods);
			
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
