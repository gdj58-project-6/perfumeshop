package controller.member.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.PwHistoryService;
import vo.Customer;

@WebServlet("/member/modifyMemberPw")
public class ModifyMemberPwController extends HttpServlet {
	private CustomerService customerService;
	private PwHistoryService pwHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 수정 form
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer) (session.getAttribute("loginMember"));

		// 로그인 안되어있으면
		if (loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}

		// 로그인되어있으면
		request.getRequestDispatcher("/WEB-INF/view/member/log/modifyMemberPw.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 비밀번호 수정 Action
		request.setCharacterEncoding("UTF-8");

		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));

		// 값 저장
		String id = loginMember.getCustomerId();
		String pw = request.getParameter("customerPw");
		String changePw = request.getParameter("changePw");
		System.out.println(id);
		System.out.println(pw);
		System.out.println(changePw);

		// Model
		this.customerService = new CustomerService();
		this.pwHistoryService = new PwHistoryService();
		if(pwHistoryService.getSelectCustomerPw(id, changePw) == null) {
			int row = customerService.getUpdateCustomerPw(id, changePw, pw);
			if(row == 1) {
				System.out.println("비밀번호 수정 성공");
			} else {
				System.out.println("비밀번호 수정 실패");
			}
		} else {
			System.out.println("중복되는 비밀번호");
		}
	}

}