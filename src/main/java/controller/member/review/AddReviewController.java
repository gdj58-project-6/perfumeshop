package controller.member.review;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;
import service.ReviewService;
import vo.OrderGoods;
import vo.PointHistory;
import vo.Review;

@WebServlet("/member/addReview")
public class AddReviewController extends HttpServlet {
	private OrderService orderService;
	private ReviewService reviewService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰를 남길 상품을 보여줌
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		// 바인딩
		OrderGoods orderGoods = new OrderGoods();
		orderGoods.setOrderCode(orderCode);
		orderGoods.setGoodsCode(goodsCode);
		
		//Model
		this.orderService = new OrderService();
		HashMap<String, Object> orderOne = orderService.getSelectOrderGoodsOne(orderGoods);
		
		request.setAttribute("orderOne", orderOne);
		
		request.getRequestDispatcher("/WEB-INF/view/member/review/addReview.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 리뷰를 남길 주문코드, 상품코드, 리뷰내용 등록 후 포인트 적립
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int point = Integer.parseInt(request.getParameter("point"));
		String reviewMemo = request.getParameter("reviewMemo");
		
		// 바인딩
		Review review = new Review();
		review.setOrderCode(orderCode);
		review.setGoodsCode(goodsCode);
		review.setReviewMemo(reviewMemo);
		
		PointHistory pointHistory = new PointHistory();
		pointHistory.setGoodsCode(goodsCode);
		pointHistory.setPoint(point);
		
		// Model
		this.reviewService = new ReviewService();
		int row = reviewService.getInsertReview(review, pointHistory);
		
		if(row == 1) {
			System.out.println("리뷰 등록 후 포인트 적립 성공");
		} else {
			System.out.println("리뷰 등록 후 포인트 적립 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/orderOne?orderCode=" + orderCode);
	}

}
