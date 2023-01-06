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
		
		// 디버깅용 로그인 정보 저장
		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)(session.getAttribute("loginEmp"));
		String empId = loginEmp.getEmpId();
		String empPw = "1234";
		
		this.empService = new EmpService();
		empService.getDeleteEmp(empId, empPw);
		
	}

}
