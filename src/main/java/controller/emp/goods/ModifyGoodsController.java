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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.GoodsService;
import vo.Goods;
import vo.GoodsImg;

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
		// System.out.println(list);
		request.setAttribute("list", list);
		
		
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/emp/goods/modifyGoods.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 수정 Action 
		// 프로젝트안 upload폴더의 실제 물리적 위치를 반환
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100; // 100Mbyte
		
		// 업로드 폴더내 동일한 이름이 있으면 뒤에 숫자를 추가
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();

		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// 이미지 파일 검사
		String contentType = mreq.getContentType("goodsImg");
		String originName = mreq.getOriginalFileName("goodsImg"); // 원본 파일 이름
		String fileName = mreq.getFilesystemName("goodsImg"); //  저장된 파일 이름
		/*
		System.out.println(contentType);
		System.out.println(originName);
		System.out.println(fileName);
		*/
		
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			int goodsCode = Integer.parseInt(mreq.getParameter("goodsCode"));
			String goodsName =  mreq.getParameter("goodsName");
			int goodsPrice = Integer.parseInt(mreq.getParameter("goodsPrice"));
			String goodsMemo = mreq.getParameter("goodsMemo");
			String soldout = mreq.getParameter("soldout");
			int hit = Integer.parseInt(mreq.getParameter("hit"));
			
			Goods goods = new Goods();
			goods.setGoodsCode(goodsCode);
			goods.setGoodsName(goodsName);
			goods.setGoodsPrice(goodsPrice);
			goods.setGoodsMemo(goodsMemo);
			goods.setSoldout(soldout);
			goods.setHit(hit);
			// System.out.println(goods);
			
			GoodsImg goodsImg = new GoodsImg();
			goodsImg.setGoodsCode(goodsCode);
			goodsImg.setFileName(fileName);
			goodsImg.setOriginName(originName);
			goodsImg.setContentType(contentType);
			// System.out.println(goodsImg);
			
			this.goodsService = new GoodsService();
			int row = goodsService.modifyGoodsAction(goods, goodsImg);
			
			if(row != 2) {
				System.out.println("수정실패");
			} else {
				System.out.println("수정성공");
			}
		}
		response.sendRedirect(request.getContextPath()+"/member/goodsList");
	}

}
