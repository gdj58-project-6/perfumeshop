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
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)session.getAttribute("loginMember");
		
		// 최고관리자만 탈퇴 권한
		if(loginMember.getAuthCode() != 7) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		// 탈퇴시킬 직원 아이디
		String empId = request.getParameter("empId");
		System.out.println(empId +"<< empId 디버깅");
		
		this.empService = new EmpService();
		int row = empService.getDeleteEmp(empId);
		
		if(row == 1) {
			System.out.println("직원 탈퇴 성공");
		} else {
			System.out.println("직원 탈퇴 실패");
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/modifyByAdmin");
	}

}
