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
import vo.GoodsQuestion;


@WebServlet("/member/modifyGoodsQuestion")
public class ModifyGoodsQuestionController extends HttpServlet {
	private QuestionService questionService;
	// 문의글 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인이거나 직원이면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Emp) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 수정폼에서 키게 되면 문의번호가 null이라 에러나는걸 홈으로 가게끔 하기위해
		int goodsQuestionCode = 0;
		if(request.getParameter("goodsQuestionCode") != null) {
			goodsQuestionCode = Integer.parseInt(request.getParameter("goodsQuestionCode"));
		} else if(request.getParameter("goodsQuestionCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		this.questionService = new QuestionService();
		GoodsQuestion goodsQuestionList = questionService.getSelectGoodsQuestionByModifyGoodsQuestion(goodsQuestionCode);
		
		request.setAttribute("goodsQuestion", goodsQuestionList);
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/modifyGoodsQuestion.jsp").forward(request, response);
	}

	// 문의글 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsQuestionCode = Integer.parseInt(request.getParameter("goodsQuestionCode"));
		String goodsQuestionMemo = request.getParameter("goodsQuestionMemo");
		// System.out.println(goodsQuestionCode);
		// System.out.println(goodsQuestionMemo);
		
		// 메서드 호출에 필요한 매개값
		GoodsQuestion g = new GoodsQuestion();
		g.setGoodsQuestionMemo(goodsQuestionMemo);
		g.setGoodsQuestionCode(goodsQuestionCode);
		// System.out.println(g.getGoodsQuestionCode());
		// System.out.println(g.getGoodsQuestionMemo());
		
		// 메서드 호출
		this.questionService = new QuestionService();
		int updateGoodsQuestion = questionService.modifyGoodsQuestion(g);
		
		if(updateGoodsQuestion == 1) {
			System.out.println("수정완료");
			response.sendRedirect(request.getContextPath() + "/member/question?goodsQuestionCode="+goodsQuestionCode);
		} 
	}

}
