package controller.member.question;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/modifyGoodsQuestion")
public class ModifyGoodsQuestionController extends HttpServlet {
	// 문의글 수정 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 비로그인 접근불가
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/member/question/modifyGoodsQuestion.jsp").forward(request, response);
	}

	// 문의글 수정 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
