package controller.emp.shop;

import java.io.File;
import java.io.IOException;

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

@WebServlet("/admin/addGoods")
public class AddGoodsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 등록 form
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/shop/addGoods.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 등록 Action
		request.setCharacterEncoding("utf-8"); // 인코딩
		
		// 프로젝트안 upload폴더의 실제 물리적 위치를 반환
		String dir = request.getServletContext().getRealPath("/upload");
		int maxFileSize = 1024 * 1024 * 100; // 100Mbyte
		
		// 업로드 폴더내 동일한 이름이 있으면 뒤에 숫자를 추가
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();
		
		MultipartRequest mreq = new MultipartRequest(request, dir, maxFileSize, "utf-8", fp);
		
		// 이미지 파일 검사
		String contentType = mreq.getContentType("goodsImg");
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			String goodsName = mreq.getParameter("goodsName");
			
			Goods goods = new Goods();
			goods.setGoodsName(goodsName);
			
			GoodsImg goodsImg = new GoodsImg();
			goodsImg.setFileName(contentType);
			
			GoodsService goodsService = new  GoodsService();
			goodsService.addGoods(goods, goodsImg, dir);
		} else {
			System.out.println("*.jpg, *.png파일만 업로드가능");
			File f = new File(dir+"\\"+mreq.getFilesystemName("goodsImg"));
			if(f.exists()) {
				f.delete();
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/addGoods");
	}

}
