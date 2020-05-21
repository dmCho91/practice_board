package simple_bbs.command.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.BbsDAO;

public class BbsDeleteCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;
		
		HttpSession session = request.getSession();
		String loginUser = (String)session.getAttribute("loginUser");
		String bId = request.getParameter("bId");
		String userId = request.getParameter("userId");
		
		if(loginUser==null || bId==null || userId==null || !loginUser.equals(userId)) {
			msg = "ACCESS_DENIED";
			request.setAttribute("msg", msg);
			return msg;
		}
		
		BbsDAO dao = new BbsDAO();
		int result = dao.bbsDelete(bId);
		if(result != 1) {
			msg = "DATABASE_ERROR";
			request.setAttribute("msg", msg);
			return msg;
		}
		
		request.setAttribute("msg", "DELETE_SUCCESS");
		return msg;
	}

}
