package controller.member.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReviewService;
import vo.Customer;

@WebServlet("/member/reviewList")
public class ReviewListController extends HttpServlet {
	private ReviewService reviewService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		
		// 로그인 안되어있으면
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 로그인되어있으면
		String id = loginMember.getCustomerId();
		
		// Model
		this.reviewService = new ReviewService();
		ArrayList<HashMap<String, Object>> list = reviewService.getSelectReviewList(id);
		
		request.setAttribute("reviewList", list);
		
		request.getRequestDispatcher("/WEB-INF/view/member/review/reviewList.jsp").forward(request, response);
	}
}
