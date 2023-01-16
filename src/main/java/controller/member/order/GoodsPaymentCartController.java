package controller.member.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import service.CustomerService;
import service.OrderService;
import service.PointHistoryService;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/member/goodsPayMentCart")
public class GoodsPaymentCartController extends HttpServlet {
	private CartService cartService;
	private CustomerService customerService;
	private OrderService orderService;
	private PointHistoryService pointHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(id);
		
		// Model
		// 카트에서 넘어오는 굿즈 리스트
		this.cartService = new CartService();
		ArrayList<HashMap<String, Object>> list = cartService.getCartList(id);
		
		// 현재 로그인회원의 주문시 필요한 정보
		this.customerService = new CustomerService();
		HashMap<String, Object> customerOne = customerService.getSelectCustomerOne(customer);
		
		// 현재 잔여 포인트
		this.pointHistoryService = new PointHistoryService();
		int usePoint = pointHistoryService.getSelectPoint(id, "사용");
		int savePoint = pointHistoryService.getSelectPoint(id, "적립");
		
		// 값 저장해서 view로 넘김
		request.setAttribute("list", list);
		request.setAttribute("customerOne", customerOne);
		request.setAttribute("usePoint", usePoint);
		request.setAttribute("savePoint", savePoint);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/order/goodsPaymentCartList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		
		// 값 받아오기
		int point = Integer.parseInt(request.getParameter("point"));
		
		// 바인딩
		Orders orders = new Orders();
		orders.setCustomerId(id);
		orders.setAddressCode(Integer.parseInt(request.getParameter("addressCode")));
		orders.setOrderMemo(request.getParameter("orderMemo"));
		orders.setOrderPrice(Integer.parseInt(request.getParameter("orderPrice")) - point);
		
		// Model
		// 카트에서 넘어오는 굿즈 리스트
		this.cartService = new CartService();
		ArrayList<HashMap<String, Object>> list = cartService.getCartList(id);
		
		// 주문
		this.orderService = new OrderService();
		int orderCode = orderService.getInsertOrderByCart(orders, list);
		if(orderCode != 0) {
			System.out.println("주문 성공"); // 주문성공하면 포인트 차감
			if(point != 0 || request.getParameter("point") != null) {
				PointHistory pointHistory = new PointHistory();
				pointHistory.setPoint(point);
				pointHistory.setPointKind("사용");
				pointHistory.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
				pointHistory.setCustomerId(id);
				pointHistory.setOrderCode(orderCode);
				
				// 포인트 사용
				pointHistoryService.getInsertPoint(pointHistory);
				System.out.println("포인트 사용 성공");
			} else {
				System.out.println("포인트 사용 실패");
			}
		} else {
			System.out.println("주문 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/goodsCompleted?orderCode=" + orderCode);
	}

}
