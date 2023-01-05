package controller.member.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/member/login")
public class LoginController extends HttpServlet {
	private CustomerService customerService;
	private EmpService empService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 view
		request.getRequestDispatcher("/WEB-INF/view/member/log/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String customerId = request.getParameter("customerId");
		String customerPw = request.getParameter("customerPw");
		String empId = request.getParameter("empId");
		String empPw = request.getParameter("empPw");
		// System.out.println(customerId + "<-- customerId");
		// System.out.println(customerPw + "<-- customerPw");
		// System.out.println(empId + "<-- empId");
		// System.out.println(empPw + "<-- empPw");
		
		
		// 메서드 호출 매개값
		Customer paramCustomer = new Customer();
		paramCustomer.setCustomerId(customerId);
		paramCustomer.setCustomerPw(customerPw);
		// System.out.println(paramCustomer.getCustomerId() + "<-- paramCustomerId");
		// System.out.println(paramCustomer.getCustomerPw() + "<-- paramCustomerPw");
		// 메서드 호출 매개값
		Emp paramEmp = new Emp();
		paramEmp.setEmpId(empId);
		paramEmp.setEmpPw(empPw);
		// System.out.println(paramEmp.getEmpId() + "<-- paramEmpId");
		// System.out.println(paramEmp.getEmpPw() + "<-- paramEmpPw");
		
		
		this.customerService = null;
		this.empService = null;
		Customer customerLogin = null;
		Emp empLogin = null;
		if(paramEmp.getEmpId() == null && paramEmp.getEmpPw() == null) {
			customerService = new CustomerService();
			customerLogin = customerService.loginCustomer(paramCustomer);
			session.setAttribute("loginCustomer", customerLogin);
			response.sendRedirect(request.getContextPath()+"/home");
		} else if(paramCustomer.getCustomerId() == null && paramCustomer.getCustomerPw() == null) {
			empService = new EmpService();
			empLogin = empService.loginEmp(paramEmp);
			session.setAttribute("loginEmp", empLogin);
			response.sendRedirect(request.getContextPath()+"/home");
		}
	}
}
