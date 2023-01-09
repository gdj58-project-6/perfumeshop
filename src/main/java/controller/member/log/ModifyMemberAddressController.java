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

@WebServlet("/member/modifyMemberAddress")
public class ModifyMemberAddressController extends HttpServlet {
	private CustomerService customerService;
	// 수정 form modal로 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer) (session.getAttribute("loginMember"));
		
		// 값 받아오기
		String id = loginMember.getCustomerId();
		String address = request.getParameter("address");
		
		// Model
		this.customerService = new CustomerService();
		int row = customerService.getUpdateCustomerAddress(id, address);
		
		if(row == 1) {
			System.out.println("주소 수정 성공");
		} else {
			System.out.println("주소 수정 실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/member/memberOne");
	}

}
