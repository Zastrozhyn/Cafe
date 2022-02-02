package by.zastr.cafe.controller.command.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.RequestParameter;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

/**
 * class FindUserCommand.
 *
 * @author A.Zastrozhyn
 */
public class FindUserCommand implements Command{
	
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
		router.setPagePath(PagePath.USERS);
		String name = request.getParameter(RequestParameter.NAME);
		List<User> users = new ArrayList<User>();
		UserServiceImpl userService = UserServiceImpl.getInstance();
		try {
			users.addAll(userService.findByName(name));
			users.addAll(userService.findByLastName(name));
 			request.setAttribute(AttributeName.USER_LIST, users);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Error in command Find user", e);
            throw new CommandException( "Error in command Find user", e);
		}

		return router;
	}
	
}
