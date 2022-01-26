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
import jakarta.servlet.http.HttpServletRequest;

public class ChangePasswordCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		Router router = new Router();
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		String confirmPassword = request.getParameter(CONFIRM_PASSWORD);
		String newPassword = request.getParameter(NEW_PASSWORD);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			String result = userService.changePassword(userId, login, password, newPassword, confirmPassword);
			if(!result.equals(UserMessage.SUCCESSFUL)) {
				router.setPagePath(PagePath.CHANGE_PASSWORD);
				request.setAttribute(AttributeName.MESSAGE, result);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Password cannot be registered:", e);
            throw new CommandException("Password cannot be registered:", e);
		}
		
		return router;
	}

}
