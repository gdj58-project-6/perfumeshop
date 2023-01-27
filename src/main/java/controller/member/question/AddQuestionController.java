package controller.member.question;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Customer;
import vo.Emp;
import vo.Question;

@WebServlet("/member/addQuestion")
public class AddQuestionController extends HttpServlet {
	private QuestionService questionService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderCode = 0;
		if(request.getParameter("orderCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} else {
			orderCode = Integer.parseInt(request.getParameter("orderCode"));
		}
		// System.out.println(orderCode);
		HttpSession session = request.getSession();
		// 비로그인이거나 직원이면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Emp) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		request.setAttribute("orderCode", orderCode);
		request.setAttribute("msg", request.getParameter("msg"));
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/addQuestion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginMember");
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String customerId = loginCustomer.getCustomerId();
		String category = request.getParameter("category");
		String questionMemo = request.getParameter("questionMemo");
		// System.out.println(orderCode);
		// System.out.println(category + "<-- category 디버깅");
		// System.out.println(questionMemo + "<-- questionMemo 디버깅");
		if(request.getParameter("category").equals("") || request.getParameter("questionMemo") == null
				|| request.getParameter("questionMemo").equals("")) {
				String msg = URLEncoder.encode("카테고리선택or문의내용을 적어주세요", "utf-8");
				response.sendRedirect(request.getContextPath() + "/member/addQuestion?msg="+msg+"&orderCode="+orderCode);
				return;
		}
		// 메서드 vo
		Question q = new Question();
		q.setOrderCode(orderCode);
		q.setCustomerId(customerId);
		q.setCategory(category);
		q.setQuestionMemo(questionMemo);
		
		
		// 메서드 호출
		this.questionService = new QuestionService();
		int addQuestion = questionService.addQuestion(q);
		
		response.sendRedirect(request.getContextPath() + "/member/question");
	}

}
