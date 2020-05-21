package simple_bbs.command.bbs;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simple_bbs.command.Command;
import simple_bbs.dao.BbsDAO;
import simple_bbs.dto.BbsDTO;
import simple_bbs.page.Pagination;

public class BbsListsCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		int curPage = request.getParameter("page")!=null? Integer.parseInt(request.getParameter("page")):1;
		
		BbsDAO dao = new BbsDAO();
		int cntList = dao.getTotal();
		Pagination pagination = new Pagination(cntList, curPage);
		
		ArrayList<BbsDTO> dtos = dao.bbsLists(pagination.getStartNo(), pagination.getEndNo());
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", dtos);
		
		return null;
	}

}
