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
	
	// RemoveGoods
	public int removeGoods(Goods goods, GoodsImg goodsImg) {
		int goodsRow = 0;
		int goodsImgRow = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 1) 
			this.goodsImgDao = new GoodsImgDao();
			goodsImgRow = goodsImgDao.removeGoodsImg(conn, goodsImg);
			
			// 2) 
			this.goodsDao = new GoodsDao();
			goodsRow = goodsDao.removeGoods(conn, goods);
			
			conn.commit(); // 1) + 2) 일괄처리 -> 트랜젝션 -> 하나라도 실패시 롤백
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
		
		return goodsRow+goodsImgRow;
	}
	
	// ModifyGoodsForm 
	public ArrayList<HashMap<String, Object>> modifyGoodsForm(int goodsCode) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
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
	
	// ModifyGoodsAction
	public int modifyGoodsAction(Goods goods, GoodsImg goodsImg, String dir) {
		int goodsRow = 0;
		int goodsImgRow = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// 1)
			this.goodsDao = new GoodsDao();
			goodsRow = goodsDao.modifyGoodsAction(conn, goods);
			// 2)
			this.goodsImgDao = new GoodsImgDao();
			goodsImgRow = goodsImgDao.modifyGoodsImg(conn, goodsImg);
			
			conn.commit(); // 1) + 2) 일괄처리 -> 트랜젝션 -> 하나라도 실패시 롤백
		} catch (Exception e) {
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
		return goodsRow+goodsImgRow;
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
	public int goodsCount(String word) {
		int row = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			row = goodsDao.goodsCount(conn, word);
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
	public ArrayList<HashMap<String, Object>> getGoodsList(int currentPage, int rowPerPage, String word, String sort, String category) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			int beginRow = (currentPage-1)*rowPerPage;
			this.goodsDao = new GoodsDao();
			list = goodsDao.selectGoodsList(conn, beginRow, rowPerPage, word, sort, category);
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
	
	// 바로구매시 보여줄 굿즈 상세
	public HashMap<String, Object> getGoodsOneByOrder(Goods goods) {
		HashMap<String, Object> goodsOne = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			goodsOne = goodsDao.goodsOneByOrder(conn, goods);
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
		
		return goodsOne;
	}
	
	// 통계
	// 상품별 총 매출
	public ArrayList<HashMap<String, Object>> getSelectGoodsSumList() {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.goodsDao = new GoodsDao();
			list = goodsDao.selectGoodsSumList(conn);
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
