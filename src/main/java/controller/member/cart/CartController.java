package controller.member.cart;

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
import vo.Customer;
import vo.Emp;

@WebServlet("/member/cart")
public class CartController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 담긴 상품, 총 가격, 선택삭제, 옵션 수정, 총 가격으로 동적으로 바뀌게
		
		// 로그인 하지않아도 장바구니에 들어올 수는 있음
		
		Customer loginCustomer = null;
		Emp loginEmp = null;
		this.cartService = new CartService();
		ArrayList<HashMap<String, Object>> list = null;
		HttpSession session =  request.getSession();
		
		// customerId값 불러오기
		if(session.getAttribute("loginMember") instanceof Customer) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			list = cartService.getCartList(loginCustomer.getCustomerId());
			request.setAttribute("loginMember", loginCustomer);
		} else if(session.getAttribute("loginMember") instanceof Emp) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} 
				
		// System.out.println(list);
		request.setAttribute("list", list);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/cart/cartList.jsp");
		rd.forward(request, response);
	}

}