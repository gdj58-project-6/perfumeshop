package controller.member.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;


@WebServlet("/member/questionList")
public class QuestionListController extends HttpServlet {
	private QuestionService questionService;
	// 고객센터 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비로그인시 접근불가
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		// 메서드 호출
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = questionService.getGoodsQuestion(currentPage, rowPerPage);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/member/shop/questionList.jsp").forward(request, response);
	}
	// 문의글 수정, 삭제 정도?
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
