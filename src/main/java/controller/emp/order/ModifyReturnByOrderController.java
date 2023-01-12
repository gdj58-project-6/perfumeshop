package controller.emp.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReturnHistoryService;
import vo.Orders;

@WebServlet("/admin/modifyReturnByOrder")
public class ModifyReturnByOrderController extends HttpServlet {
	private ReturnHistoryService returnHistoryService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		String orderState = "반품완료";
		
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
