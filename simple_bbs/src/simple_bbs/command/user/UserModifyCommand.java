package simple_bbs.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.UserDAO;
import simple_bbs.dto.UserDTO;

public class UserModifyCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		UserDTO user = new UserDTO();
		user.setUserId(request.getParameter("userId"));
		user.setUserPw(request.getParameter("userPw"));
		user.setUserName(request.getParameter("userName"));
		user.setUserGender(request.getParameter("userGender"));
		user.setUserEmail(request.getParameter("userEmail"));
		
		UserDAO dao = new UserDAO();
		int result = dao.modify(user);
		if(result == UserDAO.MODIFY_DATABASE_ERROR) {
			String msg = "MODIFY_DATABASE_ERROR";
			request.setAttribute("msg", msg);
			return msg;
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user.getUserId());
			request.setAttribute("msg", "MODIFY_SUCCESS");
			return null;
		}
		
	}

}
