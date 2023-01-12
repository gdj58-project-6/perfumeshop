package controller.member.goods;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;
import vo.Goods;

@WebServlet("/member/GoodsListSort")
public class GoodsListSortController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// col : 컬럼명
		// sort asc / desc
		String col = "goods_name"; 
		String sort = "asc";
		
		if(request.getParameter("col") != null) {
			col = request.getParameter("col");
		}
		
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		
		String paramSort = "asc"; // 제목클릭시 넘겨질 sort 값
		if(sort.equals("asc")) {
			paramSort = "desc";
		}
		/*
		System.out.println(col);
		System.out.println(sort);
		System.out.println(paramSort);
		*/
		this.goodsService = new GoodsService();
		ArrayList<Goods> sortList = goodsService.GoodsListSort(col, sort);
		// System.out.println(sortList);
		request.setAttribute("sortList", sortList);
		request.setAttribute("col", col);
		request.setAttribute("sort", sort);
		request.setAttribute("paramSort", paramSort);
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/goods/goodsList.jsp");
		rd.forward(request, response);
	}	
}
