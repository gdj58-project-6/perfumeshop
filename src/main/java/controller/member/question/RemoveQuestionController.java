package controller.member.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;

@WebServlet("/member/removeQuestion")
public class RemoveQuestionController extends HttpServlet {
	private QuestionService questionService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문의글 삭제
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		// System.out.println(questionCode);
		
		// 메서드 호출
		this.questionService = new QuestionService();
		int deleteQuestion = questionService.removeQuestion(questionCode);
		
		if(deleteQuestion == 1) {
			System.out.println("삭제완료");
			response.sendRedirect(request.getContextPath() + "/member/question");
		}
	}
}
