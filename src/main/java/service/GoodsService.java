package service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import dao.GoodsDao;
import dao.GoodsImgDao;
import util.DBUtil;
import vo.Goods;
import vo.GoodsImg;

public class GoodsService {
	private GoodsDao goodsDao;
	private GoodsImgDao goodsImgDao;
	// AddGoods
	public void addGoods(Goods goods, GoodsImg goodsImg, String dir) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			this.goodsDao = new GoodsDao();
			HashMap<String, Integer> map = goodsDao.addGoods(conn, goods);
			
			goodsImg.setGoodsCode(map.get("autoKey"));
			this.goodsImgDao = new GoodsImgDao();
			goodsImgDao.addGoodsImg(conn, goodsImg);
			
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
				// db작업 실패시 이미 업로드해버린 파일을 불러와 삭제
				File f = new File(dir+"\\"+goodsImg.getFileName());
				if(f.exists()) {
					f.delete();
				}
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
