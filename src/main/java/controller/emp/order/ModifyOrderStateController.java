package controller.emp.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;
import vo.Orders;

@WebServlet("/admin/modifyOrderState")
public class ModifyOrderStateController extends HttpServlet {
	private OrderService orderService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String orderState = request.getParameter("orderState");
		/*System.out.println(orderCode);
		System.out.println(orderState);*/
		
		// 바인딩
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);
		
		// Model
		this.orderService = new OrderService();
		int row = orderService.getUpdateOrderState(orders);
		
		if(row == 1) {
			System.out.println("주문 상태 변경 성공");
			response.sendRedirect(request.getContextPath() + "/admin/orderList");
		} else {
			System.out.println("주문 상태 변경 실패");
			response.sendRedirect(request.getContextPath() + "/admin/orderList");
		}
	}

}
