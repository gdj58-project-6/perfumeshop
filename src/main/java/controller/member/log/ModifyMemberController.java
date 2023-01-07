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
		Customer loginCustomer = (Customer)(session.getAttribute("loginMember"));
		
		
		// 멤버 정보 수정 Action
	}

}
