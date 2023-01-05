package controller.emp.log;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/admin/modifyMember")
public class ModifyMemberByAdminController extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 고객리스트 출력 view
		// 팀원이나 고객이 접속했다면 접근불가
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp.getAuthCode() != 7 || loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		// 고객 목록 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		// 서비스 호출
		this.customerService = new CustomerService();
		ArrayList<Customer> list = customerService.getCustomerList(currentPage, rowPerPage);
		int lastPage = customerService.getCustomerCountByMemberModify() / rowPerPage;
		// System.out.println(lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("latsPage", lastPage);
		request.setAttribute("list", list);
		request.setAttribute("loginEmp", loginEmp);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/modifyMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 레벨 수정 탈퇴
	}

}
