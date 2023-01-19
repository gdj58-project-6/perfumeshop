package controller.member.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReviewService;
import vo.Customer;
import vo.PointHistory;
import vo.Review;

@WebServlet("/member/removeRiview")
public class RemoveReviewController extends HttpServlet {
	private ReviewService reviewService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		// 삭제할 리뷰 내용
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int point = Integer.parseInt(request.getParameter("point"));
		String id = request.getParameter("id");

		// 바인딩
		Review review = new Review();
		review.setOrderCode(orderCode);
		review.setGoodsCode(goodsCode);
		
		PointHistory pointHistory = new PointHistory();
		pointHistory.setOrderCode(orderCode);
		pointHistory.setGoodsCode(goodsCode);
		pointHistory.setCustomerId(id);
		pointHistory.setPointKind("취소");
		pointHistory.setPoint(point);
		pointHistory.setMemo("리뷰 삭제");
		
		Customer customer = new Customer();
		customer.setPoint(point);
		customer.setCustomerId(id);
		
		// Model
		this.reviewService = new ReviewService();
		int row = reviewService.getDeleteReview(review, pointHistory, customer);
		
		if(row == 1) {
			System.out.println("리뷰 삭제 후 포인트 취소 성공");
		} else {
			System.out.println("리뷰 삭제 후 포인트 취소 실패");
		}
	}

}
