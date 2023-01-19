package controller.emp.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentService;

@WebServlet("/admin/removeGoodsComment")
public class RemoveGoodsCommentController extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int goodsCommentCode = 0;
		if(request.getParameter("goodsCommentCode") == null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		} else if(request.getParameter("goodsCommentCode") != null) {
			goodsCommentCode = Integer.parseInt(request.getParameter("goodsCommentCode"));
			// System.out.println(goodsCommentCode + "<--goodsCommentCode");
		}
		// System.out.println(goodsCommentCode);
		
		// 삭제 메서드 호출
		this.commentService = new CommentService();
		int deleteGoodsComment = commentService.removeGoodsQuestionComment(goodsCommentCode);
		
		if(deleteGoodsComment == 1) {
			System.out.println("삭제완료");
			response.sendRedirect(request.getContextPath() + "/admin/goodsComment");
		}
		
	}
}
