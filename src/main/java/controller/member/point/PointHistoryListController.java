package controller.member.point;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.PointHistoryService;
import vo.Customer;
import vo.PointHistory;

@WebServlet("/member/pointHistory")
public class PointHistoryListController extends HttpServlet {
	private PointHistoryService pointHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		String id = loginMember.getCustomerId();
		
		// Model
		this.pointHistoryService = new PointHistoryService();
		ArrayList<PointHistory> list = pointHistoryService.getSelectPointList(id);
		int usePoint = pointHistoryService.getSelectPoint(id, "사용");
		int savePoint = pointHistoryService.getSelectPoint(id, "적립");
		int cancelPoint = pointHistoryService.getSelectPoint(id, "취소");
		
		request.setAttribute("list", list);
		request.setAttribute("usePoint", usePoint);
		request.setAttribute("savePoint", savePoint);
		request.setAttribute("cancelPoint", cancelPoint);
		
		request.getRequestDispatcher("/WEB-INF/view/member/point/pointList.jsp").forward(request, response);
	}
}
