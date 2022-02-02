package by.zastr.cafe.controller.command.user;


import static by.zastr.cafe.controller.command.RequestParameter.*;

import java.util.Optional;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import by.zastr.cafe.util.MessageManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * class DeleteUserCommand
 * @author A.Zastrozhyn
 *
 */
public class DeleteUserCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = new Router();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		User sessionUser = (User) session.getAttribute(AttributeName.SESSION_USER);
		String login = request.getParameter(LOGIN);
		if (!sessionUser.getLogin().equals(login)) {
			router.setPagePath(PagePath.DELETE_USER);
			request.setAttribute(AttributeName.MESSAGE, messageManager.getMessage(UserMessage.WRONG_PASSWORD_OR_LOGIN));
			return router;
		}
		String password = request.getParameter(PASSWORD);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			Optional<User> user = userService.login(login, password);
			if (user.isPresent()) {
				userService.delete(user.get().getUserId());
		        session.invalidate();
			}
			else {
				router.setPagePath(PagePath.DELETE_USER);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot login:", e);
            throw new CommandException("User cannot login:", e);
		}
		return router;
	}

}
