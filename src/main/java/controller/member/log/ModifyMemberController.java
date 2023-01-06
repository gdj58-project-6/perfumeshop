package controller.member.log;

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
import vo.Customer;

@WebServlet("/member/modifyMember")
public class ModifyMemberController extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 멤버정보 수정 form
		// 현재 로그인 정보
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)(session.getAttribute("loginCustomer"));
		
		// 로그인 안되어있으면
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 로그인 되어있으면
		String memberId = loginCustomer.getCustomerId();
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(memberId);
		
		// Model
		this.customerService = new CustomerService();
		ArrayList<HashMap<String, Object>> list = customerService.getSelectCustomerOne(customer);
		
		request.setAttribute("customerOne", list);
		
		request.getRequestDispatcher("/WEB-INF/view/member/log/modifyMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 멤버 정보 수정 Action
	}

}
