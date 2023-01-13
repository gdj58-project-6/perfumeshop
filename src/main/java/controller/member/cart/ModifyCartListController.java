package controller.member.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartService;
import vo.Cart;


@WebServlet("/member/ModifyCartList")
public class ModifyCartListController extends HttpServlet {
	private CartService cartService;
	private Cart cart;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerId = request.getParameter("customerId");
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int cartQuantity = Integer.parseInt(request.getParameter("orderQuantity"));
		System.out.println(customerId);
		System.out.println(goodsCode);
		System.out.println(cartQuantity);
		
		this.cart = new Cart();
		cart.setCustomerId(customerId);
		cart.setGoodsCode(goodsCode);
		cart.setCartQuantity(cartQuantity);
		System.out.println(cart);
		
		
		this.cartService = new CartService();
		int row = cartService.modifyCartList(cart);
		
		if(row != 1) {
			System.out.println("장바구니 수정 실패");
		} else {
			System.out.println("장바구니 수정 성공");
		}
		
		response.sendRedirect(request.getContextPath()+"/member/cart");
	}

}
