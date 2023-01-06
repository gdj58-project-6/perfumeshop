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

import service.GoodsService;
import vo.Goods;

@WebServlet("/member/goodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 상세보기 정보, 리뷰, 문의글, 장바구니, 바로구매, 옵션수정, 가격 동적으로 수정 문의 답변도 model
		request.setCharacterEncoding("utf-8"); // 인코딩
		// 로그인 비로그인 상관없이 볼 수 있음
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		// System.out.println(goodsCode);
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.goodsOne(goodsCode);
		// System.out.println(list);
		request.setAttribute("list", list);
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/goodsOne.jsp");
		rd.forward(request, response);
	}

}
