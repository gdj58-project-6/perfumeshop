package controller.emp.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GoodsService;

@WebServlet("/admin/modifyGoods")
public class ModifyGoodsController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 Form
		// 관리자 ID로 로그인 했을 경우에만 들어올 수 있음 
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		System.out.println(goodsCode);
		
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.modifyGoodsForm(goodsCode);
		System.out.println(list);
		request.setAttribute("list", list);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 Action 이미지 수정은 보류
	}

}
