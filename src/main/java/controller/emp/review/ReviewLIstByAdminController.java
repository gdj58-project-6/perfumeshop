package controller.emp.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReviewService;

@WebServlet("/ReviewLIstByAdminController")
public class ReviewLIstByAdminController extends HttpServlet {
	private ReviewService reviewService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Model
		this.reviewService = new ReviewService();
		ArrayList<HashMap<String, Object>> list = reviewService.getSelectReviewListByAdmin();
		
		request.setAttribute("reviewList", list);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/review/reviewListByAdmin.jsp").forward(request, response);
	}
}
