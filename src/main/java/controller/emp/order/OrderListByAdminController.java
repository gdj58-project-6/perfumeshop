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
		/*if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}*/
		
		// 검색
		String stateSearch = "";
		String customerId = "";
		String sort = "DESC";
		
		if(request.getParameter("stateSearch") != null && !request.getParameter("stateSearch").equals("")) {
			stateSearch = request.getParameter("stateSearch");
		}
		
		if(request.getParameter("customerId") != null && !request.getParameter("customerId").equals("")) {
			customerId = request.getParameter("customerId");
		}
		
		if(request.getParameter("sort") != null && request.getParameter("sort").equals("ASC")) {
			sort = "ASC";
		}
		
		System.out.println(stateSearch);
		
		// 로그인이되어있으면
		// Model
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> list = orderService.getSelectAllOrderList(stateSearch, customerId, sort);
		
		session.setAttribute("state", stateSearch);
		session.setAttribute("sort", sort);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/emp/order/orderListByAdmin.jsp").forward(request, response);
	}
}
