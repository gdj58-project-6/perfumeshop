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

@WebServlet("/member/modifyMemberPw")
public class ModifyMemberPwController extends HttpServlet {
	private CustomerService customerService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 비밀번호 수정 form
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer) (session.getAttribute("loginCustomer"));

		// 로그인 안되어있으면
		if (loginCustomer == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}

		// 로그인되어있으면
		request.getRequestDispatcher("/WEB-INF/view/member/log/modifyMemberPw.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 비밀번호 수정 Action
		request.setCharacterEncoding("UTF-8");

		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginCustomer = (Customer) (session.getAttribute("loginCustomer"));

		// 값 저장
		String id = loginCustomer.getCustomerId();
		String pw = request.getParameter("customerPw");
		String chagePw = request.getParameter("changPw");

	}

}