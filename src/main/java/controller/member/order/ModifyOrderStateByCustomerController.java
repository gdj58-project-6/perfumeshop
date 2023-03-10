package controller.member.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrderService;
import service.PointHistoryService;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/member/modifyOrderState")
public class ModifyOrderStateByCustomerController extends HttpServlet {
	private OrderService orderService;
	private PointHistoryService pointHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String orderState = "구매확정";
		
		// 바인딩
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);

		// Model
		this.orderService = new OrderService();
		int row = orderService.getUpdateOrderState(orders);
		
		if(row == 1) {
			System.out.println("구매확정 성공");
		} else {
			System.out.println("구매확정 실패");
		}
		response.sendRedirect(request.getContextPath() + "/member/orderList");
	}
}
