package by.zastr.cafe.controller.command.user;

import static by.zastr.cafe.controller.command.AttributeName.REGISTRATION_RESULT;
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
import jakarta.servlet.http.HttpSession;


public class EditProfileCommand implements Command{

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		HttpSession session = request.getSession();
		Router router = new Router();
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		String name = request.getParameter(NAME);
		String lastName = request.getParameter(LAST_NAME);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			String result = userService.edit(userId, name, lastName, phone, email);
			if(result.equals(UserMessage.UPDATE_SUCCESSFUL)) {
				router.setPagePath(PagePath.PROFILE);
				session.removeAttribute(AttributeName.SESSION_USER);
				session.setAttribute(AttributeName.SESSION_USER, userService.findById(userId).get());
			}
			else {
				router.setPagePath(PagePath.EDIT_PROFILE);
			}
			request.setAttribute(REGISTRATION_RESULT, result);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot be registered:", e);
            throw new CommandException("User cannot be registered:", e);
		}
		return router;
	}
}
