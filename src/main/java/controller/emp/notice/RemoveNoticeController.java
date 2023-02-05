package controller.emp.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeService;

@WebServlet("/admin/removeNotice")
public class RemoveNoticeController extends HttpServlet {
	private NoticeService noticeService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeCode = Integer.parseInt(request.getParameter("noticeCode"));
		// System.out.println(noticeCode);
		
		// 공지 삭제 메서드 호출
		this.noticeService = new NoticeService();
		int deleteNotice = noticeService.removeNotice(noticeCode);
		
		if(deleteNotice == 1) {
			System.out.println("삭제완료");
		} else {
			System.out.println("삭제실패");
		}
		
		response.sendRedirect(request.getContextPath() + "/admin/notice");
	}
}
