package controller.member.shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/cart")
public class CartController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 담긴 상품, 총 가격, 선택삭제, 옵션 수정, 총 가격으로 동적으로 바뀌게
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/cartList.jsp");
		rd.forward(request, response);
	}

}
