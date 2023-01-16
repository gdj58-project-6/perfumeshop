package controller.emp.order;

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
import vo.Emp;

@WebServlet("/admin/orderList")
public class OrderListByAdminController extends HttpServlet {
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)(session.getAttribute("loginMember"));
		
		// 로그인이 안되어있으면
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 검색
		String orderState = null;
		String customerId = null;
		String createdate = request.getParameter("createdate");
		
		if(request.getParameter("orderState") == null || request.getParameter("orderState").equals("")
			|| request.getParameter("customerId") == null || request.getParameter("customerId").equals("")) {
			orderState = "";
			customerId = "";
		}
		
		orderState = request.getParameter("orderState");
		customerId = request.getParameter("customerId");
		
		// 로그인이되어있으면
		// Model
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> list = orderService.getSelectAllOrderList(orderState, customerId, createdate);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/emp/order/orderListByAdmin.jsp").forward(request, response);
	}
}
