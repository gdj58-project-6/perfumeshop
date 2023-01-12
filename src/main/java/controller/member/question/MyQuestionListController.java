package controller.member.question;

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
import vo.Customer;


@WebServlet("/member/myQuestionList")
public class MyQuestionListController extends HttpServlet {
	private QuestionService questionService;
	// 고객센터 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		Customer loginCustomer = null;
		String customerId = null;
		if(session.getAttribute("loginMember") instanceof Customer) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			customerId = loginCustomer.getCustomerId();
			session.setAttribute("loginMember", loginCustomer);
		}
		// 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		// 메서드 호출
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = questionService.getGoodsQuestion(customerId, currentPage, rowPerPage);
		int cnt = questionService.getQuestionCountByGoodsQuestionModify();
		int lastPage = 0;
		if(cnt % rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
		} else if (cnt % rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) +1;
		}
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/myQuestionList.jsp").forward(request, response);
	}
	// 문의글 수정, 삭제 정도?
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
