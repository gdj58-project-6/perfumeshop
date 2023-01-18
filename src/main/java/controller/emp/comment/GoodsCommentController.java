package controller.emp.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import vo.Customer;

@WebServlet("/admin/goodsComment")
public class GoodsCommentController extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인이거나 고객은 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Customer) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 상품문의 현재 페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 상품문의 표시할 목록의수
		int rowPerPage = 5;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		// 검색어 분류
		String search = "";
		if(request.getParameter("search") != null) {
			search = request.getParameter("search");
			System.out.println(search + "<-- search 디버깅");
		}
		// 검색어
		String word = null;
		if(request.getParameter("word") != null) {
			word = request.getParameter("word");
			System.out.println(word + "<-- word 디버깅");
		}
		
		String category = "";
		if(request.getParameter("category") != "") {
			category = request.getParameter("category");
			System.out.println(category + "<-- category 디버깅");
		}
		
		String sort = "";
		if(request.getParameter("sort") != "") {
			sort = request.getParameter("sort");
			System.out.println(sort + "<-- sort 디버깅");
		}
		// 메서드 호출
		this.commentService = new CommentService();
		ArrayList<HashMap<String, Object>> goodsList = commentService.getGoodsQuestionListByAdmin(word, category, search, sort, currentPage, rowPerPage);
		int goodsListCount = commentService.getGoodsQuestionCountByAdmin(word, category, search, sort);
		int lastPage = 0;
		if(goodsListCount % rowPerPage == 0) {
			lastPage = goodsListCount / rowPerPage;
		} else if(goodsListCount % rowPerPage != 0) {
			lastPage = (goodsListCount / rowPerPage) + 1;
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("word", word);
		request.setAttribute("search", search);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/comment/goodsCommentList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
