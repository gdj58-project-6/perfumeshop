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
import service.GoodsService;
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
	private GoodsService goodsService;
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
			// 상품구매 할때마다 hit값 1씩증가
			this.goodsService = new GoodsService();
			int hitRow = goodsService.getModifyHitByCart(list);
			if(hitRow == 0) {
				System.out.println("장바구니 hit 증가실패");
			} else {
				System.out.println("장바구니 hit 증가성공");
			}
				
		
			if(point != 0 && !request.getParameter("point").equals("0") && request.getParameter("point") != null) {
				PointHistory pointHistory = new PointHistory();
				pointHistory.setOrderCode(orderCode);
				pointHistory.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
				pointHistory.setCustomerId(id);
				pointHistory.setPointKind("사용");
				pointHistory.setPoint(point);
				pointHistory.setMemo("상품 구매");
				
				Customer customer = new Customer();
				customer.setPoint(point);
				customer.setCustomerId(id);
				
				// 포인트 사용
				this.customerService = new CustomerService();
				int row = customerService.getUsePointByOrder(customer, pointHistory);
				if(row == 0) {
					System.out.println("포인트 사용 실패");
				} else {
					System.out.println("주문 성공 후 포인트 사용 성공");
				}
			}
		} else {
			System.out.println("주문 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/goodsCompleted?orderCode=" + orderCode);
	}

}
