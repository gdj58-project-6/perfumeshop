package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.CommentDao;
import util.DBUtil;
import vo.GoodsQuestionComment;
import vo.QuestionComment;

public class CommentService {
	private CommentDao commentDao;
	// 주문문의 답변 삭제
	public int removeQuestionComment(int commentCode) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 & 호출
			this.commentDao = new CommentDao();
			row = commentDao.deleteQuestionCommentByAdmin(conn, commentCode);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// 주문문의 답변 등록
	public int addQuestionComment(QuestionComment questionComment) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 호출
			this.commentDao = new CommentDao();
			row = commentDao.insertQuestionCommentByAdmin(conn, questionComment);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	// 상품문의 목록수
	public int getQuestionCountByAdmin(String category, String sort, String word) {
		// 객체 생성
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			//드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 호출
			this.commentDao = new CommentDao();
			row = commentDao.selectQuestionCountByAdmin(conn, category, sort, word);
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
	// 주문문의
	public ArrayList<HashMap<String, Object>> getQuestionListByAdmin(String category, String sort, String word, int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 & 호출
			this.commentDao = new CommentDao();
			list = commentDao.selectQuestionListByAdmin(conn, category, sort, word, beginRow, rowPerPage);
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
	// 상품문의 답변 삭제
	public int removeGoodsQuestionComment(int goodsCommentCode) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 & 호출
			this.commentDao = new CommentDao();
			row = commentDao.deleteGoodsQuestionCommentByAdmin(conn, goodsCommentCode);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	// 상품문의 답변 등록
	public int addGoodsQuestionComment(GoodsQuestionComment goodsQuestionComment) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 호출
			this.commentDao = new CommentDao();
			row = commentDao.insertGoodsQuestionCommentByAdmin(conn, goodsQuestionComment);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	// 상품문의 목록수
	public int getGoodsQuestionCountByAdmin() {
		// 객체 생성
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			//드라이버 연결
			conn = DBUtil.getConnection();
			// dao초기화 호출
			this.commentDao = new CommentDao();
			row = commentDao.selectGoodsQuestionCountByAdmin(conn);
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
	// 상품문의
	public ArrayList<HashMap<String, Object>> getGoodsQuestionListByAdmin(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list =  null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 호출
			this.commentDao = new CommentDao();
			list = commentDao.selectGoodsQuestionListByAdmin(conn, beginRow, rowPerPage);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
