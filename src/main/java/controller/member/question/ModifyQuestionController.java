package controller.member.question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/modifyQuestion")
public class ModifyQuestionController extends HttpServlet {
	// 수정form은 modal
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// modal에서 post로
	}

}
