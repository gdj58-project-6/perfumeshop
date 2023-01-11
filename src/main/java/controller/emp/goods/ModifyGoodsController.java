package controller.emp.goods;

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

@WebServlet("/admin/modifyGoods")
public class ModifyGoodsController extends HttpServlet {
	private GoodsService goodsService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 Form
		// 관리자 ID로 로그인 했을 경우에만 들어올 수 있음 
		
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		// System.out.println(goodsCode);
		
		this.goodsService = new GoodsService();
		ArrayList<HashMap<String, Object>> list = goodsService.modifyGoodsForm(goodsCode);
		System.out.println(list);
		request.setAttribute("list", list);
		
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/emp/goods/modifyGoods.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 Action 이미지 수정은 보류 -> 일단 이미지 수정도 해봄!
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String fileName = request.getParameter("fileName");
		String goodsName =  request.getParameter("goodsName");
		int goodsPrice = Integer.parseInt(request.getParameter("goodsPrice"));
		String goodsMemo = request.getParameter("goodsMemo");
		String soldout = request.getParameter("soldout");
		int hit = Integer.parseInt(request.getParameter("hit"));
		
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(goodsPrice);
		goods.setGoodsMemo(goodsMemo);
		goods.setSoldout(soldout);
		goods.setHit(hit);
		// System.out.println(goods);
		// System.out.println(fileName);
		
		this.goodsService = new GoodsService();
		int row = goodsService.modifyGoodsAction(goods, fileName);
		
		if(row != 1) {
			System.out.println("수정실패");
		} else {
			System.out.println("수정성공");
		}
		response.sendRedirect(request.getContextPath()+"/member/goodsList");
	}

}
