package controller.member.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import vo.Customer;

@WebServlet("/member/RemoveAllCartList")
public class RemoveAllCartListController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 전부 비우기
		// loginMember id값 받기
		
		String customerId = request.getParameter("customerId");
		// System.out.println("customerId");
		
		this.cartService = new CartService();
		
		int row = cartService.removeAllCartList(customerId);

		
		response.sendRedirect(request.getContextPath()+"/member/cart");	
	}
}
