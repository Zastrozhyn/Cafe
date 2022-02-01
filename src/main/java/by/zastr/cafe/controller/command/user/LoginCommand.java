package by.zastr.cafe.controller.command.user;

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

import static by.zastr.cafe.controller.command.RequestParameter.*;

import java.util.Optional;

import org.apache.logging.log4j.Level;

import static by.zastr.cafe.controller.command.AttributeName.*;

public class LoginCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		String locale = (String) session.getAttribute(AttributeName.SESSION_LOCALE);
		MessageManager messageManager = MessageManager.defineLocale(locale);
		Router router = new Router();
		router.setRedirect();
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			Optional<User> user = userService.login(login, password);
			if (user.isPresent()) {
				session.setAttribute(SESSION_USER, user.get());
				if(user.get().getRole().equals(User.Role.ADMIN)) {
					session.setAttribute(ADMIN, Boolean.TRUE);
					session.setAttribute(CLIENT, Boolean.TRUE);
				}
				if(user.get().getRole().equals(User.Role.CLIENT)) {
					session.setAttribute(CLIENT, Boolean.TRUE);
				}
				router.setPagePath(PagePath.MAIN_PAGE);
			}
			else {
				router.setPagePath(PagePath.LOGIN);
				request.setAttribute(AttributeName.MESSAGE, messageManager.getMessage(UserMessage.WRONG_PASSWORD_OR_LOGIN));
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot login:", e);
            throw new CommandException("User cannot login:", e);
		}
		return router;
	}

}
