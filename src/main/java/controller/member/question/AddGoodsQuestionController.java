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
import vo.GoodsQuestion;

@WebServlet("/member/addGoodsQuestion")
public class AddGoodsQuestionController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsCode = 0;
		if(request.getParameter("goodsCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} else {
			goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		}
		// System.out.println(orderCode);
		HttpSession session = request.getSession();
		// 비로그인이거나 직원이면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Emp) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		request.setAttribute("goodsCode", goodsCode);
		request.setAttribute("msg", request.getParameter("msg"));
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/addGoodsQuestion.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginMember");
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = loginCustomer.getCustomerId();
		String category = request.getParameter("category");
		String goodsQuestionMemo = request.getParameter("goodsQuestionMemo");
		// System.out.println(goodsCode);
		// System.out.println(customerId);
		// System.out.println(category);
		// System.out.println(goodsQuestionMemo);
		if(request.getParameter("category").equals("") || request.getParameter("goodsQuestionMemo") == null
			|| request.getParameter("goodsQuestionMemo").equals("")) {
			String msg = URLEncoder.encode("카테고리선택or문의내용을 적어주세요", "utf-8");
			response.sendRedirect(request.getContextPath() + "/member/addGoodsQuestion?msg="+msg+"&goodsCode="+goodsCode);
			return;
		}
		
			
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
			System.out.println("문의완료");
			response.sendRedirect(request.getContextPath()+"/member/goodsOne?goodsCode="+goodsCode);
		}
	}
}
