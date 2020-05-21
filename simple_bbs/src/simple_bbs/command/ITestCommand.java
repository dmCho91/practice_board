package simple_bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ITestCommand {

	public int execute(HttpServletRequest request, HttpServletResponse response);
}
