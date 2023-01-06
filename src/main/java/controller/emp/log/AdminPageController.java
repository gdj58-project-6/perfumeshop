package controller.emp.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.Customer;
import vo.Emp;

/**
 * Servlet implementation class AdminPageController
 */
@WebServlet("/admin/adminPage")
public class AdminPageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 관리자 페이지
		HttpSession session = request.getSession();
		// 관리자가 아니거나 
		Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		if(loginEmp == null || loginCustomer != null) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		
		request.setAttribute("loginEmp", loginEmp);
		request.getRequestDispatcher("/WEB-INF/view/emp/admin/adminPage.jsp").forward(request, response);
	}
}
