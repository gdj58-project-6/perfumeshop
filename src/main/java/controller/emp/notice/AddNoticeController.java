package controller.emp.notice;

import java.io.IOException;
import java.net.URLEncoder;

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
		
		request.setAttribute("msg", request.getParameter("msg"));
		
		request.getRequestDispatcher("/WEB-INF/view/emp/notice/addNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		String empId = request.getParameter("empId");
		// System.out.println(noticeTitle);
		// System.out.println(noticeContent);
		// System.out.println(empId);
		
		// 공지제목or내용이 null이거나 공백이면 추가폼에서 메시지 출력 
		if(request.getParameter("noticeTitle") == null || request.getParameter("noticeTitle").equals("")
			|| request.getParameter("noticeContent") == null || request.getParameter("noticeContent").equals("")) {
			String msg = URLEncoder.encode("공지를 입력해주세요.", "utf-8");
			response.sendRedirect(request.getContextPath() + "/admin/addNotice?msg="+msg);
			return;
		} 
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
