package simple_bbs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple_bbs.command.Command;
import simple_bbs.command.bbs.BbsDeleteCommand;
import simple_bbs.command.bbs.BbsListsCommand;
import simple_bbs.command.bbs.BbsModifyCommand;
import simple_bbs.command.bbs.BbsViewCommand;
import simple_bbs.command.bbs.BbsWriteCommand;
import simple_bbs.command.user.UserJoinCommand;
import simple_bbs.command.user.UserLoginCommand;
import simple_bbs.command.user.UserModifyCommand;
import simple_bbs.command.user.UserSelectCommand;

@WebServlet("*.do")
public class FrontServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String viewPage = null;
		Command command = null;
		String msg = null;

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String comm = uri.substring(contextPath.length());

		switch (comm) {
		/* main �κ� */
		case "/main.do":
			viewPage = "/views/main.jsp";
			break;

		/* �������� �κ� */
		// ȸ������ ��
		case "/join_view.do":
			viewPage = "/views/user/joinForm.jsp";
			break;
		// ȸ������
		case "/join.do":
			command = new UserJoinCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// �α���
		case "/login.do":
			command = new UserLoginCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// ȸ������ ���� ��
		case "/userModify_view.do":
			command = new UserSelectCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/user/userModifyForm.jsp";
			}
			break;
		// ȸ������ ����
		case "/userModify.do":
			command = new UserModifyCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// �α׾ƿ�
		case "/logout.do":
			viewPage = "/views/user/logout.jsp";
			break;

		/* �Խ��� �κ� */
		case "/board.do":
			command = new BbsListsCommand();
			msg = command.execute(request, response);
			viewPage = "/views/bbs/board.jsp";
			break;
		// �� �ۼ� ������
		case "/write_view.do":
			viewPage = "/views/bbs/write.jsp";
			break;
		// �� �ۼ� ó��
		case "/write.do":
			command = new BbsWriteCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/writeComplete.jsp";
			}
			break;
		// �Խñ� ����
		case "/view.do":
			command = new BbsViewCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/view.jsp";
			}
			break;
		// �� ���� ������
		case "/modify_view.do":
			command = new BbsViewCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/bbsModify.jsp";
			}
			break;
		// �� ���� ó��
		case "/modify.do":
			command = new BbsModifyCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/view.jsp";
			}
			break;
		// �� ���� ó�� -> available=0
		case "/delete.do":
			command = new BbsDeleteCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/board.do";
			}
			break;

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
