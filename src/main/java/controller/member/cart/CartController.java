package controller.member.cart;

import java.io.IOException;
import java.io.PrintWriter;
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
import vo.Cart;
import vo.Customer;
import vo.Emp;

@WebServlet("/member/cart")
public class CartController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 담긴 상품, 총 가격, 선택삭제, 옵션 수정, 총 가격으로 동적으로 바뀌게
		
		// 로그인 하지않아도 장바구니에 상품을 담을 수 있음

		// 컨트롤러에서 alert 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();

		Customer loginCustomer = null;
		Emp loginEmp = null;
		HttpSession session =  request.getSession();
		if(session.getAttribute("loginMember") instanceof Customer) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			session.setAttribute("loginCustomer", loginCustomer);
		} else if(session.getAttribute("loginMember") instanceof Emp) {
			writer.println("<script>alert('직원은 접근할 수 없습니다');history.go(-1);</script>"); 
			writer.close();
		}
		
		if(loginCustomer != null) {
			// 로그인 한 상태
			System.out.println("로그인 O");
			this.cartService = new CartService();
			String customerId = loginCustomer.getCustomerId();
			ArrayList<HashMap<String, Object>> list = cartService.getCartList(customerId);
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/cart/cartList.jsp");
			rd.forward(request, response);
			return;
		} else { // 비로그인 
			System.out.println("로그인 X");
			ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)session.getAttribute("cart");
			// System.out.println(list);
			request.getSession().setAttribute("list", list);
			// View 연결
		}
	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/cart/cartList.jsp");
		rd.forward(request, response);
	}

}