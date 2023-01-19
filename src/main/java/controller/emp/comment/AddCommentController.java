package controller.emp.comment;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import vo.Customer;
import vo.QuestionComment;


@WebServlet("/admin/addComment")
public class AddCommentController extends HttpServlet {
	private CommentService commentService;
	// 답글 입력폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		String questionMemo = request.getParameter("questionMemo");
		// System.out.println(questionCode);
		// System.out.println(questionMemo);
		HttpSession session = request.getSession();
		// 비로그인이거나 직원이 아니면 접근불가
		if(session.getAttribute("loginMember") == null || session.getAttribute("loginMember") instanceof Customer) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		request.setAttribute("code", questionCode);
		request.setAttribute("memo", questionMemo);
		request.setAttribute("msg", request.getParameter("msg"));
		
		request.getRequestDispatcher("/WEB-INF/view/emp/comment/addComment.jsp").forward(request, response);
	}
	// 답글 입력액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int questionCode = Integer.parseInt(request.getParameter("questionCode"));
		String commentMemo = request.getParameter("commentMemo");
		// System.out.println(goodsQuestionCode);
		
		// System.out.println(goodsCommentMemo);
		if(request.getParameter("commentMemo") == null || request.getParameter("commentMemo").equals("")) {
			String msg = URLEncoder.encode("답변 내용을 입력해주세요.", "utf-8");
			String questionMemo = URLEncoder.encode(request.getParameter("questionMemo"), "utf-8");
			// System.out.println(questionMemo);
			response.sendRedirect(request.getContextPath() + "/admin/addComment?msg="+msg+"&questionCode="+questionCode+"&questionMemo="+questionMemo);
			return;
		}
		// 메서드 호출에 필요한 매개값
		QuestionComment c = new QuestionComment();
		c.setQuestionCode(questionCode);
		c.setCommentMemo(commentMemo);
		
		// 메서드 호출
		this.commentService = new CommentService();
		int addComment = commentService.addQuestionComment(c);
		
		if(addComment == 1) {
			System.out.println("입력완료");
			response.sendRedirect(request.getContextPath() + "/admin/comment");
		}
	}

}
