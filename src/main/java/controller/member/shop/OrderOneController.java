package controller.member.shop;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;

@WebServlet("/member/orderOne")
public class OrderOneController extends HttpServlet {
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주문내역 상세보기
		// 로그인 유효성 검사
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		
		// Model
		this.orderService = new OrderService();
		HashMap<String, Object> orderOne = orderService.getSelectOrderOne(orderCode);
		
		request.setAttribute("orderOne", orderOne);
		request.getRequestDispatcher("/WEB-INF/view/member/shop/orderOne.jsp").forward(request, response);
	}

}
