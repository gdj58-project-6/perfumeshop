package controller.member.order;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CustomerService;
import service.GoodsService;
import vo.Customer;
import vo.Goods;

@WebServlet("/member/goodsPayMent")
public class GoodsPaymentController extends HttpServlet {
	private GoodsService goodsService;
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 정보 저장
		HttpSession session = request.getSession();
		Customer loginMember = (Customer)(session.getAttribute("loginMember"));
		String id = loginMember.getCustomerId();
		
		// 보여줄 상품 코드, 상품 개수
		int goodsCode = Integer.parseInt(request.getParameter("goodsCode"));
		int goodsQuantity = Integer.parseInt(request.getParameter("goodsQuantity"));
		
		// 바인딩
		Goods goods = new Goods();
		goods.setGoodsCode(goodsCode);
		
		Customer customer = new Customer();
		customer.setCustomerId(id);
		
		// Model
		// goodsOne에서 넘어오는 굿즈 상세 정보
		this.goodsService = new GoodsService();
		HashMap<String, Object> goodsOne = goodsService.getGoodsOneByOrder(goods);
		
		// 로그인한 회원의 상세 정보
		this.customerService = new CustomerService();
		HashMap<String, Object> customerOne = customerService.getSelectCustomerOne(customer);
		
		request.setAttribute("goodsQuantity", goodsQuantity);
		request.setAttribute("customerOne", customerOne);
		request.setAttribute("goodsOne", goodsOne);
		request.getRequestDispatcher("/WEB-INF/view/member/order/goodsPayment.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
