package controller.member.log;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private CustomerService customerService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 view
		request.getRequestDispatcher("/WEB-INF/view/member/log/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		// 로그인 Action
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		
		
		System.out.println(customerId);
		System.out.println(customerPw);
		
		
		// 고객 로그인 메서드 호출 매개값
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(customerId);
		paramCustomer.setCustomerPw(customerPw);
		
		// 고객 로그인 메서드 호출
		this.customerService = new CustomerService();
		Customer result = customerService.customerLogin(paramCustomer);
		session.setAttribute("loginCustomer", result);
		response.sendRedirect(request.getContextPath()+"/home");
		
		/*
		if(request.getParameter("customerId") != null && request.getParameter("customerPw") != null 
				&& request.getParameter("empId") == null && request.getParameter("empPw") == null) {
			
		} else if(request.getParameter("customerId") == null && request.getParameter("customerPw") == null 
				&& request.getParameter("empId") != null && request.getParameter("empPw") != null) {
			// 직원 로그인 메서드 호출 매개값
			Emp emp = new Emp();
			emp.setEmpId(empId);
			emp.setEmpPw(empPw);
			
			// 직원 로그인 메서드 호출
			
		}
		
		*/
		
		
		
	}
}
