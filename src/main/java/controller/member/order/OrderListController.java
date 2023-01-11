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
import vo.Customer;
import vo.Emp;

@WebServlet("/member/orderList")
public class OrderListController extends HttpServlet {
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		
		// 로그인안되어있으면
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(loginMember.getCustomerId());
		
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> list = orderService.getSelectOrderByCustomerLIst(customer);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/member/order/orderList.jsp").forward(request, response);
	}
}
