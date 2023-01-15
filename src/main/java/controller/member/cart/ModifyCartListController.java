package controller.member.cart;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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

		String[] goodsCode1 = request.getParameterValues("goodsCode");
		int[] goodsCode = new int[goodsCode1.length];
		for(int i = 0; i < goodsCode1.length; i++) {
			goodsCode[i] = Integer.parseInt(goodsCode1[i]);
		}
		
		String[] cartQuantity1 = request.getParameterValues("orderQuantity");
		int[] cartQuantity = new int[cartQuantity1.length];
		for(int i = 0; i < cartQuantity1.length; i++) {
			cartQuantity[i] = Integer.parseInt(cartQuantity1[i]);
		}
		
		ArrayList<Cart> list = new ArrayList<>();
		for(int i = 0; i < goodsCode.length; i++) {
			this.cart = new Cart();
			cart.setCustomerId(customerId);
			cart.setGoodsCode(goodsCode[i]);
			cart.setCartQuantity(cartQuantity[i]);
			list.add(cart);
		}
		
		this.cartService = new CartService();
		int row  = cartService.modifyCartList(list);
		
		// 디버깅
		if(row != 1) {
			System.out.println("장바구니 수정 실패");
		} else {
			System.out.println("장바구니 수정 성공");
		}
		
		response.sendRedirect(request.getContextPath()+"/member/cart");
	}

}
