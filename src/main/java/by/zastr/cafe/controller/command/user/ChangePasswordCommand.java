package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.RequestParameter.*;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class ChangePasswordCommand.
 *
 * @author A.Zastrozhyn
 */
public class ChangePasswordCommand implements Command{

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
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
		String newPassword = request.getParameter(NEW_PASSWORD);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			String result = userService.changePassword(userId, login, password, newPassword, confirmPassword, locale);
			request.setAttribute(AttributeName.MESSAGE, result);
			if(!result.equals(messageManager.getMessage(UserMessage.SUCCESSFUL))) {
				router.setPagePath(PagePath.CHANGE_PASSWORD);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Password cannot be registered:", e);
            throw new CommandException("Password cannot be registered:", e);
		}
		
		return router;
	}

}
