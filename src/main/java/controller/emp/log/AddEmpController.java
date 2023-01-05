package controller.emp.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import vo.Emp;

@WebServlet("/admin/addAdmin")
public class AddEmpController extends HttpServlet {
	private EmpService empService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 회원가입 view
		request.getRequestDispatcher("/WEB-INF/view/emp/log/addEmp.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 로그인 유효성 검사
		
		// 중복 아이디 검사
		
		// 입력 값
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		
		// 바인딩
		Emp emp = new Emp();
		emp.setEmpId(id);
		emp.setEmpName(name);
		emp.setEmpPw(pw);
		
		// Model
		this.empService = new EmpService();
		int row = empService.getInsertEmp(emp);
		
		if(row == 1) {
			System.out.println("직원 가입 성공");
		} else {
			System.out.println("직원 가입 실패");
		}
	}

}
