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
		
		String id = loginMember.getCustomerId();
		
		// 로그인되어있으면
		// 검색
		// 검색을 하지 않았을때 모두 풀력하기 위해서 공백 대입
		String stateSearch = "";
		String sort = "DESC";
		
		if(request.getParameter("stateSearch") != null && !request.getParameter("stateSearch").equals("")) {
			stateSearch = request.getParameter("stateSearch");
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
		// 한페이지당 보여줄 order 개수
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		// 페이징을 위한 row count
		this.orderService = new OrderService();
		int cnt = orderService.getCustomerOrderListCnt(id, stateSearch);
		//  마지막페이지
		int lastPage = cnt / rowPerPage;
		if(lastPage % rowPerPage != 0) {
			lastPage++;
		}
		
		int showPage = 5; // 버튼에 표시할 페이지 개수
		int startPage = ((currentPage - 1) / showPage) * showPage + 1; // 시작 페이지
		System.out.println(startPage);
		int endPage = (((currentPage - 1) / showPage) + 1) * showPage; // 현제 페이지의 마지막 페이지 번호
		System.out.println(endPage);
		// lastPage가 endPage보다 작을 경우 lastPage로 바꿔줌
		if(lastPage < endPage) {
			endPage = lastPage;
		}
		
		// 이전 버튼 활성화 여부
		boolean prev = (startPage == 1) ? false : true;
		// 다음 버튼 활성화 여부
		boolean next = (endPage == lastPage) ? false : true;
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(loginMember.getCustomerId());
		
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> list = orderService.getSelectOrderByCustomerLIst(id, stateSearch, sort, beginRow, rowPerPage);
		
		// 페이징
		request.setAttribute("list", list);
		session.setAttribute("currentPage", currentPage);
		session.setAttribute("lastPage", lastPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		// 검색
		session.setAttribute("state", stateSearch);
		session.setAttribute("sort", sort);
		
		request.getRequestDispatcher("/WEB-INF/view/member/order/orderList.jsp").forward(request, response);
	}
}
