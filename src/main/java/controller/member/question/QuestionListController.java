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


@WebServlet("/member/question")
public class QuestionListController extends HttpServlet {
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
		int currentPage2 = 1;
		if(request.getParameter("currentPage2") != null) {
			currentPage2 = Integer.parseInt(request.getParameter("currentPage2"));
		}
		int rowPerPage = 5;
		
		// 메서드 호출
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = questionService.getGoodsQuestion(customerId, currentPage2, rowPerPage);
		ArrayList<HashMap<String, Object>> questionList = questionService.getQuestionList(customerId, currentPage2, rowPerPage);
		int cnt = questionService.getQuestionCountByGoodsQuestionModify();
		int count = questionService.getCountByQuestionList();
		int lastPage = 0;
		int lastPage2 = 0;
		if(cnt % rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
		} else if (cnt % rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) +1;
		}
		if(count % rowPerPage == 0) {
			lastPage2 = count / rowPerPage;
		} else if(count % rowPerPage != 0) {
			lastPage2 = (count / rowPerPage) +1;
		}
		request.setAttribute("goodsQuestionList", list);
		request.setAttribute("questionList", questionList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("currentPage2", currentPage2);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("lastPage2", lastPage2);
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/questionList.jsp").forward(request, response);
	}
}
