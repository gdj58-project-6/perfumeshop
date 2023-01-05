package controller.member.shop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ShopService;
import vo.Customer;
import vo.Emp;
import vo.Notice;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private ShopService shopService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =  request.getSession();
		// Customer loginCustomer = (Customer)session.getAttribute("loginCustomer");
		Emp loginEmp = (Emp)session.getAttribute("loginEmp");
		// System.out.println(loginCustomer);
		
		// 공지목록 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int rowPerPage = 5;
		// 공지목록 메서드 호출
		this.shopService = new ShopService();
		ArrayList<Notice> list = shopService.getNoticeList(currentPage, rowPerPage);
		int lastPage = shopService.getHomeCount() / rowPerPage;
		// System.out.println(lastPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("list", list);
		request.setAttribute("loginEmp", loginEmp);
		
		// 공지사항 list
		request.getRequestDispatcher("/WEB-INF/view/member/shop/home.jsp").forward(request, response);
	}

}
