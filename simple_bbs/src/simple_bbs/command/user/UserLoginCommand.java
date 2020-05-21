package simple_bbs.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.UserDAO;

public class UserLoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;

		//session체크
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		if(loginUser != null) {
			msg = "ALREADY_LOGINED";
			request.setAttribute("msg", msg);
			return msg;
		}
		// request 받기
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		//로그인 로직 수행
		UserDAO dao = new UserDAO();
		int result = dao.login(userId, userPw);
		if(result == UserDAO.LOGIN_NO_EXIST_ID){
			msg = "NO_EXIST_ID";
		}else if(result == UserDAO.LOGIN_PASSWORD_NOT_MATCH){
			msg = "NOT_MATCH_PASSWORD";
		}else if(result == UserDAO.LOGIN_DATABASE_ERROR){
			msg = "LOGIN_DATABASE_ERROR";
		}else{
			session.setAttribute("loginUser", userId);
		}
		
		request.setAttribute("msg", msg);
		return msg;
	}

}
