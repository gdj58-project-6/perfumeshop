package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;

public class NoticeDao {
	// 공지사항 삭제
	public int deleteNotice(Connection conn, int noticeCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "DELETE FROM notice WHERE notice_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, noticeCode);
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 공지사항 수정
	public int updateNotice(Connection conn, Notice notice) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "UPDATE notice SET notice_title=?, notice_content=?, emp_id=?, createdate=now() WHERE notice_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setString(3, notice.getEmpId());
		stmt.setInt(4, notice.getNoticeCode());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 공지사항 수정 폼 출력
	public Notice selectNoticeByModifyNotice(Connection conn, int noticeCode) throws Exception {
		// 객체 초기화
		Notice n = null;
		// 쿼리문 작성
		String sql = "SELECT notice_code noticeCode"
				+ "		, notice_title noticeTitle"
				+ "		, notice_content noticeContent"
				+ "		, emp_id empId"
				+ "		, createdate"
				+ "		FROM notice WHERE notice_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ? 값지정
		stmt.setInt(1, noticeCode);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setEmpId(rs.getString("empId"));
			n.setCreatedate(rs.getString("createdate"));
		}
		stmt.close();
		rs.close();
		return n;
	}
	// 공지사항보기
	public Notice selectNotice(Connection conn, int noticeCode) throws Exception {
		// 객체 초기화
		Notice n = null;
		// 쿼리문 작성
		String sql = "SELECT notice_code noticeCode"
				+ "		, notice_title noticeTitle"
				+ "		, notice_content noticeContent"
				+ "		, emp_id empId"
				+ "		, createdate"
				+ "		FROM notice WHERE notice_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ? 값지정
		stmt.setInt(1, noticeCode);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setEmpId(rs.getString("empId"));
			n.setCreatedate(rs.getString("createdate"));
		}
		stmt.close();
		rs.close();
		return n;
	}
	// 공지사항 입력
	public int insertNotice(Connection conn, Notice notice) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO notice(notice_title, notice_content, emp_id, createdate) VALUES(?, ?, ?, NOW())";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setString(1, notice.getNoticeTitle());
		stmt.setString(2, notice.getNoticeContent());
		stmt.setString(3, notice.getEmpId());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// home화면 페이징 목록수
	public int selectNoticeCountByHome(Connection conn) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "SELECT COUNT(*) FROM notice";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		
		stmt.close();
		rs.close();
		return row;
	}
	// home화면 공지목록
	public ArrayList<Notice> selectNoticeListByhome(Connection conn, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<Notice> list = null;
		// 쿼리문 작성
		String sql = "SELECT "
				+ " notice_code noticeCode"
				+ " , notice_title noticeTitle"
				+ " , emp_id empId"
				+ " , createdate"
				+ " FROM notice ORDER BY notice_code DESC LIMIT ?,?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		
		list = new ArrayList<Notice>();
		while(rs.next()) {
			Notice n = new Notice();
			n.setNoticeCode(rs.getInt("noticeCode"));
			n.setNoticeTitle(rs.getString("noticeTitle"));
			n.setEmpId(rs.getString("empId"));
			n.setCreatedate(rs.getString("createdate"));
			list.add(n);
		}
		
		stmt.close();
		rs.close();
		return list;
	}
}
