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

@WebServlet("/member/removeOrder")
public class RemoveOrderController extends HttpServlet {
	private OrderService orderService;
	private PointHistoryService pointHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");
		String id = loginMember.getCustomerId();
		
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int orderPrice = Integer.parseInt(request.getParameter("orderPrice"));
		int point = Integer.parseInt(request.getParameter("point"));
		String orderState = "취소";
		
		// 바인딩
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);
		orders.setOrderPrice(orderPrice);
		orders.setCustomerId(id);
		
		PointHistory pointHistory = new PointHistory();
		pointHistory.setOrderCode(orderCode);
		pointHistory.setGoodsCode(goodsCode);
		pointHistory.setCustomerId(id);
		pointHistory.setPointKind(orderState);
		pointHistory.setPoint(point);
		pointHistory.setMemo("주문 취소");
		
		Customer customer = new Customer();
		customer.setPoint(point);
		customer.setCustomerId(id);
		
		// Model
		this.orderService = new OrderService();
		int row = 0;
		if(point == 0) {
			row = orderService.getCancelOrderState(orders);
		} else {
			row = orderService.getCancelOrderStatePoint(orders, pointHistory, customer);
		}
		
		if(row == 0) {
			System.out.println("주문 취소 실패");
		} else {
			System.out.println("주문 취소 성공");
		}
		response.sendRedirect(request.getContextPath() + "/member/orderList");
	}
}
