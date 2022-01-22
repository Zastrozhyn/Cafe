package by.zastr.cafe.controller.command.admin;

import java.util.List;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.controller.command.AttributeName;
import by.zastr.cafe.controller.command.Command;
import by.zastr.cafe.controller.command.PagePath;
import by.zastr.cafe.controller.command.Router;
import by.zastr.cafe.exception.CommandException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ViewUserCommand implements Command {
	private static final UserServiceImpl userService = UserServiceImpl.getInstance();

	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
        	List<User> userList;
 			userList = userService.findAll();
 			session.setAttribute(AttributeName.USER_LIST, userList);
 			router.setPagePath(PagePath.ADMINISTRATION);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "cannot go to administration", e);
            throw new CommandException("cannot go to administration", e);
		}
        
		return router;
	}

}
