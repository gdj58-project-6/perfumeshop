package controller.member.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrderService;
import service.ReturnHistoryService;
import vo.Customer;
import vo.Orders;
import vo.ReturnHistory;

@WebServlet("/member/addReturnByOrder")
public class AddReturnByOrderController extends HttpServlet {
	private OrderService orderService;
	private ReturnHistoryService returnHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		
		// 로그인이 안되어있으면
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
				
		// 취소할 오더코드
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		// 취소할 오더리스트 출력
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> orderList = orderService.getSelectOrderByGoodsList(orderCode);
		
		request.setAttribute("orderList", orderList);
		
		request.getRequestDispatcher("/WEB-INF/view/member/order/addReturnByOrder.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String id = request.getParameter("id");
		String memo = request.getParameter("memo");
		String orderState = "반품신청"; // 신청하면 무조건 반품신청으로 상태변경
		
		// 바인딩
		// 반품 내역 추가
		ReturnHistory returnHistory = new ReturnHistory();
		returnHistory.setOrderCode(orderCode);
		returnHistory.setCustomerId(id);
		returnHistory.setReturnMemo(memo);
		
		// 주문 상태 변경
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);
		
		// Model
		this.returnHistoryService = new ReturnHistoryService();
		int row = returnHistoryService.getInsertCancelByOrder(returnHistory, orders);
		
		if(row == 1) {
			System.out.println("환불 신청 성공");
			response.sendRedirect(request.getContextPath() + "/member/orderList");
		} else {
			System.out.println("환불 신청 실패");
			response.sendRedirect(request.getContextPath() + "/member/orderList");
		}
	}

}
