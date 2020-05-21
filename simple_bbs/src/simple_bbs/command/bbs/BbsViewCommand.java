package simple_bbs.command.bbs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import simple_bbs.command.Command;
import simple_bbs.dao.BbsDAO;
import simple_bbs.dto.BbsDTO;

public class BbsViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String msg = null;

		HttpSession session = request.getSession();
		String loginUser = (String) session.getAttribute("loginUser");

		String bId = request.getParameter("bId");
		String complete = request.getParameter("complete");

		BbsDAO dao = new BbsDAO();
		BbsDTO dto = new BbsDTO();
		if (complete != null && complete.equals("write")) {
			dto = dao.writeComplete(loginUser);
		} else if (complete != null && complete.equals("modify")) {
			dto = dao.view(bId, 0);
		} else {
			dto = dao.view(bId, 1);
		}
		
		if (dto == null) {
			msg = "NO_DATA";
			request.setAttribute("msg", msg);
			return msg;
		}

		request.setAttribute("list", dto);
		return null;
	}

}
