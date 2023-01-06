package controller.emp.log;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Customer;
import vo.Emp;

@WebServlet("/admin/modifyByAdmin")
public class ModifyEmpByAdminController extends HttpServlet {
	private EmpService empService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 리스트 출력 view
		// 팀원이나 접속했다면 접근불가
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null) {
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
		int lastPage = empService.getEmpCountByEmpModify() / rowPerPage;
		System.out.println(lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		request.setAttribute("loginEmp", loginEmp);
		
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/modifyEmp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 레벨 수정 탈퇴 계정사용
	}

}
