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

import service.CustomerService;
import service.GoodsService;
import service.OrderService;
import service.PointHistoryService;
import vo.Customer;
import vo.Goods;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/member/goodsPayMent")
public class GoodsPaymentController extends HttpServlet {
	private GoodsService goodsService;
	private CustomerService customerService;
	private PointHistoryService pointHistoryService;
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		
		// 보여줄 상품 코드, 상품 개수
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int goodsQuantity = Integer.parseInt(request.getParameter("goodsQuantity"));
		System.out.println(goodsQuantity);
		
		// 바인딩
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		
		Customer customer = new Customer();
		customer.setCustomerId(id);
		
		// Model
		// goodsOne에서 넘어오는 굿즈 상세 정보
		this.goodsService = new GoodsService();
		HashMap<String, Object> goodsOne = goodsService.getGoodsOneByOrder(goods);
		
		// 로그인한 회원의 상세 정보
		this.customerService = new CustomerService();
		HashMap<String, Object> customerOne = customerService.getSelectCustomerOne(customer);
		
		// 현재 잔여 포인트
		this.pointHistoryService = new PointHistoryService();
		int usePoint = pointHistoryService.getSelectPoint(id, "사용");
		int savePoint = pointHistoryService.getSelectPoint(id, "적립");
		
		request.setAttribute("goodsQuantity", goodsQuantity);
		request.setAttribute("customerOne", customerOne);
		request.setAttribute("goodsOne", goodsOne);
		request.setAttribute("usePoint", usePoint);
		request.setAttribute("savePoint", savePoint);
		request.getRequestDispatcher("/WEB-INF/view/member/order/goodsPayment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		
		// 값 받아오기
		int point = Integer.parseInt(request.getParameter("point"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
		int goodsQuantity = Integer.parseInt(request.getParameter("goodsQuantity"));
		
		// 바인딩
		Orders orders = new Orders();
		orders.setCustomerId(id);
		orders.setAddressCode(Integer.parseInt(request.getParameter("addressCode")));
		orders.setOrderMemo(request.getParameter("orderMemo"));
		orders.setOrderPrice(Integer.parseInt(request.getParameter("orderPrice")) - point);
		
		// 바인딩
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		
		// 구매할 굿즈 리스트에 저장
		this.goodsService = new GoodsService();
		HashMap<String, Object> goodsOne = new HashMap<String, Object>();
		goodsOne.put("goodsCode", goodsCode);
		goodsOne.put("goodsPrice", goodsPrice);
		goodsOne.put("goodsQuantity", goodsQuantity);
		
		// 주문
		this.orderService = new OrderService();
		int orderCode = orderService.getInsertOrder(orders, goodsOne);
		if(orderCode != 0) {
			System.out.println("주문 성공"); // 주문성공하면 포인트 차감
			if(point != 0 && !request.getParameter("point").equals("0") && request.getParameter("point") != null) {
				PointHistory pointHistory = new PointHistory();
				pointHistory.setPoint(point);
				pointHistory.setPointKind("사용");
				pointHistory.setGoodsCode(Integer.parseInt(request.getParameter("goodsCode")));
				pointHistory.setCustomerId(id);
				pointHistory.setOrderCode(orderCode);
				
				// 포인트 사용
				int row = pointHistoryService.getInsertPoint(pointHistory);
				if(row == 0) {
					System.out.println("포인트 사용 실패");
				} else {
					System.out.println("포인트 사용 성공");
				}
			}
		} else {
			System.out.println("주문 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/goodsCompleted?orderCode=" + orderCode);
	}

}
