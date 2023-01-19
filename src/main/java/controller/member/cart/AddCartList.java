package controller.member.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import vo.Cart;
import vo.Customer;

@WebServlet("/member/AddCartList")
public class AddCartList extends HttpServlet {
	private CartService cartService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 비회원으로 장바구니 상품 담고 -> 로그인 시
		HttpSession session =  request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginMember");
		if(session.getAttribute("cart") != null) {
			String customerId = loginCustomer.getCustomerId();
			ArrayList<HashMap<String, Object>> cartList = new ArrayList<HashMap<String, Object>>();
			cartList = (ArrayList<HashMap<String, Object>>)session.getAttribute("cart");
			System.out.println(cartList.size() +"list.size()"); // size 확인
			
			for(int i=0; i<cartList.size(); i++) {
				String code = cartList.get(i).get("goodsCode").toString(); 
				String quantity = cartList.get(i).get("cartQuantity").toString(); 
				int goodsCode = Integer.parseInt(code); 
				int cartQuantity = Integer.parseInt(quantity);
				
				Cart cart = new Cart();
				cart.setCustomerId(customerId);
				cart.setGoodsCode(goodsCode);
				cart.setCartQuantity(cartQuantity);
				
				System.out.println(cart);
				
				cartService = new CartService();
				// 넘어온값이 이미 장바구니에 있는지 확인
				boolean result = cartService.checkCartList(cart);
				if(result == false) { // 데이터가 없으면
					// 장바구니 Add
					int row = cartService.addCart(cart);
					if(row != 1) {
						System.out.println("장바구니 추가 실패");
					} else {
						System.out.println("장바구니 추가 성공");
						
					}
				} else { // 이미 장바구니에있는 상품일 경우
					System.out.println("장바구니 담기 실패");
				}
			}
			// 비회원 장바구니값 세션 삭제
			session.removeAttribute("cartList");
			
			response.sendRedirect(request.getContextPath()+"/member/cart");
		}
	}
}
