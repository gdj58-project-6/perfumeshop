package controller.emp.shop;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;
import vo.Goods;
import vo.GoodsImg;



@WebServlet("/admin/removeGoods")
public class RemoveGoodsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 삭제
		
		// 관리자만 상품 삭제할 수 있음
		// 정말 삭제하겠냐는 경고창 하나 나오게
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		
		GoodsImg goodsImg = new GoodsImg();
		goodsImg.setGoodsCode(goodsCode);
		
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		
		GoodsService goodsService = new GoodsService();
		int row = goodsService.removeGoods(goods, goodsImg);
		// System.out.println(row);
		
		if(row != 2) {
			System.out.println("상품삭제 실패");
		} else {
			System.out.println("상품삭제 성공");
		}
	
		response.sendRedirect(request.getContextPath()+"/member/goodsList");
	}

}
