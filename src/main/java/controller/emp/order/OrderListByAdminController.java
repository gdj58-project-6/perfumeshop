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
		// 검색을 하지 않았을때 모두 풀력하기 위해서 공백 대입
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
		
		// 페이징
		// 현재페이지
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 한페이지당 보여줄 order 갯수
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		// 페이징을 위한 row count
		this.orderService = new OrderService();
		int cnt = orderService.getOrderListCount(stateSearch, customerId, sort);
		//  마지막페이지
		int lastPage = cnt / rowPerPage;
		if(lastPage % rowPerPage != 0) {
			lastPage++;
		}
		
		// 로그인이되어있으면
		// Model
		ArrayList<HashMap<String, Object>> list = orderService.getSelectAllOrderList(stateSearch, customerId, sort, beginRow, rowPerPage);
		
		// 페이징
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("lastPage", lastPage);
		
		// 검색
		session.setAttribute("state", stateSearch);
		session.setAttribute("sort", sort);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/emp/order/orderListByAdmin.jsp").forward(request, response);
	}
}
