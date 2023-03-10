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

	// 회원정보 수정 modal 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer) (session.getAttribute("loginMember"));

		// input 값 받기
		String id = loginMember.getCustomerId();
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String pw = request.getParameter("pw");

		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerName(name);
		customer.setCustomerPhone(phone);
		customer.setCustomerPw(pw);

		// Model
		this.customerService = new CustomerService();
		int row = customerService.getUpdateCustomerOne(customer);
		
		if(row == 1) {
			System.out.println("회원정보 수정 성공");
		} else {
			System.out.println("회원정보 수정 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/memberOne");
	}

}