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
		/* main 부분 */
		case "/main.do":
			viewPage = "/views/main.jsp";
			break;

		/* 유저관리 부분 */
		// 회원가입 폼
		case "/join_view.do":
			viewPage = "/views/user/joinForm.jsp";
			break;
		// 회원가입
		case "/join.do":
			command = new UserJoinCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// 로그인
		case "/login.do":
			command = new UserLoginCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// 회원정보 수정 폼
		case "/userModify_view.do":
			command = new UserSelectCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/user/userModifyForm.jsp";
			}
			break;
		// 회원정보 수정
		case "/userModify.do":
			command = new UserModifyCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/user/userAlert.jsp";
			} else {
				viewPage = "/views/main.jsp";
			}
			break;
		// 로그아웃
		case "/logout.do":
			viewPage = "/views/user/logout.jsp";
			break;

		/* 게시판 부분 */
		case "/board.do":
			command = new BbsListsCommand();
			msg = command.execute(request, response);
			viewPage = "/views/bbs/board.jsp";
			break;
		// 글 작성 페이지
		case "/write_view.do":
			viewPage = "/views/bbs/write.jsp";
			break;
		// 글 작성 처리
		case "/write.do":
			command = new BbsWriteCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/writeComplete.jsp";
			}
			break;
		// 게시글 보기
		case "/view.do":
			command = new BbsViewCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/view.jsp";
			}
			break;
		// 글 수정 페이지
		case "/modify_view.do":
			command = new BbsViewCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/bbsModify.jsp";
			}
			break;
		// 글 수정 처리
		case "/modify.do":
			command = new BbsModifyCommand();
			msg = command.execute(request, response);
			if (msg != null) {
				viewPage = "/views/bbs/bbsAlert.jsp";
			} else {
				viewPage = "/views/bbs/view.jsp";
			}
			break;
		// 글 삭제 처리 -> available=0
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
