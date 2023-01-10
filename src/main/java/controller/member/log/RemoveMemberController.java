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
		Customer loginMember = (Customer)session.getAttribute("loginMember");

		// 로그인이 안되어있다면
		if (loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}

		// 로그인되어있으면
		request.setAttribute("loginMember", loginMember);
		request.getRequestDispatcher("/WEB-INF/view/member/log/removeMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원탈퇴 Action
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");

		String id = loginMember.getCustomerId();
		String pw = request.getParameter("customerPw");
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerPw(pw);

		// Model
		this.customerService = new CustomerService();
		int row = customerService.getDeleteCustomer(customer);
		if (row == 1) {
			System.out.println("회원 탈퇴 성공");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			System.out.println("회원 탈퇴 실패");
			response.sendRedirect(request.getContextPath() + "/member/removeMember");
		}
	}

}