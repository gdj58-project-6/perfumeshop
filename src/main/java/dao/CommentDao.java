package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import vo.GoodsQuestionComment;
import vo.QuestionComment;


public class CommentDao {
	// 주문문의 답변삭제
	public int deleteQuestionCommentByAdmin(Connection conn, int commentCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "DELETE FROM question_comment WHERE comment_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, commentCode);
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 주문문의 답변 달기
	public int insertQuestionCommentByAdmin(Connection conn , QuestionComment questionComment) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO question_comment(question_code, comment_memo, createdate) VALUES(?, ?, NOW())";
		// 쿼리객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, questionComment.getQuestionCode());
		stmt.setString(2, questionComment.getCommentMemo());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	
	// 주문문의 목록수
	public int selectQuestionCountByAdmin(Connection conn, String category, String sort, String word) throws Exception {
		// 객체 초기화
		int row = 0;
		// 검색어 null이나 공백이 넘어오면 공백처리
		if(word == null || word.equals("")) {
			word = "";
		}
		// 정렬 null이나 공백이 넘어오면 공백처리
		if(sort == null || sort.equals("")) {
			sort = "";
		}
		// 분류 null이나 공백이 넘어오면 공백처리
		if(category == null || category.equals("")) {
			category = "";
		}
		// 쿼리문 작성
		String sql = "";
		// 쿼리 객체 생성
		PreparedStatement stmt = null;
		// 검색어x
		if(word.equals("")) {
			// 검색어x, 정렬x
			if(sort.equals("")) {
				// 검색어x, 정렬x, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*) FROM question";
					stmt = conn.prepareStatement(sql);
				// 검색어x, 정렬x, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*) FROM question WHERE category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 정렬x, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*) FROM question WHERE category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 정렬x, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*) FROM question WHERE category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 정렬x, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*) FROM question WHERE category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				}
			// 검색어x, 정렬o(답변전 행의수)
			} else if(sort.equals("asc")) {
				// 검색어x, 답변전, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
				// 검색어x, 답변전, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변전, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변전, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변전, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				}
			// 검색어x, 정렬o(답변후 행의수)
			} else if(sort.equals("desc")) {
				// 검색어x, 답변후, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code";
					stmt = conn.prepareStatement(sql);
				// 검색어x, 답변후, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변후, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변후, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				// 검색어x, 답변후, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
				}
			}
		// 검색어o
		} else {
			// 검색어o, 정렬x(답변전or답변후 둘다아님)
			if(sort.equals("")) {
				// 검색어o, 정렬x, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
				// 검색어o, 정렬x, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 정렬x, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 정렬x, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 정렬x, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				}
			// 검색어o, 정렬o(답변전 행의수)	
			} else if(sort.equals("asc")) {
				// 검색어o, 답변전, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
				// 검색어o, 답변전, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변전, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변전, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변전, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q LEFT JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				}
			// 검색어o, 정렬o(답변후 행의수)
			} else if(sort.equals("desc")) {
				// 검색어o, 답변후, 카테고리x
				if(category.equals("")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
				// 검색어o, 답변후, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변후, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변후, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				// 검색어o, 답변후, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT COUNT(*)"
						+ "		FROM question q INNER JOIN question_comment qc"
						+ "		ON q.question_code = qc.question_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
				}
			}
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	// 주문문의
	public ArrayList<HashMap<String, Object>> selectQuestionListByAdmin(Connection conn, String category, String sort, String word, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 검색어 null이나 공백이 넘어오면 공백처리
		if(word == null || word.equals("")) {
			word = "";
		}
		// 정렬 null이나 공백이 넘어오면 공백처리
		if(sort == null || sort.equals("")) {
			sort = "";
		}
		
		// 분류 null이나 공백이 넘어오면 공백처리
		if(category == null || category.equals("")) {
			category = "";
		}
		// 쿼리문
		String sql = "";
		// 쿼리 객체 생성
		PreparedStatement stmt = null;
		
		// 검색어x
		if(word.equals("")) {
			// 검색어x, 정렬x q.question_code desc
			if(sort.equals("")) {
				// 검색어x, 정렬x, 카테고리x 
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색어x, 정렬x, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 정렬x, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 정렬x, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 정렬x, 카테고리 결제	
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
				
			// 검색어x, 답변전으로 정렬 q.createdate desc
			} else if(sort.equals("asc")) {
				// 검색어x, 답변전, 카테고리x
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색어x, 답변전, 카테고리 할인	
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변전, 카테고리 포인트	
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변전, 카테고리 구매	
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변전, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE qc.comment_memo IS NULL AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
			// 검색어x, 답변후로 정렬 qc.createdate desc
			} else if(sort.equals("desc")) {
				// 검색어x, 답변후, 카테고리x
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY qc.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색어x, 답변후, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY qc.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변후, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY qc.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변후, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY qc.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어x, 답변후, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY qc.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
			}
		// 검색어o
		} else {
			// 검색어o, 정렬x q.question_code desc
			if(sort.equals("")) {
				// 검색어o, 정렬x, 카테고리x
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어o, 정렬x, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 정렬x, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 정렬x, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 정렬x, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q LEFT JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.question_code DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색어o, 답변전 q.createdate desc
			} else if(sort.equals("asc")) {
				// 검색어o, 답변전, 카테고리x
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q left JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어o, 답변전, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q left JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변전, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q left JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변전, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q left JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변전, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q left JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=? AND qc.comment_memo IS NULL"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색어o, 답변후
			} else if(sort.equals("desc")) {
				// 검색어o, 답변후, 카테고리x
				if(category.equals("")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색어o, 답변후, 카테고리 할인
				} else if(category.equals("할인")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변후, 카테고리 포인트
				} else if(category.equals("포인트")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변후, 카테고리 구매
				} else if(category.equals("구매")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				// 검색어o, 답변후, 카테고리 결제
				} else if(category.equals("결제")) {
					sql = "SELECT"
						+ "		q.question_code questionCode, q.orders_code orderCode, q.category category"
						+ "		, q.question_memo questionMemo, q.createdate qCreatedate, qc.comment_code commentCode"
						+ "		, qc.comment_memo commentMemo, qc.createdate qcCreatedate, o.customer_id customerId"
						+ "		, o.order_state orderState, og.goods_code goodsCode, g.goods_name goodsName"
						+ "		FROM question q INNER JOIN question_comment qc ON q.question_code = qc.question_code"
						+ "		INNER JOIN orders o ON q.orders_code = o.order_code"
						+ "		INNER JOIN order_goods og ON q.orders_code = og.order_code"
						+ "		INNER JOIN goods g ON og.goods_code = g.goods_code"
						+ "		WHERE q.orders_code LIKE ? AND q.category=?"
						+ "		GROUP BY q.question_code"
						+ "		ORDER BY q.createdate DESC LIMIT ?, ?";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, word);
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			}
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("questionCode", rs.getInt("questionCode"));
			m.put("orderCode", rs.getInt("orderCode"));
			m.put("category", rs.getString("category"));
			m.put("questionMemo", rs.getString("questionMemo"));
			m.put("qCreatedate", rs.getString("qCreatedate"));
			m.put("commentCode", rs.getInt("commentCode"));
			m.put("commentMemo", rs.getString("commentMemo"));
			m.put("qcCreatedate", rs.getString("qcCreatedate"));
			m.put("customerId", rs.getString("customerId"));
			m.put("orderState", rs.getString("orderState"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("goodsName", rs.getString("goodsName"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
	// 상품문의 답변삭제
	public int deleteGoodsQuestionCommentByAdmin(Connection conn, int goodsCommentCode) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "DELETE FROM goods_question_comment WHERE goods_comment_code=?";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsCommentCode);
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 상품문의 답변달기
	public int insertGoodsQuestionCommentByAdmin(Connection conn, GoodsQuestionComment goodsQuestionComment) throws Exception {
		// 객체 초기화
		int row = 0;
		// 쿼리문 작성
		String sql = "INSERT INTO goods_question_comment(goods_question_code, goods_comment_memo, createdate) VALUES(?, ?, NOW())";
		// 쿼리 객체 생성
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 쿼리문 ?값 지정
		stmt.setInt(1, goodsQuestionComment.getGoodsQuestionCode());
		stmt.setString(2, goodsQuestionComment.getGoodsCommentMemo());
		// 쿼리 실행
		row = stmt.executeUpdate();
		
		stmt.close();
		return row;
	}
	// 상품문의 목록수
	public int selectGoodsQuestionCountByAdmin(Connection conn, String word, String category, String search, String sort) throws Exception {
		// 객체 초기화
		int row = 0;
		// 검색어
		if(word == null || word.equals("")) {
			word = "";
		}
		// 검색어분류
		if(search == null || search.equals("")) {
			search = "";
		}
		// 분류
		if(category == null || category.equals("")) {
			category = "";
		}
		// 정렬
		if(sort == null || sort.equals("")) {
			sort = "";
		}
		String sql ="";
		PreparedStatement stmt = null;
		// 검색 x
		if(search.equals("")) {
			// 검색x, 정렬 x
			if(sort.equals("")) {
				// 검색x, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*) FROM goods_question";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
				// 검색x, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*) FROM goods_question WHERE category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
				}
			// 검색x, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색x, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
				// 검색x, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.category=? AND gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
				}
			// 검색x 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색x, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
				// 검색x, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
				}
			}
		// 검색 : 상품명
		} else if(search.equals("상품명")) {
			// 검색 : 상품명, 정렬 x
			if(sort.equals("")) {
				// 검색 : 상품명, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*) FROM goods_question gq INNER JOIN goods g ON gq.goods_code = g.goods_code WHERE g.goods_name LIKE ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 상품명, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*) "
						+ "		FROM goods_question gq INNER JOIN goods g "
						+ "		ON gq.goods_code = g.goods_code "
						+ "		WHERE g.goods_name LIKE ? AND gq.category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			// 검색 : 상품명, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색 : 상품명, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 상품명, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gq.category=? AND gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			// 검색 : 상품명 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색 : 상품명, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 상품명, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gq.category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			}
		// 검색 : 고객ID
		} else if(search.equals("고객ID")) {
			// 검색 : 고객ID, 정렬 x
			if(sort.equals("")) {
				// 검색 : 고객ID, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question WHERE customer_id LIKE ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 고객ID, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question WHERE customer_id LIKE ? AND category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			// 검색 : 고객ID, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색 : 고객ID, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 고객ID, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gq.category=? AND gqc.goods_comment_memo IS NULL";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			// 검색 : 고객ID 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색 : 고객ID, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
				// 검색 : 고객ID, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT COUNT(*)"
						+ "		FROM goods_question gq INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gq.category=?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
				}
			}
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			row = rs.getInt("COUNT(*)");
		}
		stmt.close();
		rs.close();
		return row;
	}
	// 상품문의
	public ArrayList<HashMap<String, Object>> selectGoodsQuestionListByAdmin(Connection conn, String word, String category, String search, String sort, int beginRow, int rowPerPage) throws Exception {
		// 객체 초기화
		ArrayList<HashMap<String, Object>> list = null;
		// 검색어
		if(word == null || word.equals("")) {
			word = "";
		}
		// 검색어분류
		if(search == null || search.equals("")) {
			search = "";
		}
		// 분류
		if(category == null || category.equals("")) {
			category = "";
		}
		// 정렬
		if(sort == null || sort.equals("")) {
			sort = "";
		}

		String sql ="";
		PreparedStatement stmt = null;
		// 검색 x
		if(search.equals("")) {
			// 검색x, 정렬 x
			if(sort.equals("")) {
				// 검색x, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색x, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.category=?"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
			// 검색x, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색x, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색x, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.category=? AND gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
			// 검색x 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색x, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		ORDER BY gqc.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setInt(1, beginRow);
					stmt.setInt(2, rowPerPage);
				// 검색x, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.category=?"
						+ "		ORDER BY gqc.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, category);
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				}
			}
		// 검색 : 상품명
		} else if(search.equals("상품명")) {
			// 검색 : 상품명, 정렬 x
			if(sort.equals("")) {
				// 검색 : 상품명, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ?"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 상품명, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gq.category=?"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색 : 상품명, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색 : 상품명, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 상품명, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gq.category=? AND gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색 : 상품명 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색 : 상품명, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ?"
						+ "		ORDER BY gqc.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 상품명, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE g.goods_name LIKE ? AND gq.category=?"
						+ "		ORDER BY gqc.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			}
		// 검색 : 고객ID
		} else if(search.equals("고객ID")) {
			// 검색 : 고객ID, 정렬 x
			if(sort.equals("")) {
				// 검색 : 고객ID, 정렬x, 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ?"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 고객ID, 정렬x, 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gq.category=?"
						+ "		ORDER BY gq.goods_question_code DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색 : 고객ID, 정렬(답변전)
			} else if(sort.equals("asc")) {
				// 검색 : 고객ID, 정렬(답변전), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 고객ID, 정렬(답변전), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		LEFT JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gq.category=? AND gqc.goods_comment_memo IS NULL"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			// 검색 : 고객ID 정렬(답변후)
			} else if(sort.equals("desc")) {
				// 검색 : 고객ID, 정렬(답변후), 분류x
				if(category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ?"
						+ "		ORDER BY gqc.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setInt(2, beginRow);
					stmt.setInt(3, rowPerPage);
				// 검색 : 고객ID, 정렬(답변후), 분류o
				} else if(!category.equals("")) {
					// 쿼리문 작성
					sql = "SELECT"
						+ "		gq.goods_question_code goodsQuestionCode"
						+ "		, gq.goods_code goodsCode"
						+ "		, gq.customer_id customerId"
						+ "		, gq.category category"
						+ "		, gq.goods_question_memo goodsQuestionMemo"
						+ "		, gq.createdate gqCreatedate"
						+ "		, g.goods_name goodsName"
						+ "		, gqc.goods_comment_code goodsCommentCode"
						+ "		, gqc.goods_comment_memo goodsCommentMemo"
						+ "		, gqc.createdate gqcCreatedate"
						+ "		FROM goods_question gq INNER JOIN goods g"
						+ "		ON gq.goods_code = g.goods_code"
						+ "		INNER JOIN goods_question_comment gqc"
						+ "		ON gq.goods_question_code = gqc.goods_question_code"
						+ "		WHERE gq.customer_id LIKE ? AND gq.category=?"
						+ "		ORDER BY gq.createdate DESC LIMIT ?, ?";
					// 쿼리 객체 생성
					stmt = conn.prepareStatement(sql);
					// 쿼리문 ?값 지정
					stmt.setString(1, "%"+word+"%");
					stmt.setString(2, category);
					stmt.setInt(3, beginRow);
					stmt.setInt(4, rowPerPage);
				}
			}
		}
		// 쿼리 실행
		ResultSet rs = stmt.executeQuery();
		list = new ArrayList<HashMap<String, Object>>();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsQuestionCode", rs.getInt("goodsQuestionCode"));
			m.put("goodsCode", rs.getInt("goodsCode"));
			m.put("customerId", rs.getString("customerId"));
			m.put("category", rs.getString("category"));
			m.put("goodsQuestionMemo", rs.getString("goodsQuestionMemo"));
			m.put("gqCreatedate", rs.getString("gqCreatedate"));
			m.put("goodsName", rs.getString("goodsName"));
			m.put("goodsCommentCode", rs.getInt("goodsCommentCode"));
			m.put("goodsCommentMemo", rs.getString("goodsCommentMemo"));
			m.put("gqcCreatedate", rs.getString("gqcCreatedate"));
			list.add(m);
		}
		stmt.close();
		rs.close();
		return list;
	}
}
