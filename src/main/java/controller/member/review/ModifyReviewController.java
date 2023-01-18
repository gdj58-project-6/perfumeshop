package controller.member.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReviewService;
import vo.Review;

@WebServlet("/member/modifyReview")
public class ModifyReviewController extends HttpServlet {
	private ReviewService reviewService;
	// 동적으로 수정
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정할 리뷰의 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String reviewMemo = request.getParameter("reviewMemo");
		
		// 바인딩
		Review review = new Review();
		review.setOrderCode(orderCode);
		review.setGoodsCode(goodsCode);
		review.setReviewMemo(reviewMemo);
		
		// Model
		this.reviewService = new ReviewService();
		int row = reviewService.getUpdateReview(review);
		
		if(row == 1) {
			System.out.println("리뷰 수정 성공");
		} else {
			System.out.println("리뷰 수정 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/reviewList");
	}

}
