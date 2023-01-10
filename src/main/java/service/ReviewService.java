package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.ReviewDao;
import util.DBUtil;

public class ReviewService {
	private ReviewDao reviewDao;
	
	// 리뷰 페이징
	public int getReviewCount() {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao호출
			this.reviewDao = new ReviewDao();
			row = reviewDao.selectCountByReview(conn);
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
	
	// 리뷰 출력
	public ArrayList<HashMap<String, Object>> getReviewList(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao호출
			this.reviewDao = new ReviewDao();
			list = reviewDao.selectReview(conn, beginRow, rowPerPage);
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
		return list;
	}
}
