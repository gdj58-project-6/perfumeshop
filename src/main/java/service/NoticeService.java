package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.NoticeDao;
import util.DBUtil;
import vo.Notice;

public class NoticeService {
	private NoticeDao noticeDao;
	// 공지삭제
	public int removeNotice(int noticeCode) {
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.noticeDao = new NoticeDao();
			row = noticeDao.deleteNotice(conn, noticeCode);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
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
	// 공지사항 수정
	public int modifyNotice(Notice notice) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 호출
			this.noticeDao = new NoticeDao();
			row = noticeDao.updateNotice(conn, notice);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 수정 실패시 롤백
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
	// 공지 사항 수정 폼
	public Notice getNoticeByModifyNotice(int noticeCode) {
		// 객체초기화
		Notice n = null;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao호출
			this.noticeDao = new NoticeDao();
			n = noticeDao.selectNotice(conn, noticeCode);
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
		
		return n;
	}
	
	// 공지사항 보기
	public Notice getNotice(int noticeCode) {
		Notice n = null;
		Connection conn = null;
		
		try {
			conn = DBUtil.getConnection();
			this.noticeDao = new NoticeDao();
			n = noticeDao.selectNotice(conn, noticeCode);
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
		
		return n;
	}
	// 공지사항 입력
	public int addInsertNotice(Notice notice) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 호출
			this.noticeDao = new NoticeDao();
			row = noticeDao.insertNotice(conn, notice);
			// 커밋
			conn.commit();
		} catch(Exception e) {
			// 입력 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 입력 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
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
			this.noticeDao = new NoticeDao();
			// dao호출
			row = noticeDao.selectNoticeCountByHome(conn);
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
			this.noticeDao = new NoticeDao();
			// dao호출
			list = noticeDao.selectNoticeListByhome(conn, beginRow, rowPerPage);
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
