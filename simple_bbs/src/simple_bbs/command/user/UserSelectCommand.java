package simple_bbs.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.UserDAO;
import simple_bbs.dto.UserDTO;

public class UserSelectCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "ACCESS_DENIED";
		}
		
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.selectUser(loginUser);
		
		request.setAttribute("user", dto);
		return null;
	}

}
