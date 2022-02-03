package by.zastr.cafe.controller.command.user;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;

import static by.zastr.cafe.controller.command.RequestParameter.*;
import static by.zastr.cafe.controller.command.RequestParameter.SESSION_LOCALE;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.service.UserService;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class RegistrationCommand.
 *
 * @author A.Zastrozhyn
 */
public class RegistrationCommand implements Command {
	 private static final UserService userService = UserServiceImpl.getInstance();

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
		String name = request.getParameter(NAME);
		String lastName = request.getParameter(LAST_NAME);
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		try {
			String result = userService.registration(name, lastName, phone, login, password, confirmPassword, email, locale);
			if (result.equals(messageManager.getMessage(UserMessage.REGISTRATION_SUCCESSFUL))) {
				router.setPagePath(PagePath.LOGIN);
			}
			else {
				router.setPagePath(PagePath.REGISTRATION);
				request.setAttribute(AttributeName.NAME, name);
				request.setAttribute(AttributeName.LAST_NAME, lastName);
				request.setAttribute(AttributeName.PHONE, phone);
				request.setAttribute(AttributeName.EMAIL, email);
			}
			request.setAttribute(AttributeName.REGISTRATION_RESULT, result);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot be registered:", e);
            throw new CommandException("User cannot be registered:", e);
		}
		return router;
	}
}
