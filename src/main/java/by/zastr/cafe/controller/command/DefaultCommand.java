package by.zastr.cafe.controller.command;

import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class Default Command.
 *
 * @author A.Zastrozhyn
 */
public class DefaultCommand implements Command{

	/**
	 * Execute.
	 *
	 * @param request the request
	 * @return the router
	 * @throws CommandException the command exception
	 */
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(PagePath.MAIN_PAGE);
        return router;
	}

}
