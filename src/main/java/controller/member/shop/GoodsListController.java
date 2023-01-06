package controller.member.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

@WebServlet("/member/goodsList")
public class GoodsListController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 굿즈 리스트
		this.goodsService = new GoodsService();
		// 로그인 비로그인 상관없이 볼 수 있음
		
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsList();
		// System.out.println(list);
		request.setAttribute("list", list);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/goodsList.jsp");
		rd.forward(request, response);
	}

}
