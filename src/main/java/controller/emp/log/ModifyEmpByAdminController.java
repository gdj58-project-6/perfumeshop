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
import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/admin/modifyByAdmin")
public class ModifyEmpByAdminController extends HttpServlet {
	private EmpService empService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 리스트 출력 view
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)session.getAttribute("loginMember");
		// 비로그인시 접근불가 그리고 멤버레벨이 7이 아니면 접근불가
		if(loginMember == null || loginMember.getAuthCode() != 7) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		// 직원 목록 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		
		// 서비스 호출
		this.empService = new EmpService();
		ArrayList<Emp> list = empService.getEmpList(currentPage, rowPerPage);
		int cnt = empService.getEmpCountByEmpModify();
		// System.out.println(cnt + "<-- 총 목록수");
		int lastPage = 0;
		if(cnt % rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
			// System.out.println(lastPage + "<--나누어 떨어질때");
		} else if(cnt % rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) + 1;
			// System.out.println(cnt%rowPerPage);
			// System.out.println(lastPage + "<--나누어 떨어지지 않을때");
		}
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		request.setAttribute("loginMember", loginMember);
		
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/modifyEmpLevel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 레벨 수정 탈퇴 계정사용
		// 레벨 수정(1단계:수정버튼으로 수정되게끔) 탈퇴기능은 나중에 
		request.setCharacterEncoding("utf-8");
		int empCode = Integer.parseInt(request.getParameter("empCode")); 
		String empId = request.getParameter("empId");
		int authCode = Integer.parseInt(request.getParameter("authCode"));
		//System.out.println(empCode);
		//System.out.println(empId);
		//System.out.println(authCode);
		
		// 메서드 호출
		this.empService = new EmpService();
		int row = empService.updateEmpLevel(authCode, empId, empCode);
		
		if(row == 1) {
			// System.out.println("수정완료");
			response.sendRedirect(request.getContextPath()+"/admin/modifyByAdmin");
		}
	}

}
