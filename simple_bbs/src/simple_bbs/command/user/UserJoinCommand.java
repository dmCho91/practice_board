package simple_bbs.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.UserDAO;
import simple_bbs.dto.UserDTO;

public class UserJoinCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;
		
		//session 체크
		String loginUser = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
			loginUser = (String) session.getAttribute("loginUser");
		}
		if(loginUser != null) {
			msg = "ALREADY_LOGINED";
			request.setAttribute("msg", msg);
			return msg;
		}
		
		//user 객체에 담기
		UserDTO user = new UserDTO();
		user.setUserId(request.getParameter("userId"));
		user.setUserPw(request.getParameter("userPw"));
		user.setUserName(request.getParameter("userName"));
		user.setUserGender(request.getParameter("userGender"));
		user.setUserEmail(request.getParameter("userEmail"));
		
		//회원가입 로직
		UserDAO dao = new UserDAO();
		
		int result = dao.join(user);
		if(result == UserDAO.JOIN_DATABASE_ERROR){
			msg = "JOIN_EXIST_ID";
			request.setAttribute("msg", msg);
			return msg;
		}else{
			session.setAttribute("loginUser", user.getUserId());
			request.setAttribute("msg", "JOIN_SUCCESS");
			return msg;
		}

	}

}
