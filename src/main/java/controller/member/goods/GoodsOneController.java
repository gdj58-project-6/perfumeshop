package controller.member.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CartService;
import service.GoodsService;
import service.QuestionService;
import service.ReviewService;
import vo.Cart;
import vo.Customer;
import vo.Emp;

@WebServlet("/member/goodsOne")
public class GoodsOneController extends HttpServlet {
	private GoodsService goodsService;
	private CartService cartService;
	private QuestionService questionService;
	private ReviewService reviewService;
	private Cart cart;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품 상세보기 정보, 리뷰, 문의글, 장바구니, 바로구매, 옵션수정, 가격 동적으로 수정 문의 답변도 model
		request.setCharacterEncoding("utf-8"); // 인코딩
		// 로그인 비로그인 상관없이 볼 수 있음
		Customer loginCustomer = null;
		Emp loginEmp = null;
		HttpSession session =  request.getSession();
		
		// customerId값 불러오기
		if(session.getAttribute("loginMember") == session.getAttribute("customerLogin")) {
			loginCustomer = (Customer)session.getAttribute("loginMember");
			request.setAttribute("loginMember", loginCustomer);
		} else if(session.getAttribute("loginMember") == session.getAttribute("empLogin")) {
			loginEmp = (Emp)session.getAttribute("loginMember");
			request.setAttribute("loginMember", loginEmp);
		} 
		// System.out.println(loginMember);
		// 상품코드 없이는 상품상세보기칸으로 못감
		int goodsCode = 0;
		if(request.getParameter("goodsCode") == null) {
			response.sendRedirect(request.getContextPath()+"/member/goodsList");
			return;
		} else if(request.getParameter("goodsCode") != null) {
			goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
			// System.out.println(goodsCode);
		}
		
		// 페이징 마지막 페이지는 아직 미구현
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		int currentPage2 = 1;
		if(request.getParameter("currentPage2") != null) {
			currentPage2 = Integer.parseInt(request.getParameter("currentPage2"));
		}
		int rowPerPage = 5;
		
		
		// System.out.println(goodsCode); 아직 lastPage는 미구현
		this.goodsService = new GoodsService();
		this.questionService = new QuestionService();
		this.reviewService = new ReviewService();
		ArrayList<HashMap<String, Object>> list = goodsService.goodsOne(goodsCode);
		ArrayList<HashMap<String, Object>> questionList = questionService.getGoodsQuestionList(currentPage, rowPerPage);
		ArrayList<HashMap<String, Object>> reviewList = reviewService.getReviewListByCustomer(currentPage2, rowPerPage);
		int cnt = questionService.getQuestionCountByGoodsQuestion();
		int count = reviewService.getReviewCount();
		int lastPage = 0;
		int lastPage2 = 0;
		if(cnt % rowPerPage == 0) {
			lastPage = cnt / rowPerPage;
			// System.out.println(lastPage + "<--나누어 떨어질때");
		} else if(cnt % rowPerPage != 0) {
			lastPage = (cnt / rowPerPage) + 1;
			// System.out.println(cnt%rowPerPage);
			// System.out.println(lastPage + "<--나누어 떨어지지 않을때");
		}
		
		if(count % rowPerPage == 0) {
			lastPage2 = count / rowPerPage;
		} else if(count % rowPerPage != 0) {
			lastPage2 = (count / rowPerPage) + 1;
		}
		// System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("questionList", questionList);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("goodsCode", goodsCode);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("currentPage2", currentPage2);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("lastPage2", lastPage2);
		// View 연결
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/member/goods/goodsOne.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 장바구니 담기 버튼 누르면 장바구니로 내역 이동
		request.setCharacterEncoding("utf-8"); // 인코딩
		// 컨트롤러에서 alert 띄우기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		
		HttpSession session =  request.getSession();
		Customer loginCustomer = (Customer)session.getAttribute("loginMember");
		// 값 받아오기
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		String customerId = request.getParameter("customerId");
		int cartQuantity = Integer.parseInt(request.getParameter("cartQuantity"));
		/*
		System.out.println(goodsCode);
		System.out.println(customerId);
		System.out.println(cartQuantity);
		*/
		if(loginCustomer != null) {
			this.cart = new Cart();
			cart.setGoodsCode(goodsCode);
			cart.setCustomerId(customerId);
			cart.setCartQuantity(cartQuantity);
			
			int row = 0;
			
			this.cartService = new CartService();
			boolean checkCart = cartService.checkCartList(cart);
			if(checkCart == false) { // 카트에 없는 물건일 경우
				row = cartService.addCart(cart);
				// System.out.println("장바구니 담기 성공");
				writer.println("<script>alert('장바구니 담기 성공');history.go(-1);</script>"); 
				writer.close();
			} else { // 카트에 같은물건이 이미 있는경우 
				System.out.println("장바구니 담기 실패");
				writer.println("<script>alert('이미 장바구니에 담긴 상품입니다');history.go(-1);</script>"); 
				writer.close();
			}
		}
		// 비로그인 장바구니 담기 
		
		if(loginCustomer == null) {
			System.out.println("비로그인 장바구니 담기");
			ArrayList<HashMap<String, Object>> list = null;
			ArrayList<HashMap<String, Object>> cart = (ArrayList<HashMap<String,Object>>)session.getAttribute("cart");
			
			if(cart == null) { // 장바구니에 물건이 없을 경우
				list = new ArrayList<HashMap<String, Object>>();
			} else {
				int goodsNo = 0;
				for(HashMap<String, Object> c : cart) {
						goodsNo = Integer.parseInt(String.valueOf(c.get("goodsCode")));
						System.out.println(goodsNo);
						if(goodsNo == goodsCode) {
							session.setAttribute("cart", cart);
							System.out.println("장바구니 담기 실패");
							writer.println("<script>alert('이미 장바구니에 담긴 상품입니다');history.go(-1);</script>"); 
							writer.close();
							return;
						} else {
							System.out.println("장바구니 추가 가능");
						}
					}
					list = cart;
			}
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("goodsCode", request.getParameter("goodsCode"));
			m.put("goodsName", request.getParameter("goodsName"));
			m.put("goodsPrice", request.getParameter("goodsPrice"));
			m.put("cartQuantity", request.getParameter("cartQuantity"));
			m.put("filename", request.getParameter("filename"));
			list.add(m);
			// System.out.println(list);
			session.setAttribute("cart", list);
			writer.println("<script>alert('장바구니 담기 성공');history.go(-1);</script>"); 
			writer.close();
			return;
		}
	}

}