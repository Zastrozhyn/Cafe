package by.zastr.cafe.controller.command.menu;

import static by.zastr.cafe.controller.command.AttributeName.BEGIN_LIST;
import static by.zastr.cafe.controller.command.AttributeName.CURRENT_PAGE;
import static by.zastr.cafe.controller.command.AttributeName.END_LIST;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class PaginationCommand
 * @author A.Zastrozhyn
 *
 */
public class PaginationCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
		router.setPagePath(PagePath.MENU);
		int currentPage = (int) session.getAttribute(RequestParameter.CURRENT_PAGE);
		int pages = (int) session.getAttribute(RequestParameter.PAGES);
		int nextPage = Integer.parseInt(request.getParameter(RequestParameter.NEXT_PAGE));
		currentPage += nextPage;
		if(currentPage>pages|currentPage<1) {
 			request.setAttribute(BEGIN_LIST, DisplayMenuCommand.DEFAULT_BEGIN);
 			request.setAttribute(END_LIST, DisplayMenuCommand.DEFAULT_END);
 			session.setAttribute(CURRENT_PAGE, DisplayMenuCommand.DEFAULT_CURRENT_PAGE);
 			return router;
		}
		session.setAttribute(AttributeName.BEGIN_LIST, (DisplayMenuCommand.DEFAULT_BEGIN + (currentPage-1)*DisplayMenuCommand.DEFAULT_END));
		session.setAttribute(AttributeName.END_LIST, currentPage*DisplayMenuCommand.DEFAULT_END);
		session.setAttribute(CURRENT_PAGE, currentPage);

		return router;
	}

}
