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

@WebServlet("/member/removeMember")
public class RemoveMemberController extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴 form
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)(session.getAttribute("loginCustomer"));
		
		// 로그인이 안되어있다면
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 로그인되어있으면
		request.setAttribute("loginCutomer", loginCustomer);
		
		request.getRequestDispatcher("/WEB-INF/view/member/log/removeMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴 Action
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer)(session.getAttribute("loginCustomer"));
		
		// 로그인이 안되어있다면
		if(loginCustomer == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 로그인되어있으면
		String pw = request.getParameter("customerPw");
		
		// Model
		this.customerService = new CustomerService();
		customerService.getDeleteCustomer(pw, pw);
	}

}
