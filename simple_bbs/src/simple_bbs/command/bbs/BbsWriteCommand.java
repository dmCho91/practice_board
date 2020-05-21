package simple_bbs.command.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.BbsDAO;
import simple_bbs.servlet.FrontServlet;

public class BbsWriteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;

		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("loginUser");

		if (loginUser == null) {
			msg = "ACCESS_DENIED";
			request.setAttribute("msg", msg);
			return msg;
		}

		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		BbsDAO dao = new BbsDAO();
		int result = dao.write(loginUser, bTitle, bContent);
		if (result == BbsDAO.DATABASE_ERROR) {
			msg = "DATABASE_ERROR";
			request.setAttribute("msg", msg);
			return msg;
		}

		return msg;

	}

}
