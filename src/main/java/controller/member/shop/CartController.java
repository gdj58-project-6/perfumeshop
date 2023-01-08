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

import service.CartService;

@WebServlet("/member/cart")
public class CartController extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 담긴 상품, 총 가격, 선택삭제, 옵션 수정, 총 가격으로 동적으로 바뀌게
		this.cartService = new CartService();
		// 로그인 하지않아도 장바구니에 들어올 수는 있음
		
		ArrayList<HashMap<String, Object>> list = cartService.getCartList();
		System.out.println(list);
		request.setAttribute("list", list);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/cartList.jsp");
		rd.forward(request, response);
	}

}
