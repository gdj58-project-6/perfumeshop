package controller.emp.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.NoticeService;
import vo.Emp;
import vo.Notice;

@WebServlet("/admin/noticeOne")
public class NoticeOneController extends HttpServlet {
	private NoticeService noticeService;
	// 공지내용 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		// System.out.println(noticeCode);

		HttpSession session = request.getSession();
		Emp loginEmp = (Emp)session.getAttribute("loiginMember");
		request.setAttribute("loginMember", loginEmp);
		
		// 메서드 호출
		this.noticeService = new NoticeService();
		Notice notice = noticeService.getNotice(noticeCode);
		request.setAttribute("n", notice);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/noticeOne.jsp").forward(request, response);
	}
}
