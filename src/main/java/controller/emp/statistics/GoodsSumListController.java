package controller.emp.statistics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;

@WebServlet("/GoodsSumListController")
public class GoodsSumListController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.getSelectGoodsSumList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/view/emp/statistics/goodsSumList.jsp").forward(request, response);
	}
}
