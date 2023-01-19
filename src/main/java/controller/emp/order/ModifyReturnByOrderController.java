package controller.emp.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReturnHistoryService;
import vo.Customer;
import vo.Orders;
import vo.PointHistory;

@WebServlet("/admin/modifyReturnByOrder")
public class ModifyReturnByOrderController extends HttpServlet {
	private ReturnHistoryService returnHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)session.getAttribute("loginMember");
		String id = loginMember.getCustomerId();
		
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int point = Integer.parseInt(request.getParameter("point"));
		String orderState = "반품 완료";

		// 바인딩
		Orders orders = new Orders();
		orders.setOrderCode(orderCode);
		orders.setOrderState(orderState);

		// Model
		this.returnHistoryService = new ReturnHistoryService();
		int row = returnHistoryService.getUpdateReturnStateByAdmin(orders);
		
		if(row == 1) {
			System.out.println("반품 승인 성공");
			response.sendRedirect(request.getContextPath() + "/admin/returnHistoryList");
		} else {
			System.out.println("반품 승인 실패");
			response.sendRedirect(request.getContextPath() + "/admin/returnHistoryList");
		}
	}
}
