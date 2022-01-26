package by.zastr.cafe.controller.command.user;


import static by.zastr.cafe.controller.command.RequestParameter.*;

import java.util.Optional;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeleteUserCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = new Router();
		String login = request.getParameter(LOGIN);
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
