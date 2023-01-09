package controller.member.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import service.GoodsService;
import service.QuestionService;
import vo.Cart;
import vo.Customer;

@WebServlet("/member/goodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;
	private CartService cartService;
	private QuestionService questionService;
	private Cart cart;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 상세보기 정보, 리뷰, 문의글, 장바구니, 바로구매, 옵션수정, 가격 동적으로 수정 문의 답변도 model
		request.setCharacterEncoding("utf-8"); // 인코딩
		// 로그인 비로그인 상관없이 볼 수 있음
		
		
		// customerId값 불러오기
		HttpSession session =  request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");
		request.setAttribute("loginMember", loginMember);
		// System.out.println(loginMember);
		// 상품코드 없이는 상품상세보기칸으로 못감
		int goodsCode = 0;
		if(request.getParameter("goodsCode") == null) {
			response.sendRedirect(request.getContextPath()+"/member/goodsList");
			return;
		} else if(request.getParameter("goodsCode") != null) {
			goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
			// System.out.println(goodsCode);
		}
		
		// 페이징 마지막 페이지는 아직 미구현
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		
		// System.out.println(goodsCode);
		this.goodsService = new GoodsService();
		this.questionService = new QuestionService();
		ArrayList<HashMap<String, Object>> list = goodsService.goodsOne(goodsCode);
		ArrayList<HashMap<String, Object>> questionList = questionService.getGoodsQuestionList(currentPage, rowPerPage);
		// System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("questionList", questionList);
		request.setAttribute("goodsCode", goodsCode);
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/goodsOne.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 담기 버튼 누르면 장바구니로 내역 이동
		request.setCharacterEncoding("utf-8"); // 인코딩
		
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = request.getParameter("customerId");
		int cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		
		System.out.println(goodsCode);
		System.out.println(customerId);
		System.out.println(cartQuantity);
		
		this.cart = new Cart();
		cart.setGoodsCode(goodsCode);
		cart.setCustomerId(customerId);
		cart.setCartQuantity(cartQuantity);
		
		this.cartService = new CartService();
		int row = cartService.addCart(cart);
		
		// 디버깅
		if(row != 1) {
			System.out.println("장바구니 담기 실패");
		} else {
			System.out.println("장바구니 담기 성공");
		}
		response.sendRedirect(request.getContextPath()+"/member/goodsOne?goodsCode="+cart.getGoodsCode());
	}
}