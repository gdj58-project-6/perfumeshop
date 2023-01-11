package controller.member.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CartService;

@WebServlet("/RemoveCartList")
public class RemoveCartList extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니에서 상품 삭제할 수 있게
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println(goodsCode);
		
		this.cartService = new CartService();
		
		
		int row =  cartService.removeCartList(goodsCode);
		
		if(row != 1) {
			System.out.println("장바구니 삭제 실패");
		} else {
			System.out.println("장바구니 삭제 성공");
		}
		
		response.sendRedirect(request.getContextPath()+"/member/cart");
	}

}
