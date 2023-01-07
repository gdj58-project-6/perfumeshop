package controller.emp.shop;

import java.io.File;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.GoodsService;
import vo.Emp;
import vo.Goods;
import vo.GoodsImg;

@WebServlet("/admin/addGoods")
public class AddGoodsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 등록 form
		// 직원으로 로그인 한 사람만 들어올 수 있음
		HttpSession session =  request.getSession();
		Emp loginMember = (Emp)session.getAttribute("loginMember");	
		// 
		if(loginMember == null || loginMember.getAuthCode() < 4) {
			response.sendRedirect(request.getContextPath()+"/member/login");
			return;
		}
		// 상품등록시 로그인한 직원 아이디 출력을 위해
		request.setAttribute("loginMember", loginMember);
		// System.out.println(loginEmp.getEmpId());
		
		// 나중에 방어코드 넣기
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/emp/shop/addGoods.jsp");
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
		String originName = mreq.getOriginalFileName("goodsImg"); // 원본 파일 이름
		String fileName = mreq.getFilesystemName("goodsImg"); //  저장된 파일 이름
		
		if(contentType.equals("image/jpeg") || contentType.equals("image/png")) {
			String goodsName = mreq.getParameter("goodsName");
			int goodsPrice = Integer.parseInt(mreq.getParameter("goodsPrice"));
			String soldout = mreq.getParameter("soldout");
			int hit = Integer.parseInt(mreq.getParameter("hit"));
			String empId = mreq.getParameter("empId");
			
			Goods goods = new Goods();	
			goods.setGoodsName(goodsName);
			goods.setGoodsPrice(goodsPrice);
			goods.setSoldout(soldout);
			goods.setHit(hit);
			goods.setEmpId(empId);
		
			/*
			System.out.println(goodsName);
			System.out.println(goodsPrice);
			System.out.println(soldout);
			System.out.println(hit);
			System.out.println(empId);
			*/
			
			GoodsImg goodsImg = new GoodsImg();
			goodsImg.setOriginName(originName);
			goodsImg.setFileName(fileName);
			goodsImg.setContentType(contentType);
	
			GoodsService goodsService = new  GoodsService();
			goodsService.addGoods(goods, goodsImg, dir);
		
		} else {
			System.out.println("*.jpg, *.png파일만 업로드가능");
			File f = new File(dir+"\\"+mreq.getFilesystemName("goodsImg"));
			if(f.exists()) {
				f.delete();
			}
		}
		
		response.sendRedirect(request.getContextPath()+"/member/goodsList");
	}

}
