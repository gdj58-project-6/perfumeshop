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

/**
 * Servlet implementation class ModifyNoticeController
 */
@WebServlet("/admin/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	private NoticeService noticeService;
	// 공지사항 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = 0;
		if(request.getParameter("noticeCode") != null) {
			noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		// 비로그인이거나 직원이 아니면 접근불가
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Customer) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		// 수정할때 현재 작성자 바뀔수있게
		Emp loginEmp = (Emp)session.getAttribute("loiginMember");
		request.setAttribute("loginMember", loginEmp);
		
		// 메서드 호출
		this.noticeService = new NoticeService();
		Notice notice = noticeService.getNoticeByModifyNotice(noticeCode);
		request.setAttribute("n", notice);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/shop/modifyNotice.jsp").forward(request, response);
	}
	// 공지사항 수정 액션 제목,내용,작성자,작성일=수정일
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		String empId = request.getParameter("empId");
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		// System.out.println(noticeCode);
		// System.out.println(empId);
		// System.out.println(noticeTitle);
		// System.out.println(noticeContent);
		
		// 메서드 호출에 필요한 매개값
		Notice n = new Notice();
		n.setEmpId(empId);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setNoticeCode(noticeCode);
		
		// 메서드 호출
		this.noticeService = new NoticeService();
		int updateNotice = noticeService.modifyNotice(n);
		
		if(updateNotice == 1) {
			System.out.println("수정완료");
		} else {
			System.out.println("수정실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/noticeOne?noticeCode="+noticeCode);
	}

}
