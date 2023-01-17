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
		HttpSession session = request.getSession();
		// 로그인 되어있다면 로그인페이지는 접근불가
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		}
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
		if(paramCustomer.getCustomerId() != null && paramCustomer.getCustomerPw() != null) {
			customerService = new CustomerService();
			customerLogin = customerService.loginCustomer(paramCustomer);
			session.setAttribute("loginMember", customerLogin);
			// 비로그인 장바구니에 상품 담긴채로 로그인 했을 경우
			if(session.getAttribute("cart") != null) {
				System.out.println("장바구니로");
				response.sendRedirect(request.getContextPath()+"/AddCartList");
				return;
			}
			response.sendRedirect(request.getContextPath()+"/home");
		} else if(paramCustomer.getCustomerId() == null && paramCustomer.getCustomerPw() == null) {
			empService = new EmpService();
			empLogin = empService.loginEmp(paramEmp);
			session.setAttribute("loginMember", empLogin);
			response.sendRedirect(request.getContextPath()+"/home");
		}
		
		// 실패 디버깅
	}
}
