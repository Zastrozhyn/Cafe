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

/**
 * class ViewDeletedUser
 * @author A.Zastrozhyn
 *
 */
public class ViewDeletedUser implements Command{
	@Override
	public Router execute(HttpServletRequest request) throws CommandException {
		UserServiceImpl userService = UserServiceImpl.getInstance();
        Router router = new Router();
        try {
        	List<User> userList;
 			userList = userService.findAllDeleted();
 			request.setAttribute(AttributeName.USER_LIST, userList);
 			router.setPagePath(PagePath.USERS);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Cannot find all user", e);
            throw new CommandException("Cannot find all order user", e);
		}
        
		return router;
	}

}
