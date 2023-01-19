package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import dao.CounterDao;
import service.CounterService;

@WebListener
public class CounterListener implements HttpSessionListener {
	private CounterService counterService;
	// 세션이 생성될때
	public void sessionCreated(HttpSessionEvent se)  { 
         // 현재 서브의 접속자 수 -> 서버속성을 이용
		int num = (int)(se.getSession().getServletContext().getAttribute("currentCount"));
		se.getSession().getServletContext().setAttribute("currentCount", num + 1);
		System.out.println("세션생성 currentCount :" + se.getSession().getServletContext().getAttribute("currentCount"));
		
		// 전체 or 날짜별 접속자 수
		this.counterService = new CounterService();
		try {
			int todayCount = counterService.getSelectTodayCount();
			if(todayCount == 0) { // 오늘 첫 접속자
				counterService.getInsertCount();
			} else { // 오늘 첫 접속자가 아님
				counterService.getUpdateCount();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	// 세션이 소멸될때 (로그아웃)
    public void sessionDestroyed(HttpSessionEvent se)  { 
         int num = (int)(se.getSession().getServletContext().getAttribute("currentCount"));
         se.getSession().getServletContext().setAttribute("currentCount", num - 1);
         System.out.println("세션소멸 currentCount :" + se.getSession().getServletContext().getAttribute("currentCount"));
    }
	
}
