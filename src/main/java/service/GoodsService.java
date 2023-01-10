package service;

import java.awt.image.PackedColorModel;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.GoodsDao;
import dao.GoodsImgDao;
import util.DBUtil;
import vo.Goods;
import vo.GoodsImg;

public class GoodsService {
	private GoodsDao goodsDao;
	private GoodsImgDao goodsImgDao;
	
	// ModifyGoodsForm 
	public ArrayList<HashMap<String, Object>> modifyGoodsForm(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			list = goodsDao.modifyGoodsForm(conn, goodsCode);
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// GoodsOne
	public ArrayList<HashMap<String, Object>> goodsOne(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			list = goodsDao.goodsOne(conn, goodsCode);
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
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 상품 전체 수
	public int goodsCount() {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			row = goodsDao.goodsCount(conn);
			conn.commit();
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
		return row;
	}
	// GoodsList
	public ArrayList<HashMap<String, Object>> getGoodsList(int currentPage, int rowPerPage, String word) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage+1;
			int endRow = beginRow + rowPerPage - 1;
			this.goodsDao = new GoodsDao();
			list = goodsDao.selectGoodsList(conn, beginRow, endRow, word);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(SQLException e1) {
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
		return list;
	}
	
	
	// AddGoods
	public void addGoods(Goods goods, GoodsImg goodsImg, String dir) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
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
