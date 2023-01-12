package controller.member.question;

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
 * Servlet implementation class QuestionController
 */
@WebServlet("/member/question")
public class QuestionController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer loginCustomer = null;
		Emp loginEmp = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") instanceof Customer) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			session.setAttribute("loginMember", loginCustomer);
		} else if(session.getAttribute("loginMember") instanceof Emp) {
			loginEmp = (Emp)session.getAttribute("loginMember");
			session.setAttribute("loginMember", loginEmp);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/question.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
