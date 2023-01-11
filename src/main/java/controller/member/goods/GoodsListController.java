package controller.member.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GoodsDao;
import service.*;

@WebServlet("/member/goodsList")
public class GoodsListController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 굿즈 리스트
		this.goodsService = new GoodsService();
		// 로그인 비로그인 상관없이 볼 수 있음
		
		// 검색 및 페이징
		String word = request.getParameter("word");
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 10;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		// lastPage
		int count = goodsService.goodsCount(word);
		int lastPage = (int)Math.ceil((double)count / (double)rowPerPage);
		
		ArrayList<HashMap<String, Object>> list = goodsService.getGoodsList(currentPage, rowPerPage, word);
		// System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("word", word);
		request.setAttribute("lastPage", lastPage);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/goods/goodsList.jsp");
		rd.forward(request, response);
	}

}
