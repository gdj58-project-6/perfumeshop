package controller.emp.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentService;

/**
 * Servlet implementation class RemoveCommentController
 */
@WebServlet("/admin/removeComment")
public class RemoveCommentController extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentCode = 0;
		if(request.getParameter("commentCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} else if(request.getParameter("commentCode") != null) {
			commentCode = Integer.parseInt(request.getParameter("commentCode"));
			System.out.println(commentCode + "<--commentCode");
		}
		
		// 삭제 메서드 호출
		this.commentService = new CommentService();
		int deleteComment = commentService.removeQuestionComment(commentCode);
		
		if(deleteComment == 1) {
			System.out.println("삭제완료");
			response.sendRedirect(request.getContextPath() + "/admin/comment");
		}
	}
}
