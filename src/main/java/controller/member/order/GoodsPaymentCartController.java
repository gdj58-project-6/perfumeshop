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
import vo.Customer;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/member/goodsPayMentCart")
public class GoodsPaymentCartController extends HttpServlet {
	private CartService cartService;
	private CustomerService customerService;
	private OrderService orderService;
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
		
		// 값 저장해서 view로 넘김
		request.setAttribute("list", list);
		request.setAttribute("customerOne", customerOne);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/order/goodsPaymentCartList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		int point = Integer.parseInt(request.getParameter("point"));
		
		// 값 받아오기
		// 바인딩
		Orders orders = new Orders();
		orders.setCustomerId(id);
		orders.setAddressCode(Integer.parseInt(request.getParameter("addressCode")));
		orders.setOrderMemo(request.getParameter("orderMemo"));
		orders.setOrderPrice(Integer.parseInt(request.getParameter("orderPrice")));
		
		PointHistory pointHistory = new PointHistory();
		pointHistory.setPoint(point);
		pointHistory.setPointKind("사용");
		
		// Model
		// 카트에서 넘어오는 굿즈 리스트
		this.cartService = new CartService();
		ArrayList<HashMap<String, Object>> list = cartService.getCartList(id);
		
		// 주문
		this.orderService = new OrderService();
		int orderCode = orderService.getInsertOrder(orders, list, pointHistory);
		if(orderCode != 0) {
			System.out.println("주문 성공");
		} else {
			System.out.println("주문 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/goodsCompleted?orderCode=" + orderCode);
	}

}
