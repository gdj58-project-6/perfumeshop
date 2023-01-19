package controller.counter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CounterService;
import vo.SiteCounter;

@WebServlet("/counter")
public class counterController extends HttpServlet {
	private CounterService counterService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.counterService = new CounterService();
		
		try {
			// 오늘 총 접속자 수
			int todayCount = counterService.getSelectTodayCount();
			System.out.println("todayCount : " + todayCount);
			request.setAttribute("todayCount", todayCount);
			
			// 사이트가 생성된 후부터 누적 접속자 수
			int totalCount = counterService.getSelectTotalCount();
			System.out.println("totalCount : " + totalCount);
			request.setAttribute("totalCount", totalCount);
			
			// 년도별 누적 접속자 수
			ArrayList<SiteCounter> yearList = counterService.getSelectCountByYear();
			request.setAttribute("yearList", yearList);
			
			// 월별 누적 접속자 수
			ArrayList<SiteCounter> monthList = counterService.getSelectCountByMonth();
			request.setAttribute("monthList", monthList);
			
			// 일자별 누적 접속자 수
			ArrayList<SiteCounter> dayList = counterService.getSelectCountByDay();
			request.setAttribute("dayList", dayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/view/counter.jsp").forward(request, response);
	}

}
