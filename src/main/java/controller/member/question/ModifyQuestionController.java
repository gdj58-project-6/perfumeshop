package controller.member.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;
import vo.Emp;
import vo.Question;

@WebServlet("/member/modifyQuestion")
public class ModifyQuestionController extends HttpServlet {
	private QuestionService questionService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인이거나 직원이면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Emp) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		// 수정폼에서 키게 되면 문의번호가 null이라 에러나는걸 홈으로 가게끔 하기위해
		int questionCode = 0;
		if(request.getParameter("questionCode") != null) {
			questionCode = Integer.parseInt(request.getParameter("questionCode"));
		} else if(request.getParameter("questionCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 메서드 호출
		this.questionService = new QuestionService();
		Question modifyQuestion = questionService.getQuestionListByModify(questionCode);
		request.setAttribute("modifyQuestion", modifyQuestion);
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/modifyQuestion.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		String questionMemo = request.getParameter("questionMemo");
		// System.out.println(questionCode);
		// System.out.println(questionMemo);
		
		// 메서드 호출시 매개값
		Question q = new Question();
		q.setQuestionCode(questionCode);
		q.setQuestionMemo(questionMemo);
		
		// 메서드 호출
		this.questionService = new QuestionService();
		int updateQuestion = questionService.modifyQuestion(q);
		
		response.sendRedirect(request.getContextPath() + "/member/question");
	}

}
