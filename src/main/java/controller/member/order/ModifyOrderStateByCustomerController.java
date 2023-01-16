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
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");
		String id = loginMember.getCustomerId();
		
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int point = Integer.parseInt(request.getParameter("point"));
		String orderState = "취소";
		
		// 바인딩
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);
		
		PointHistory pointHistory = new PointHistory();
		pointHistory.setOrderCode(orderCode);
		pointHistory.setGoodsCode(goodsCode);
		pointHistory.setCustomerId(id);
		pointHistory.setPoint(point);
		pointHistory.setPointKind(orderState);
		
		// Model
		this.orderService = new OrderService();
		int row = orderService.getUpdateOrderState(orders);
		
		if(row == 1) {
			if(point != 0 && !request.getParameter("point").equals("0") && request.getParameter("point") != null) {
				this.pointHistoryService = new PointHistoryService();
				int result = pointHistoryService.getInsertPoint(pointHistory);
				if(result == 1) {
					System.out.println("주문 취소 후 포인트 취소 성공");
				} else {
					System.out.println("주문 취소 후 포인트 취소 실패");
				}
			}
			System.out.println("주문 취소 성공");
		} else {
			System.out.println("주문 취소 실패");
		}
		response.sendRedirect(request.getContextPath() + "/member/orderList");
	}
}
