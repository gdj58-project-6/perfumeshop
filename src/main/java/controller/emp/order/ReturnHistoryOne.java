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
import service.ReturnHistoryService;
import vo.Emp;

@WebServlet("/admin/returnHistoryOne")
public class ReturnHistoryOne extends HttpServlet {
	private ReturnHistoryService returnHistoryService;
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)(session.getAttribute("loginMember"));
		
		// 로그인이 안되어있으면
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		if(request.getParameter("orderCode") == null || request.getParameter("orderCode").equals("")) {
			response.sendRedirect(request.getContextPath() + "/admin/returnHistoryList");
		}
		
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> goodsList = orderService.getSelectOrderByGoodsList(orderCode);
		
		this.returnHistoryService = new ReturnHistoryService();
		HashMap<String, Object> returnOne = returnHistoryService.selectReturnHistoryOne(orderCode);
		
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("returnOne", returnOne);
		request.getRequestDispatcher("/WEB-INF/view/emp/order/returnHistoryOne.jsp").forward(request, response);
	}

}
