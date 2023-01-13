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

@WebServlet("/admin/comment")
public class CommentController extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인이거나 고객은 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Customer) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		// 상품문의 현재 페이지
		int goodsCurrentPage = 1;
		if(request.getParameter("goodsCurrentPage") != null) {
			goodsCurrentPage = Integer.parseInt(request.getParameter("goodsCurrentPage"));
		}
		// 상품문의 표시할 목록의수
		int goodsRowPerPage = 5;
		if(request.getParameter("goodsRowPerPage") != null) {
			goodsRowPerPage = Integer.parseInt(request.getParameter("goodsRowPerPage"));
		}
		
		String word = null;
		if(request.getParameter("word") != null) {
			word = request.getParameter("word");
		}
		
		// 메서드 호출
		this.commentService = new CommentService();
		ArrayList<HashMap<String, Object>> goodsList = commentService.getGoodsQuestionListByAdmin(goodsCurrentPage, goodsRowPerPage);
		int goodsListCount = commentService.getGoodsQuestionCountByAdmin();
		int goodsLastPage = 0;
		if(goodsListCount % goodsRowPerPage == 0) {
			goodsLastPage = goodsListCount / goodsRowPerPage;
		} else if(goodsListCount % goodsRowPerPage != 0) {
			goodsLastPage = (goodsListCount / goodsRowPerPage) + 1;
		}
		request.setAttribute("goodsCurrentPage", goodsCurrentPage);
		request.setAttribute("goodsRowPerPage", goodsRowPerPage);
		request.setAttribute("goodsLastPage", goodsLastPage);
		request.setAttribute("goodsList", goodsList);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/comment/commentList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
