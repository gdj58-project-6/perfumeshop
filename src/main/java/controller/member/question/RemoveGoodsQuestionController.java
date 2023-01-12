package controller.member.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.QuestionService;


@WebServlet("/member/removeGoodsQuestion")
public class RemoveGoodsQuestionController extends HttpServlet {
	private QuestionService questionService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsQuestionCode = Integer.parseInt(request.getParameter("goodsQuestionCode"));
		// System.out.println(goodsQuestionCode);
		
		// 삭제 메서드 호출
		this.questionService = new QuestionService();
		int deleteGoodsQuestion = questionService.removeGoodsQuestion(goodsQuestionCode);
		
		if(deleteGoodsQuestion == 1) {
			System.out.println("삭제완료");
			response.sendRedirect(request.getContextPath() + "/member/myQuestionList");
		} 
	}
}
