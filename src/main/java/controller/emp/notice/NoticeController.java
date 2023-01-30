package controller.emp.notice;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/admin/notice")
public class NoticeController extends HttpServlet {
    	private NoticeService noticeService;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Customer loginCustomer = null;
	    Emp loginEmp = null;
	    // 세션 요청
	    HttpSession session = request.getSession();

	    // 로그인 정보
	    if(session.getAttribute("loginMember") instanceof Customer) {
		loginCustomer = (Customer)session.getAttribute("loginMember");
		session.setAttribute("loginMember", loginCustomer);
	    } else if(session.getAttribute("loginMember") instanceof Emp) {
		loginEmp = (Emp)session.getAttribute("loginMember");
		session.setAttribute("loginMember", loginEmp);
	    }
	    
	    // 공지목록 페이징
	    // 현재 페이지
	    int currentPage = 1;
	    if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	    }
	    // 페이지당 표시할 목록의 수
	    int rowPerPage = 5;
	    if(request.getParameter("rowPerPage") != null) {
		rowPerPage = Integer.parseInt(request.getParameter("rowPerPage")); 
	    }
	    
	    // 공지목록 메서드
	    this.noticeService = new NoticeService();
	    ArrayList<Notice> list = noticeService.getNoticeList(currentPage, rowPerPage);
	    // 공지 마지막 페이지 구하기
	    int cnt = noticeService.getNoticeCount();
	    int lastPage = 0;
	    if(cnt % rowPerPage == 0) {
		lastPage = cnt / rowPerPage;
	    } else if(cnt % rowPerPage != 0 ) {
		lastPage = (cnt / rowPerPage) + 1;
	    }
	    
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("rowPerPage", rowPerPage);
	    request.setAttribute("list", list);
	    request.setAttribute("lastPage", lastPage);
	    
	    request.getRequestDispatcher("/WEB-INF/view/emp/notice/notice.jsp").forward(request, response);
	}
}
