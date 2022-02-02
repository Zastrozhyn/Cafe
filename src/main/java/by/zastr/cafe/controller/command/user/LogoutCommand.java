package by.zastr.cafe.controller.command.user;

import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class LogoutCommand
 * @author A.Zastrozhyn
 *
 */
public class LogoutCommand implements Command {

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setRedirect();
        HttpSession session = request.getSession();
        session.invalidate();
        return router;
	}

}
