package simple_bbs.command.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.BbsDAO;

public class BbsModifyCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;

		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("loginUser");

		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		if (loginUser == null || bId == null || bTitle == null || bContent == null) {
			msg = "ACCESS_DENIED";
			request.setAttribute("msg", msg);
			return msg;
		}

		BbsDAO dao = new BbsDAO();
		int result = dao.bbsModify(bId, bTitle, bContent);
		if (result == BbsDAO.DATABASE_ERROR) {
			msg = "DATABASE_ERROR";
		} else {
			request.setAttribute("modify_bId", bId);
		}
		return msg;

	}

}
