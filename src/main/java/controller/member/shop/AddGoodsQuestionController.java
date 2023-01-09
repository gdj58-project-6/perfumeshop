package controller.member.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.QuestionService;
import vo.GoodsQuestion;

/**
 * Servlet implementation class AddGoodsQuestionController
 */
@WebServlet("/member/addGoodsQuestion")
public class AddGoodsQuestionController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = request.getParameter("customerId");
		String category = request.getParameter("category");
		String goodsQuestionMemo = request.getParameter("goodsQuestionMemo");
		// System.out.println(goodsCode);
		// System.out.println(customerId);
		// System.out.println(category);
		// System.out.println(goodsQuestionMemo);
		
		// 메서드 호출시 사용할 매개값
		GoodsQuestion goodsQuestion  = new GoodsQuestion();
		goodsQuestion.setGoodsCode(goodsCode);
		goodsQuestion.setCustomerId(customerId);
		goodsQuestion.setCategory(category);
		goodsQuestion.setGoodsQuestionMemo(goodsQuestionMemo);
		// System.out.println(goodsQuestion.getGoodsCode());
		// System.out.println(goodsQuestion.getCategory());
		// System.out.println(goodsQuestion.getGoodsQuestionMemo());
		
		// 메서드 호출
		QuestionService questionService = new QuestionService();
		int row = questionService.insertGoodsQuestion(goodsQuestion);

		if(row == 1) {
			response.sendRedirect(request.getContextPath()+"/member/goodsOne?goodsCode="+goodsCode);
		}
		
		
	}

}
