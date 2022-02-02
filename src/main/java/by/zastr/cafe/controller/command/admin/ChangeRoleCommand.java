package by.zastr.cafe.controller.command.admin;

import static by.zastr.cafe.controller.command.RequestParameter.USER_ID;

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
import jakarta.servlet.http.HttpServletRequest;

/**
 * class ChangeRoleCommand.
 *
 * @author A.Zastrozhyn
 */
public class ChangeRoleCommand implements Command{

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
		int userId = Integer.parseInt(request.getParameter(USER_ID));
		UserServiceImpl userService = UserServiceImpl.getInstance();
		router.setPagePath(PagePath.USERS);
		try {
			User user = userService.findById(userId).get();
			if (user.getRole().equals(User.Role.CLIENT)){
				user.setRole(User.Role.ADMIN);
				userService.update(user);
				return router;
			}
			if (user.getRole().equals(User.Role.ADMIN)){
				user.setRole(User.Role.CLIENT);
				userService.update(user);
				return router;
			}
			request.setAttribute(AttributeName.MESSAGE, UserMessage.SUCCESSFUL);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "User cannot be blocked:", e);
            throw new CommandException("User cannot be blocked:", e);
		}
		return router;
	}

}
