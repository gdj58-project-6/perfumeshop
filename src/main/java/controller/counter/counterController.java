package controller.counter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CounterService;

@WebServlet("/counter")
public class counterController extends HttpServlet {
	private CounterService counterService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.counterService = new CounterService();
		
		try {
			int todayCount = counterService.getSelectTodayCount();
			System.out.println("todayCount : " + todayCount);
			request.setAttribute("todayCount", todayCount);
			
			int totalCount = counterService.getSelectTotalCount();
			System.out.println("totalCount : " + totalCount);
			request.setAttribute("totalCount", totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/counter.jsp").forward(request, response);
	}

}
