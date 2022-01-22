package by.zastr.cafe.controller.command;

import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(PagePath.MAIN_PAGE);
        return router;
	}

}
