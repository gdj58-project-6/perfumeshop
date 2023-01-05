package controller.member.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;
import vo.Customer;

@WebServlet("/member/addMember")
public class AddMemberController extends HttpServlet {
	private CustomerService customerService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 view
		request.getRequestDispatcher("/WEB-INF/view/member/log/addMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입 Action
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 로그인 유효성 검사
		
		// 입력 값
		String id = request.getParameter("id");            
		String name = request.getParameter("name");
		String phone = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		System.out.println(phone); // 디버깅
		String pw = request.getParameter("pw");
		
		// 바인딩
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerName(name);
		customer.setCustomerPhone(phone);
		customer.setCustomerPw(pw);
		
		// Model
		this.customerService = new CustomerService();
		
		/* 아이디 중복검사
		if(customerService.getSelectCustomerId(customer) == null) { // 중복되는 아이디 없으면 회원가입 진행
			int row = customerService.getInsertCustomer(customer);
			if(row == 1) {
				System.out.println("회원가입 성공");
			} else {
				System.out.println("회원가입 실패");
			}
		} else {
			System.out.println("중복되는 아이디");
		}*/
	}

}
