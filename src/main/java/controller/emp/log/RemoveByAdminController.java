package controller.emp.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.EmpService;
import vo.Emp;

@WebServlet("/admin/removeByAdmin")
public class RemoveByAdminController extends HttpServlet {
	private EmpService empService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 멤버 탈퇴
		// 인코딩
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		
		// 최고관리자만 탈퇴 권한
		if(loginEmp.getAuthCode() != 7) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 탈퇴시킬 직원 아이디
		String empId = request.getParameter("empId");
		
		this.empService = new EmpService();
		int row = empService.getDeleteEmp(empId);
		
		if(row == 1) {
			System.out.println("직원 탈퇴 성공");
		} else {
			System.out.println("직원 탈퇴 실패");
		}
	}

}
