package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.QuestionDao;
import util.DBUtil;
import vo.GoodsQuestion;

public class QuestionService {
	private QuestionDao questionDao;
	
	// 상품문의 리스트
	public ArrayList<HashMap<String, Object>> getGoodsQuestionList(int currentPage, int rowPerPage) {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list =  null;
		// 드라이버 초기화
		Connection conn = null;
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 호출
			this.questionDao = new QuestionDao();
			list = questionDao.selectGoodsQuestionList(conn, beginRow, rowPerPage);
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
	
	// 상품문의 문의하기
	public int insertGoodsQuestion(GoodsQuestion goodsQuestion) {
		// 객체 초기화
		int row = 0;
		// 드라이버 초기화
		Connection conn = null;
		
		try {
			// 드라이버 연결
			conn = DBUtil.getConnection();
			// dao 호출
			this.questionDao = new QuestionDao();
			row = questionDao.insertGoodsQuestion(conn, goodsQuestion);
			// 커밋하기
			conn.commit();
		} catch(Exception e) {
			// 추가 실패시 롤백
			try {
				conn.rollback();
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 추가 성공시 자원반납
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
}
