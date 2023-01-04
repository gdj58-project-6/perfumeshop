package controller.member.shop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/goodsOne")
public class GoodsOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 상세보기 정보, 리뷰, 문의글, 장바구니, 바로구매, 옵션수정, 가격 동적으로 수정 문의 답변도 model
	}

}
