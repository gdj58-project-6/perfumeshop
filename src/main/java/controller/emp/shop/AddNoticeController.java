package controller.emp.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NoticeService;
import vo.Customer;
import vo.Emp;
import vo.Notice;


@WebServlet("/admin/addNotice")
public class AddNoticeController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인이거나 고객이라면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Customer) {
			response.sendRedirect(request.getContextPath()+"/home");
			return;
		} else if(session.getAttribute("loginMember") !=null) {
			Emp loginEmp = (Emp)session.getAttribute("loginMember");
			session.setAttribute("loginMember", loginEmp);
		}
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/addNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String empId = request.getParameter("empId");
		// System.out.println(noticeTitle);
		// System.out.println(noticeContent);
		// System.out.println(empId);
		
		// 메서드 호출 매개값
		Notice notice = new Notice();
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		notice.setEmpId(empId);
		
		// 메서드 호출
		this.noticeService = new NoticeService();
		int addNotice = noticeService.addInsertNotice(notice);
		
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
