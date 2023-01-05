package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ShopDao;
import util.DBUtil;
import vo.Notice;

public class ShopService {
	private ShopDao shopDao;
	
	// home화면 페이징할 목록의수
	public int getHomeCount() {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결 오토커밋끄는거 포함
			conn = DBUtil.getConnection();
			// dao 초기화 및 공간 확보
			this.shopDao = new ShopDao();
			// dao호출
			row = shopDao.selectNoticeCountByHome(conn);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// home화면 공지목록
	public ArrayList<Notice> getNoticeList(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<Notice> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 초기화및 공간확보
			this.shopDao = new ShopDao();
			// dao호출
			list = shopDao.selectNoticeListByhome(conn, beginRow, rowPerPage);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				//자원반납
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
