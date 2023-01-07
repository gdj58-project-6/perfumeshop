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

@WebServlet("/admin/adminOne")
public class EmpOneController extends HttpServlet {
	private EmpService empService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 직원 상세 정보 view
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Emp loginMember = (Emp)session.getAttribute("loginMember");
		// System.out.println(loginMember);
		// 로그인이 안되어있으면
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login");
			return;
		}
		
		// 로그인 되어있으면
		String empId = loginMember.getEmpId();
		// System.out.println(empId + "<-empId디버깅");
		
		// 바인딩
		Emp emp = new Emp();
		emp.setEmpId(empId);
		// System.out.println(emp.getEmpId() + "<--emp 정보");
		
		// Model
		this.empService = new EmpService();
		Emp empOne = empService.getSelectEmpOne(emp);
		// System.out.println(empOne + "<--empOne 디버깅");
		
		request.setAttribute("empOne", empOne);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/log/empOne.jsp").forward(request, response);
		
	}
}
