package controller.member.shop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/goodsPayMent")
public class GoodsPaymentController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 결제창 현재 무통장 결제로.....
		
		
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/goodsPaymentList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 구매 Action
	}

}
