package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Notice;

public class ShopDao {
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
				+ " , notice_content noticeContent"
				+ " , emp_id empId"
				+ " , createdate"
				+ " FROM notice ORDER BY notice_code ASC LIMIT ?,?";
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
			n.setNoticeContent(rs.getString("noticeContent"));
			n.setEmpId(rs.getString("empId"));
			n.setCreatedate(rs.getString("createdate"));
			list.add(n);
		}
		
		stmt.close();
		rs.close();
		return list;
	}
}
