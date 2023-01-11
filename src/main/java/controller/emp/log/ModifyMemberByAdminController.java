package controller.emp.log;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import vo.Customer;
import vo.Emp;

@WebServlet("/admin/modifyMember")
public class ModifyMemberByAdminController extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = request.getParameter("msg");
		// 고객리스트 출력 view
		// 비로그인or등급 7이 아니면 접근불가 
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getAuthCode() != 7) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		// 고객 목록 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		// 서비스 호출
		this.customerService = new CustomerService();
		ArrayList<Customer> list = customerService.getCustomerList(currentPage, rowPerPage);
		int cnt = customerService.getCustomerCountByMemberModify();
		// System.out.println(cnt);
		int lastPage = 0;
		if(cnt / rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
			// System.out.println(lastPage + "<--나누어 떨어질때");
		} else if(cnt / rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) +1;
			// System.out.println(lastPage + "<--안나누어 떨어질때");
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		request.setAttribute("loginMember", loginMember);
		request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/log/modifyMemberLevel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 레벨 수정(1단계:수정버튼으로 수정되게끔) 탈퇴기능은 나중에
		int customerCode = Integer.parseInt(request.getParameter("customerCode")); 
		String customerId = request.getParameter("customerId");
		int authCode = Integer.parseInt(request.getParameter("authCode"));
		// System.out.println(customerCode);
		// System.out.println(customerId);
		// System.out.println(authCode);
		// 아직 수정시스템 미구현 혹시 몰라 고객 등급이 3보다 크면 수정 안되게끔
		if(authCode > 4) {
			String msg = URLEncoder.encode("고객의 최대등급은 3입니다.", "utf-8");
			response.sendRedirect(request.getContextPath()+"/admin/modifyMember?msg="+msg);
		}
		
		// 메서드 호출
		this.customerService = new CustomerService();
		int row = customerService.updateMemberLevel(authCode, customerId);
		
		if(row == 1) {
			// System.out.println("수정완료");
			response.sendRedirect(request.getContextPath()+"/admin/modifyMember");
		}
		
	}

}
