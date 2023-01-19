package controller;

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

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private NoticeService noticeService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer loginCustomer = null;
		Emp loginEmp = null;
		HttpSession session =  request.getSession();
		// 로그인정보 가져오기
		if(session.getAttribute("loginMember") == session.getAttribute("customerLogin")) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			request.setAttribute("loginMember", loginCustomer);
		} else if(session.getAttribute("loginMember") == session.getAttribute("empLogin")) {
			loginEmp = (Emp)session.getAttribute("loginMember");
			request.setAttribute("loginMember", loginEmp);
		}
		// 공지목록 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		// 공지목록 메서드 호출
		this.noticeService = new NoticeService();
		ArrayList<Notice> list = noticeService.getNoticeList(currentPage, rowPerPage);
		int cnt = noticeService.getHomeCount();
		int lastPage = 0;
		if(cnt % rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
		} else if(cnt % rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) +1;
		}
		// System.out.println(lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		
		// 공지사항 list
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}
}
