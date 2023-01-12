package controller.member.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;

@WebServlet("/member/goodsCompleted")
public class GoodsCompletedController extends HttpServlet {
	private OrderService orderService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 결제 완료 창
		// 주문 성공 후 orderCode 값 받기
		int orderCode = Integer.parseInt(request.getParameter("orderCode"));
		
		// Model
		this.orderService = new OrderService();
		ArrayList<HashMap<String, Object>> list = orderService.getSelectOrderByGoodsList(orderCode);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/view/member/order/goodsCompletedList.jsp").forward(request, response);
	}

}
