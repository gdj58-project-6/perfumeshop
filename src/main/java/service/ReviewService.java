package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.PointHistoryDao;
import dao.ReviewDao;
import util.DBUtil;
import vo.PointHistory;
import vo.Review;

public class ReviewService {
	private ReviewDao reviewDao;
	private PointHistoryDao pointHistoryDao;
	// 직원용 리뷰리스트 출력
	public ArrayList<HashMap<String, Object>> getSelectReviewListByAdmin() {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			this.reviewDao = new ReviewDao();
			list = reviewDao.selectReviewListByAdmin(conn);
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
	
	// 로그인한 회원이 작성한 리뷰리스트, 리뷰 내용까지 한 번에 출력
	public ArrayList<HashMap<String, Object>> getSelectReviewList(String id) {
		ArrayList<HashMap<String, Object>> list = null;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			this.reviewDao = new ReviewDao();
			list = reviewDao.selectReviewList(conn, id);
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
	
	// 리뷰 작성시 포인트 적립
	public int getInsertReview(Review review, PointHistory pointHistory) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.reviewDao = new ReviewDao();
			int result = reviewDao.insertReviewByCustomer(conn, review);
			if(result == 0) {
				throw new Exception();
			} else { // 리뷰가 등록이되면
				this.pointHistoryDao = new PointHistoryDao();
				row = pointHistoryDao.insertPoint(conn, pointHistory); // 포인트 등록
				if(row == 0) {
					throw new Exception(); // 포인트도 등록이안되면 예외발생
				}
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}

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
	public ArrayList<HashMap<String, Object>> getReviewListByCustomer(int currentPage2, int rowPerPage) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage2 - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao호출
			this.reviewDao = new ReviewDao();
			list = reviewDao.selectReviewByCustomer(conn, beginRow, rowPerPage);
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
	
	// 리뷰 수정
	public int getUpdateReview(Review review) {
		int row = 0;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			this.reviewDao = new ReviewDao();
			row = reviewDao.updateReview(conn, review);
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

	// 리뷰 삭제
	public int getDeleteReview(Review review, PointHistory pointHistory) {
		int row = 0;
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			this.reviewDao = new ReviewDao();
			int result = reviewDao.deleteReview(conn, review);
			if (result == 0) {
				throw new Exception();
			} else {
				this.pointHistoryDao = new PointHistoryDao();
				row = pointHistoryDao.insertPoint(conn, pointHistory);
				if (row == 0) {
					throw new Exception();
				}
			}
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

		return row;
	}
}
